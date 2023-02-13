package javalin.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
@Data
@NoArgsConstructor
public class TweetItemDto {
    @NonNull
    private String tweetContent;
    @NonNull
    private Date tweetDate;
    @NonNull
    private long tweetId;
    @NonNull
    private long tweetOwnerId;
}