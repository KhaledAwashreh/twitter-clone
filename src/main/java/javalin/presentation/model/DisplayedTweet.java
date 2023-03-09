package javalin.presentation.model;

import javalin.data.dto.TweetItemDto;
import javalin.data.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
    public class DisplayedTweet {
    private String tweetContent;
    private String tweetOwnerName;
}
