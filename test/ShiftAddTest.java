import com.coursera.WordGram.WordGram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShiftAddTest {
    WordGram wordGram;
    String[] arr = {"this", "is", "a", "test"};
    @BeforeEach
    void setup() {wordGram = new WordGram(arr, 0, 4);}

    @Test
    void testShiftAdd() {
        wordGram.shiftAdd("yes");

    }

}
