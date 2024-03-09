package com.coursera.MarkovEfficient;

import com.coursera.MarkovNew.AbstractMarkovModel;

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private HashMap<String, ArrayList<String>> map;
    public EfficientMarkovModel(int n) {
        super(n);
        map = new HashMap<String, ArrayList<String>>();
    }

    private String getKey(int index) {
        return myText.substring(index, index + n);
    }

    private String getFollowingLetter(int index) {
        return myText.substring(index+n, index+n+1);
    }

    public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();

    }

    private void buildMap() {
        for (int i = 0; i < myText.length() - n; i++) {
            String key = getKey(i);
            String follow = getFollowingLetter(i);

            if (map.containsKey(key)) {
                map.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(key, list);
            }
        }

    }
    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for (int k = 0; k < numChars - n; k++) {

            ArrayList<String> follows =getFollows(key);

            if (follows == null) break;

            String followsRandom = follows.get(myRandom.nextInt(follows.size()));

            sb.append(followsRandom);
            key = key.substring(1) + followsRandom;

        }

        return sb.toString();
    }
    public void printHashMapInfo() {
        System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
//		for (String key : map.keySet()) {
//			System.out.printf("Key:\t[%s]\tvalues: ", key);
//			System.out.println(map.get(key));
//		}

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

    @Override
    public String toString() {
        return "Efficient Markov Model order " + n;
    }
}
