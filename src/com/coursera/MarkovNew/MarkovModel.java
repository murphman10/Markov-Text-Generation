package com.coursera.MarkovNew;

import com.coursera.Interfaces.IMarkovModel;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private int n;
    public MarkovModel(int num) {

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
    public String toString() { return "MarkovModel of order " + n;}

}
