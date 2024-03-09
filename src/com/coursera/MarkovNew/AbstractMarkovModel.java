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
    
    public AbstractMarkovModel(int n) {
        myRandom = new Random();
        this.n = n;
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom.setSeed(seed);
    }

    //abstract public String getRandomText(int numChars);

    private boolean matchKey(String key, int pos) {
        return key.equals(myText.substring(pos, pos + n));
    }

    private String getFollowingLetter(int pos) {
        int index = pos + n;
        return myText.substring(index, index + 1);
    }

    public ArrayList<String> getFollows(String key) {

        ArrayList<String> follows = new ArrayList<String>();

        for (int i = 0; i < myText.length() - n; i++) {
            // if found key, add letter after the key to the list
            // key length = order
            if (matchKey(key, i))
                follows.add(getFollowingLetter(i));
        }

        return follows;

    }

    abstract public String getRandomText(int numChars);

    @Override
    public String toString() {
        return "Markov Model order " + n;
    }

}
