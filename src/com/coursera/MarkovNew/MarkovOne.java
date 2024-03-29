package com.coursera.MarkovNew;

import com.coursera.Interfaces.IMarkovModel;

import java.util.ArrayList;
import java.util.Random;


public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        super(1);
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }
    public String toString() { return "MarkovModel of order 1";}
}