import java.util.Comparator;

public class CompareLetters implements Comparator<String> {

    public int compare(String s1, String s2){
        return (s1.toLowerCase().compareTo(s2.toLowerCase()));
    }

}