import java.util.ArrayList;
import java.util.Random;


public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
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
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> listChar = new ArrayList<>();
        for(int i = 0; i < myText.length() - 1; i++) {
            if(key.charAt(0) == myText.charAt(i)) {
                String following = String.valueOf(myText.charAt(i + 1));
                listChar.add(following);
            }
        }
        return listChar;
    }
}