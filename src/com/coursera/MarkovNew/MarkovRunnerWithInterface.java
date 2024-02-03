package com.coursera.MarkovNew;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import com.coursera.Interfaces.IMarkovModel;
import com.coursera.MarkovEfficient.EfficientMarkovModel;
import edu.duke.FileResource;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int sd = 42;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, sd);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, sd);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, sd);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, sd);

    }

	public void testHashMap() {

		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		//st = st.replace('\n', ' ');
		int size = 50;
		int sd = 42;

		EfficientMarkovModel emm = new EfficientMarkovModel(3);
		runModel(emm,st, size, sd);
	}

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	public static void main(String[] args) {
		MarkovRunnerWithInterface mri = new MarkovRunnerWithInterface();
		mri.testHashMap();
	}
	
}
