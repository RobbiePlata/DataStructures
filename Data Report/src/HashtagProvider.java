import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HashtagProvider {

    static String ConsumerKey = "8zL76Gdyo7a9sqBwwtYHfborw";
    static String ConsumerSecret = "hJQsVYHVGXRS4GG9Ltvcy3z6iplO9eE6dkHDSJEiO4IZp8FxB3";
    static String AccessToken = "40386305-GutswOZy2zeuh456LVDPSvla5NaSAmeJ9pBcXUHGM";
    static String TokenSecret = "xxBTB0suVRP4pj1RWMjqZKV7VvJ4PTdgXgLgYZEkFs2xT";

    public List<String> getHashtags(String searchTerm) throws TwitterException, IOException {

        List<String> tweetsAsListOfString = new ArrayList<String>();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(AccessToken)
                .setOAuthAccessTokenSecret(TokenSecret);
        QueryResult result;
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(searchTerm);
        query.setCount(1000); //tweets per query
        result = twitter.search(query);
        String output = "";

        for (Status status : result.getTweets()) {
            output += ("@" + status.getUser().getScreenName() + ":" + status.getText());
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        tweetsAsListOfString.add(output);
        return tweetsAsListOfString;
    }

    public List<String> getHashtagStrings(String t){
        List<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(t.trim(), " \t\n\r\f,.:;?![]'”“\"…");
        while (st.hasMoreTokens()) {
            String word = st.nextToken().toLowerCase(); // Each interpreted word is made lowercase
            words.add(word); // Each newly lowercase word is added to List
        }
        return words; // List is returned
    }
}