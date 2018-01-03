import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Sorting{

    public ArrayList<String> javaSortAlphabetically(ArrayList<String> hashtags) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(hashtags);
        Collections.sort(list, new CompareLetters());
        return list;
    }

    public ArrayList<String> javaSortLength(ArrayList<String> hashtags){
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(hashtags);
        Collections.sort(list, new CompareLength());
        return list;
    }

    public ArrayList<String> bubbleSortLength(ArrayList<String> hashtags){
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(hashtags);
        boolean go = true;
        for(int i = list.size() - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                if(list.get(j).length() > (list.get(j + 1)).length()) {
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    public ArrayList<String> bubbleSortAlphabetically(ArrayList<String> hashtags){
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(hashtags);
        boolean go = true;
        for(int i = list.size() - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                if(list.get(j).compareToIgnoreCase(list.get(j + 1)) > 0) {
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    public void printList(ArrayList<String> list){
        for(String words : list){
            System.out.println(words);
        }
    }

}