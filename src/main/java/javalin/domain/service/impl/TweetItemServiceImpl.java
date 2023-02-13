package javalin.domain.service.impl;

import javalin.data.dto.TweetItemDto;
import javalin.domain.repository.TweetItemRepository;
import javalin.domain.service.TweetItemService;

import java.util.List;

public class TweetItemServiceImpl implements TweetItemService {

    private final TweetItemRepository tweetRepository;

    public TweetItemServiceImpl(TweetItemRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<TweetItemDto> returnAllTweets() {
        return tweetRepository.returnAllTweets();
    }

    @Override
    public TweetItemDto getTweetById(long tweetId) {
        return tweetRepository.getTweetById(tweetId);
    }

    @Override
    public void updateTweetItem(TweetItemDto tweet) {
        tweetRepository.updateTweetItem(tweet);

    }

    @Override
    public void createTweetItem(TweetItemDto tweetItem) {
        tweetRepository.createTweetItem(tweetItem);

    }

    @Override
    public void deleteTweetItem(long tweetId) {
        tweetRepository.deleteTweetItem(tweetId);
    }
}
