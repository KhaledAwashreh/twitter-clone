package javalin.presentation.restController;

import io.javalin.http.NotFoundResponse;
import javalin.data.models.TweetItem;
import javalin.presentation.controller.TweetItemController;
import io.javalin.http.Context;

public class TweetItemRestApi {
    private final TweetItemController tweetController = new TweetItemController();
    public void get(Context ctx){
        ctx.json(tweetController.returnAllTweets());
    }
    public void getByID(Context ctx){
        TweetItem foundTweet = tweetController.getTweetById(ctx.pathParamAsClass("id",Long.class).get());
        if(foundTweet!=null) {
            ctx.json(foundTweet);
        }
        else {
          throw new NotFoundResponse();
        }
    }
    public void post(Context ctx){
        tweetController.createTweetItem(ctx.bodyAsClass(TweetItem.class));
        ctx.status(200);
    }
    public void put(Context ctx){
        tweetController.updateTweetItem(ctx.pathParamAsClass("id",Long.class).get(),ctx.bodyAsClass(TweetItem.class));
    }
    public void delete (Context ctx){
        tweetController.deleteTweetItem(ctx.pathParamAsClass("id",Long.class).get() );
        ctx.status(200);
    }
}
