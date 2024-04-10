package com.coursera.WordGram;

import com.coursera.Interfaces.IMarkovModel;
import com.coursera.WordGram.WordGram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private HashMap<WordGram, ArrayList<String>> map;
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        map = new HashMap<WordGram, ArrayList<String>>();
        myRandom = new Random();
    }
    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    private void buildMap() {
        for(int i = 0; i < myText.length - myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            String next = myText[i + myOrder];
            if(map.containsKey(wg)) {
                map.get(wg).add(next);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                map.put(wg, list);
            }

        }
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
    public ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }

    public int hashCode() {
        WordGram hashC = new WordGram(myText, 0, myText.length);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < myText.length; i++) {
            sb.append(hashC.wordAt(i));
            sb.append(" ");
        }
        StringBuilder result = new StringBuilder(sb.toString().trim());
        return result.hashCode();
    }

//    public int indexOf(String[] words, WordGram target, int start) {
//
//        for(int i = start; i < words.length - myOrder; i++) {
//            WordGram wg = new WordGram(words, i, myOrder);
//            if(wg.equals(target)) return i;
//        }
//
//        return -1;
//    }
    public void printHashMapInfo() {

        System.out.println("It has " +  map.size() + " keys in the HashMap");
        int maxSize = 0;
        for (WordGram wg : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(wg).size());
    //			System.out.printf("Key:\t[%s]\tvalues: ", wg);
    //			System.out.println(myMap.get(wg));
        }
        System.out.println("The maximum number of elements following a key is " + maxSize);

        System.out.println("Keys with the maximum size value: ");
        for (WordGram wg : map.keySet()) {
            if (map.get(wg).size()==maxSize) {
                System.out.print(wg);
                System.out.println(" (The follow words: " + map.get(wg) + ")");
            }
        }
    }

}
