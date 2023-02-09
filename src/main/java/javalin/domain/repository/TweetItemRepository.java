package javalin.domain.repository;

import javalin.data.dto.TweetItemDto;
import java.util.List;

public interface TweetItemRepository {

    public List<TweetItemDto> returnAllTweets();
    public TweetItemDto getTweetById(long tweetId);
    public void updateTweetItem(long tweetId, TweetItemDto tweet);
    public void createTweetItem(TweetItemDto tweetItem);
    public void deleteTweetItem(long tweetId);
}
