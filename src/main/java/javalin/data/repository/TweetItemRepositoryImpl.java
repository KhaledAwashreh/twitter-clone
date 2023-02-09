package javalin.data.repository;
import javalin.data.dao.TweetItemDAO;
import javalin.data.dao.UserDAO;
import javalin.data.dto.TweetItemDto;
import javalin.data.models.TweetItem;
import javalin.domain.repository.TweetItemRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetItemRepositoryImpl implements TweetItemRepository {

    private TweetItemDAO tweetItemDao;
    private int tweetId=0;
    private final static Map<Long, TweetItem> tweetList = new HashMap<Long, TweetItem>();
    public TweetItemRepositoryImpl(TweetItemDAO dao){
        this.tweetItemDao=dao;
    }
    public List<TweetItemDto> returnAllTweets(){
        return tweetItemDao.getAll();
    }
    public TweetItemDto getTweetById(long tweetId){
        try {
            return tweetItemDao.get(tweetId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTweetItem(long tweetId, TweetItemDto tweet){
        try {
            tweetItemDao.update(tweet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTweetItem(TweetItemDto tweetItem){
        try {
            tweetItemDao.create(tweetItem);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTweetItem(long tweetId){
        tweetList.remove(tweetId);
    }
    private long getNextId(){
        tweetId++;
        return tweetId;
    }

}
