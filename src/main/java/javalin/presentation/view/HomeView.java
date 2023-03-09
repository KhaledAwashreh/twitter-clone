package javalin.presentation.view;

import io.javalin.http.Context;
import javalin.data.dto.TweetItemDto;
import javalin.domain.service.TweetItemService;
import javalin.domain.service.UserService;
import javalin.presentation.model.DisplayedTweet;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

public class HomeView {

    private final UserService userService;
    private final TweetItemService tweetService;

    public HomeView(UserService userService, TweetItemService tweetService){
        this.userService=userService;
        this.tweetService=tweetService;
    }
    public void loadHomePage(Context ctx){

        List<DisplayedTweet> displayedTweetList= prepareTweetsToBeDisplayed();
        ctx.render("page/home.peb",asMap(displayedTweetList));
    }


    public void createATweet(Context ctx){
        String tweetContent= ctx.formParam("tweetBox");
        //String tweetOwnerName=ctx.formParam("username");
        long tweetOwnerId = userService.getUserByUsername("khaledTest1").getUserId();
        Date tweetDate = new Date();
        long tweetId = (long) Math.random();
        TweetItemDto newTweet = new TweetItemDto();
        newTweet.setTweetId(tweetId);
        newTweet.setTweetDate(tweetDate);
        newTweet.setTweetOwnerId(tweetOwnerId);
        newTweet.setTweetContent(tweetContent);
        tweetService.createTweetItem(newTweet);
        loadHomePage(ctx);
    }
    public static Map<String, Object> asMap(Object data){
        return model("tweets",data);
    }

    public List<DisplayedTweet> prepareTweetsToBeDisplayed(){
        List<TweetItemDto> tweetList=tweetService.returnAllTweets();
        List<DisplayedTweet> displayedTweetList = new ArrayList<>();


        for(TweetItemDto tweet:tweetList){
            DisplayedTweet newTweet = new DisplayedTweet();
            newTweet.setTweetContent(tweet.getTweetContent());
            newTweet.setTweetOwnerName(userService.getUserById(tweet.getTweetOwnerId()).getUsername());
            displayedTweetList.add(newTweet);
        }
        return displayedTweetList;
    }
}

