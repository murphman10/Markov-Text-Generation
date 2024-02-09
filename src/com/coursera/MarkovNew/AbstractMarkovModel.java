package com.coursera.MarkovNew;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import com.coursera.Interfaces.IMarkovModel;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int n;
    
    public AbstractMarkovModel() {}

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
