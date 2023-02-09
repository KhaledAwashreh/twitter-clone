package javalin.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
@Data
@NoArgsConstructor
public class TweetItemDto {
    @NonNull
    private String tweet_content;
    @NonNull
    private Date tweet_date;
    @NonNull
    private long tweet_id;
    @NonNull
    private long tweet_owner_id;
}