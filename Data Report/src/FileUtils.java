import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileUtils {

    public void writeFile(List<String> list) throws IOException {
        File file = new File("diskoutput.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuffer sb = new StringBuffer();
        for(String lines : list){
            sb.append(lines);
            sb.append(System.getProperty("line.separator"));
        }
        bw.write(sb.toString());
        bw.close();
    }

    public String readFile(String path) throws IOException {
        String str;
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String polynomial = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public ArrayList<String> wordsFrom(String t){
        ArrayList<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(t.trim(), " \t\n\r\f,.:;?![]'”“\"…");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            words.add(word);
        }
        return words;
    }

}