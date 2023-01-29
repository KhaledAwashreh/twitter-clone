
package org.example;
import com.testing.javalin.models.TweetItem;
import com.testing.javalin.restAPi.TweetItemRestApi;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.apibuilder.ApiBuilder.delete;



public class Main {
    public static void main(String[] args) {


        var app = Javalin.create().start();
        TweetItemRestApi tweetItems= new TweetItemRestApi();
        app.routes(()->{
            path("/tweet",()->{
                get(tweetItems::get);
                post(tweetItems::post);
                path("{id}",()->{
                    get(tweetItems::getByID);
                    post(tweetItems::put);
                    delete(tweetItems::delete);
                });
            });
        });
        app.get("/tweet",tweetItems::get);
        app.get("/tweet/{id}",tweetItems::getByID);
    }
}