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
}