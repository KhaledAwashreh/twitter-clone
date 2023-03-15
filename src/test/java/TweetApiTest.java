import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJackson;
import javalin.TwitterApp;
import javalin.data.dto.TweetItemDto;
import javalin.presentation.controller.TweetItemController;
import org.junit.jupiter.api.Test;
import io.javalin.testtools.JavalinTest;
import java.sql.SQLException;
import java.util.Date;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TweetApiTest {
    TwitterApp twitter = new TwitterApp();
    Javalin app;
    private String tweetJson;
    public TweetApiTest(){
        try {
            twitter.initalizeApp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        app=twitter.getApp();
        tweetJson= new JavalinJackson().toJsonString(twitter.getTweetItemService().returnAllTweets());

    }

    @Test
    public void GET_to_fetch_tweet_returns_list_of_tweets() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("localhost:8080/tweet").code()).isEqualTo(200);
            assertThat(client.get("localhost:8080/tweet").body().string()).isEqualTo(tweetJson);
        });
    }

}




