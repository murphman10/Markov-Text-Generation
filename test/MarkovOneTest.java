import edu.duke.FileResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MarkovOneTest {
    MarkovOne markovOne;

    @BeforeEach
    void setup() {
        markovOne = new MarkovOne();
    }

    @Test
    void getFollows() {
        String myText = "this is a test yes this is a test.";
        ArrayList<String> result = new ArrayList<>(Arrays.asList("h", "e", " ", "h", "e", "."));
        markovOne.setTraining(myText);
        assertEquals(result, markovOne.getFollows("t"));
        System.out.println(markovOne.getFollows("t"));

    }
    @Test
    void getFollowsTestWithFile() {
        FileResource fr = new FileResource("C:/Users/Asa/Documents/Code/Coursera/GeneratingRandomText/data/confucius.txt");
        String st = fr.asString();
        markovOne.setTraining(st);
        assertEquals(11548, markovOne.getFollows("t").size());
        System.out.println(markovOne.getFollows("t").size());
    }
}