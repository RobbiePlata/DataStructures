import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    /*
    For this assignment you will use your data from the previous assignment. You will sort the collection of hashtags collected by your software.

    Implement the interface Compare.
    Provide two different implementations for CompareTo.

    For example, in one of them you sort the hastags ignoring upper case: #SECChampionship and #seccampionship will be identical;
    while in another case you sort first by length and for two strings of the same length you use lexicographical ordering:
    #iubb will be ahead of #BrunoMars (because the former's length is smaller than the latter's length) and #BrunoBars will be ahead of #BrunoMars.

    Implement one of the quadratic sorting algorithms discussed in class.

    You have four settings: two sorting algorithms (Java-Sorting and Your Sorting) and 2 CompareTo implementations.
    Take the cross product. Run your implementation of the four settings on your dataset. Record the running times.

    Provide an one-page brief report.
    mention the quadratic algorithm that you implemented
    a description of the two CompareTo methods
    a table where you show the times of the 4 settings described above
    */

    FileUtils fileUtils = new FileUtils();
    Sorting sorting = new Sorting();

    public static void main(String args[]) throws IOException {
        Main main = new Main();
        main.run();
    }

    public void run() throws IOException{
        String hashtagtxt = fileUtils.readFile("hashtags.txt");
        ArrayList<String> hashtaglist = fileUtils.wordsFrom(hashtagtxt);

        long start1 = System.nanoTime();
        ArrayList<String> javaSortedAlphabet = sorting.javaSortAlphabetically(hashtaglist);
        long end1 = System.nanoTime();
        long timeInMillis1 = TimeUnit.MILLISECONDS.convert(end1 - start1, TimeUnit.NANOSECONDS);
        sorting.printList(javaSortedAlphabet);

        long start2 = System.nanoTime();
        ArrayList<String> javaSortedLength = sorting.javaSortLength(hashtaglist);
        long end2 = System.nanoTime();
        long timeInMillis2 = TimeUnit.MILLISECONDS.convert(end2 - start2, TimeUnit.NANOSECONDS);
        sorting.printList(javaSortedLength);

        long start3 = System.nanoTime();
        ArrayList<String> bubbleSortedAlphabet = sorting.bubbleSortAlphabetically(hashtaglist);
        long end3 = System.nanoTime();
        long timeInMillis3 = TimeUnit.MILLISECONDS.convert(end3 - start3, TimeUnit.NANOSECONDS);
        sorting.printList(bubbleSortedAlphabet);

        long start4 = System.nanoTime();
        ArrayList<String> bubbleSortedLength = sorting.bubbleSortLength(hashtaglist);
        long end4 = System.nanoTime();
        long timeInMillis4 = TimeUnit.MILLISECONDS.convert(end4 - start4, TimeUnit.NANOSECONDS);
        sorting.printList(bubbleSortedLength);

        System.out.println("");
        System.out.println("First implementation of Compare is 'compareLetters' which returns a number > 0 when s1 > s2");
        System.out.println("Second implementation of Compare is 'compareLength' which compares the length of two integers returning a > 0 when i1 > i2");
        System.out.println("BubbleSort by lexicographical order uses the manual implementation of 'compareTo' which compares two strings and returns > 0 when s1 > s2");
        System.out.println("BubbleSort by length uses the manual implementation comparing String length with length() function");
        System.out.println("");
        System.out.println("Java-Sorting by Lexicographical Order: " + timeInMillis1 + " milliseconds");
        System.out.println("Java-Sorting by Length: " + timeInMillis2 + " milliseconds");
        System.out.println("BubbleSort by Lexicographical Order: " + timeInMillis3 + " milliseconds");
        System.out.println("BubbleSort by Length: " + timeInMillis4 + " milliseconds");
        System.out.println("");
        System.out.println("All Four Settings are printed");

    }
}