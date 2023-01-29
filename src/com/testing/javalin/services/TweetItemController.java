package com.testing.javalin.services;
import com.testing.javalin.models.TweetItem;
import com.testing.javalin.repo.TweetItemRepo;
import io.javalin.http.Context;
import java.util.List;
public class TweetItemController {
    private TweetItemRepo repo= new TweetItemRepo();
    public TweetItemController(){};
    public List<TweetItem> returnAllTweets(){
        return repo.returnAllTweets();
    }
    public TweetItem getTweetById(long tweetId){
        return repo.getTweetById(tweetId);
    }
    public void updateTweetItem(long tweetId, TweetItem tweet){
        repo.updateTweetItem(tweetId,tweet);
    }
    public void createTweetItem(TweetItem tweetItem){
        repo.createTweetItem(tweetItem);
    }
    public void deleteTweetItem(long tweetId){
        repo.deleteTweetItem(tweetId);
    }

}
