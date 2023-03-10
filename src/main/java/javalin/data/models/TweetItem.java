package javalin.data.models;

public class TweetItem {
    private long tweetID;
    private String tweetContent;

    public TweetItem(){

    }
    public TweetItem(long tweetID, String tweetContent) {
        this.tweetID = tweetID;
        this.tweetContent = tweetContent;
    }

    public long getTweetID() {
        return tweetID;
    }

    public void setTweetID(long tweetID) {
        this.tweetID = tweetID;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }



}
