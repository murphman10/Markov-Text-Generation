import com.coursera.MarkovWord.MarkovWordOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MarkovWordOneTest {

    String text = "this is just a test yes this is a simple test";
    Random myRandom = new Random(1);
    MarkovWordOne markovWordOne;

    @BeforeEach
    void setMarkovWordOne() {
        markovWordOne = new MarkovWordOne();
    }
    @Test
    void testIndexOf1() {
        String[] words = text.split(" ");
        assertEquals(0,indexOf(words, "this", 0));

    }

    @Test
    void testIndexOf2() {
        String[] words = text.split(" ");

        assertEquals(3,indexOf(words, "this", 3));
    }

    @Test
    void testIndexOf3() {
        String[] words = text.split(" ");
        assertEquals(0,indexOf(words, "frog", 0));
    }

    @Test
    void testIndexOf4() {
        String[] words = text.split(" ");
        assertEquals(5,indexOf(words, "test", 4));
    }

    @Test
    void testIndexOf5() {
        String[] words = text.split(" ");
        assertEquals(-1,indexOf(words, "transform", 4));
    }

    @Test
    void testGetFollows() {
        assertEquals("yes", getRandomText(1));
    }

    public int indexOf(String[] words, String target, int start) { //make private when done testing

        for(int k = start; k < words.length; k++) {
            if(words[k].equals(target)) {
                return k;
            }
        }

        return -1;
    }
    public String getRandomText(int numWords){
        String[] myText = text.split(" ");
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    public ArrayList<String> getFollows(String key) { // make private when done testing
        String[] myText = text.split(" ");
        ArrayList<String> listChar = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText,key, pos);
            if(start == -1) break;
            if(start + key.length() >= myText.length - 1) break;
            String next = myText[start + 1]; //using 1 word to predict the next word
            listChar.add(next);
            pos = start + key.length(); // + 1 might be useful in the future
        }
        return listChar;
    }


}
