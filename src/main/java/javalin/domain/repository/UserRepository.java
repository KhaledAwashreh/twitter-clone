package javalin.domain.repository;
import javalin.data.dto.UserDto;

import java.util.List;

public interface UserRepository {
    public List<UserDto> returnAllUsers();
    public UserDto getTweetById(long tweetId);
    public void updateTweetItem(long tweetId, UserDto tweet);
    public void createTweetItem(UserDto tweetItem);
    public void deleteTweetItem(long tweetId);
}
