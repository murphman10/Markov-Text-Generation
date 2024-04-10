//package com.coursera.MarkovOld;
//
//import edu.duke.FileResource;
//
//import java.util.ArrayList;
//
//public class Tester {
//
//    private String getText() {
//        FileResource fr = new FileResource("data/confucius.txt");
//        String st = fr.asString();
//        st = st.replace('\n', ' ');
//        return st;
//    }
//    @SuppressWarnings("unused")
//    private void testGetFollows(int order) {
//
//        MarkovModel markov = new MarkovModel(order);
//        System.out.printf("Markov model order: %d\n", order);
//        String st = "this is a test yes this is a test.";
//        System.out.println(st);
//        markov.setRandom(150);
//        markov.setTraining(st);
//
//        for (int i=0; i<st.length()-order; i++) {
//
//            String key = st.substring(i, i+order);
//            ArrayList<String> follows = markov.getFollows(key);
//            System.out.printf("%s\t%s\n", key, follows);
//
//        }
//
//    }
//
//    private void testGetFollowsWithFile() {
//
//        MarkovModel markov = new MarkovModel(1);
//
//        String st = getText();
//
//        System.out.println(st);
//        markov.setRandom(150);
//        markov.setTraining(st);
//        int tcnt = markov.getFollows("he").size();
//        System.out.println("Size: " + tcnt);
//
//
//
//    }
//
//    public static void main(String[] args) {
//
//        Tester t = new Tester();
////		t.testGetFollows(4);
//        t.testGetFollowsWithFile();
//    }
//}