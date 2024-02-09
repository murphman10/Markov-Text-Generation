package com.coursera.MarkovEfficient;

import com.coursera.MarkovNew.AbstractMarkovModel;

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int n;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int num) {
        map = new HashMap<String, ArrayList<String>>();
        n = num;
    }
    public void setTraining(String s){

        super.setTraining(s);
        map = buildMap();
        printHashMapInfo();
    }

    @Override
    public String getRandomText(int numChars) {
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

    private HashMap<String,ArrayList<String>> buildMap() {
        HashMap<String, ArrayList<String>> mapper = new HashMap<>();
        return mapper;
    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }

    public void printHashMapInfo() {
        System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
    }
    public int mapSize() {
        return map.size();
    }

    public int longestFollowsSize() {
        int maxSize = 0;
        for (String key : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(key).size());
        }

        return maxSize;
    }
    public String toString() { return "MarkovModel of order " + n;}
}
