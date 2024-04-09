package com.coursera.WordGram;

import com.coursera.Interfaces.IMarkovModel;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int i=0; i < numWords-myOrder; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public int indexOf(String[] words, WordGram target, int start) {

        for(int i = start; i < words.length - myOrder; i++) {
            WordGram wg = new WordGram(words, i, myOrder);
            if(wg.equals(target)) return i;
        }

        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> result = new ArrayList<String>();

        int index = indexOf(myText, kGram, 0);
        while (index != -1) {
            result.add(myText[index + myOrder]);
            index = indexOf(myText, kGram, index+1);
        }

        return result;
    }


}
