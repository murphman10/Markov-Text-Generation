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
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
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