import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.HashtagEntity;
import twitter4j.*;
    
public class Structures {
    public ArrayList<String> users;
    public int numHashtagTweets;
    public ArrayList<String> storedTweets;
    public LinkedList<String> hashTags = new LinkedList<String>();
    public QueryResult result;
    
    static String ConsumerKey = "";
    static String ConsumerSecret = "";
    static String AccessToken = "";
    static String TokenSecret = "";
    
    // Searches Tweets then returns the Query Result
    public String searchWord(String searchTerm) throws TwitterException{
        ArrayList<String> users = new ArrayList();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(ConsumerKey)
            .setOAuthConsumerSecret(ConsumerSecret)
            .setOAuthAccessToken(AccessToken)
            .setOAuthAccessTokenSecret(TokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(searchTerm);
        query.setCount(100); //tweets per query
        QueryResult result = twitter.search(query);
        this.result = result;
        String output = "";
        for (twitter4j.Status status : this.result.getTweets()){
            output += ("@" + status.getUser().getScreenName() + ":" + status.getText());
            String user = status.getUser().getScreenName();
            users.add(user);
        }
        this.users = users;
        return output;
    } 
    
    public String queryTweets(String hashtag){
        storedTweets = getStoredTweets();
        hashTags = getHashTags();
        String output = "";
        for(int i = 0; i < storedTweets.size(); i++){
            if (hashtag.equals(hashTags.get(i))){
                output += (storedTweets.get(i));
            }            
        }
        return output;
        
    }
    
    //To continuously add data to the data structures without resetting them, just de-comment the getters. Then change aggregate HashTag function
    public void addHashTags(QueryResult result){
        //LinkedList hashtaglist = getHashTags();
        //ArrayList storedtweets = getStoredTweets();
        LinkedList<String> hashtaglist = new LinkedList();
        ArrayList<String> storedtweets = new ArrayList();
        for(Status status : result.getTweets()){
            for(twitter4j.HashtagEntity str : status.getHashtagEntities()){
                hashtaglist.add(str.getText()); 
                String output = (status.getUser().getScreenName() + ":" + status.getText());
                storedtweets.add(output); //will add tweet that has hashtag                
            }
        }
        this.hashTags = hashtaglist;
        this.storedTweets = storedtweets;
    }
    
    public String aggregateHashTags(){
        Set<String> unique = new HashSet<String>(hashTags);
        String output = "";
        for (String key : unique){
            output += ("#" + key + ": " + Collections.frequency(hashTags, key));
            output += (System.lineSeparator());
        }
        return output;
    }
        
    public ArrayList<String> getStoredTweets(){
        return storedTweets;
    }
    
    public LinkedList<String> getHashTags(){
        return hashTags;
    }
}

