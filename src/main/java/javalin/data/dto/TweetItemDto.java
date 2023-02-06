package javalin.data.dto;

public class TweetItemDto {
    private String tweetText;
    private long tweetId;

    public TweetItemDto(String tweetText) {
        this.tweetText = tweetText;
    }
    public String getTweetText() {
        return tweetText;
    }
    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }
}
