package javalin.presentation.controller;
import javalin.data.dto.TweetItemDto;
import javalin.data.models.TweetItem;
import javalin.data.repository.TweetItemRepositoryImpl;
import javalin.domain.service.TweetItemService;

import java.util.List;
public class TweetItemController {

    private final TweetItemService tweetService;
    public TweetItemController(TweetItemService service){
        this.tweetService=service;
    };
    public List<TweetItemDto> returnAllTweets(){
        return tweetService.returnAllTweets();
    }
    public TweetItemDto getTweetById(long tweetId){
        return tweetService.getTweetById(tweetId);
    }
    public void updateTweetItem(TweetItemDto tweet){
        tweetService.updateTweetItem(tweet);
    }
    public void createTweetItem(TweetItemDto tweetItem){
        tweetService.createTweetItem(tweetItem);
    }
    public void deleteTweetItem(long tweetId){
        tweetService.deleteTweetItem(tweetId);
    }

}
