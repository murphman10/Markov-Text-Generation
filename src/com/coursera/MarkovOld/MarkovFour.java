package com.coursera.MarkovOld;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
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
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);

        for(int k = 0; k < numChars - 4; k++) {
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
}
