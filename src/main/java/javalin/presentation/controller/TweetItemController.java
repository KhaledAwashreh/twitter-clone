package javalin.presentation.controller;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import javalin.data.dto.TweetItemDto;
import javalin.data.models.TweetItem;
import javalin.data.repository.TweetItemRepositoryImpl;
import javalin.domain.service.TweetItemService;

import java.util.List;
public class TweetItemController {
    private final TweetItemService tweetService;
    public TweetItemController(TweetItemService service){
        this.tweetService=service;
    };
    public void getAllTweets(Context ctx){
        ctx.json(tweetService.returnAllTweets());
        ctx.status(200);

    }
    public void getTweetById(Context ctx){
        TweetItemDto foundTweet = tweetService.getTweetById(ctx.pathParamAsClass("id",Long.class).get());
        if(foundTweet!=null) {
            ctx.json(foundTweet);
            ctx.status(200);

        }
        else {
            throw new NotFoundResponse();
        }
    }
    public void updateTweetItem(Context ctx){
        tweetService.updateTweetItem(ctx.bodyAsClass(TweetItemDto.class));
        ctx.status(200);
    }
    public void createTweetItem(Context ctx){
        tweetService.createTweetItem(ctx.bodyAsClass(TweetItemDto.class));
        ctx.status(200);

    }
    public void deleteTweetItem (Context ctx){
        tweetService.deleteTweetItem(ctx.pathParamAsClass("id",Long.class).get());
        ctx.status(200);
    }
}
