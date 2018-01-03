import java.util.Comparator;

public class CompareLength implements Comparator<String>{

    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }

}
