package javalin.data.repo;
import javalin.data.models.TweetItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetItemRepo {

    private int tweetId=0;
    private final static Map<Long, TweetItem> tweetList = new HashMap<Long, TweetItem>();
    static {
        tweetList.put((long)0,new TweetItem(0,"test"));
        tweetList.put((long)1,new TweetItem(1,"test2"));
        tweetList.put((long)(2),new TweetItem(2 ,"test3"));
    }
    public TweetItemRepo(){}
    public List<TweetItem> returnAllTweets(){
        return new ArrayList<>(tweetList.values());
    }
    public TweetItem getTweetById(long tweetId){
        TweetItem found = tweetList.get(tweetId);
        return tweetList.get(tweetId);
    }
    public void updateTweetItem(long tweetId, TweetItem tweet){
    }
    public void createTweetItem(TweetItem tweetItem){
        tweetItem.setTweetID(getNextId());
        tweetList.put(getNextId(),tweetItem);
    }
    public void deleteTweetItem(long tweetId){
        tweetList.remove(tweetId);

    }
    private long getNextId(){

        tweetId++;
        return tweetId;
    }

}
