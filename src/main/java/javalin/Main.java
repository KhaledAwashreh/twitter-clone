
package javalin;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.DelegatingLoader;
import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import io.javalin.http.staticfiles.Location;
import javalin.restAPi.TweetItemRestApi;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinPebble;


import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {


        var app = Javalin.create(config -> {

            config.addStaticFiles("src/main/resources/webapp", Location.EXTERNAL);
        });

        TweetItemRestApi tweetItems= new TweetItemRestApi();

        app.routes(()->{
            path("/tweet",()->{
                get(tweetItems::get);
                post(tweetItems::post);
                path("{id}",()->{
                    get(tweetItems::getByID);
                    put(tweetItems::put);
                    delete(tweetItems::delete);
                });
            });
        });
        //setupJavalinPebble();
        app.start(8080);
    }


    private static void setupJavalinPebble() {
        boolean isProduction = Boolean.parseBoolean(System.getProperty("production", "false"));
        Loader loader = isProduction ? new DelegatingLoader(List.of(new ClasspathLoader(), new FileLoader())) : new FileLoader();
        loader.setPrefix(isProduction ? "templates/pebble/" : "src/main/resources/templates/pebble/");
        loader.setCharset("utf-8");
        PebbleEngine engine = new PebbleEngine.Builder()
                .loader(loader)
                .cacheActive(isProduction)
                .build();
        JavalinPebble.configure(engine);

    }
}