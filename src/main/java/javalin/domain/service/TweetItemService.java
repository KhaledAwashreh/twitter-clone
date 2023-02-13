package javalin.domain.service;

import javalin.data.dto.TweetItemDto;

import java.util.List;

public interface TweetItemService {


    public List<TweetItemDto> returnAllTweets();
    public TweetItemDto getTweetById(long tweetId);
    public void updateTweetItem(TweetItemDto tweet);
    public void createTweetItem(TweetItemDto tweetItem);
    public void deleteTweetItem(long tweetId);
}
