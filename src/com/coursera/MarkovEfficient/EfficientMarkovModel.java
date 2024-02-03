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
