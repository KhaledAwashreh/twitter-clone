package com.testing.javalin.repo;
import com.testing.javalin.models.TweetItem;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;

public class TweetItemRepo {

    private int tweetId=0;
    private final static List<TweetItem> tweetList = new ArrayList<>();
    static {
        tweetList.add(new TweetItem(0,"test"));
        tweetList.add(new TweetItem(1,"test2"));
        tweetList.add(new TweetItem(2    ,"test3"));
    }
    public TweetItemRepo(){}
    public List<TweetItem> returnAllTweets(){
        return tweetList;
    }
    public TweetItem getTweetById(long tweetId){
        return tweetList.stream().filter(tweet->tweet.getTweetID()==tweetId).findFirst().orElse(null);
    }
    public void updateTweetItem(long tweetId, TweetItem tweet){
    }
    public void createTweetItem(TweetItem tweetItem){
    }
    public void deleteTweetItem(long tweetId){
    }
    private long generateTweetId(){
        return tweetId++;
    }

}
