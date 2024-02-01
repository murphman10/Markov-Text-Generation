package com.coursera.MarkovEfficient;

import com.coursera.MarkovNew.AbstractMarkovModel;

import java.util.ArrayList;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int n;
    public EfficientMarkovModel(int num) {

        myRandom = new Random();
        n = num;
    }
    public void setRandom(int seed){

        myRandom = new Random(seed);
    }
    public void setTraining(String s){

        myText = s.trim();
    }
    public String getRandomText(int numChars){

        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for(int k = 0; k < numChars - n; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> listChar = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if(start == -1) break;
            if(start + key.length() >= myText.length() - 1) break;
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            listChar.add(next);
            pos = start + key.length(); // + 1 might be useful in the future
        }
        return listChar;
    }
    public String toString() { return "MarkovModel of order " + n;}
}
