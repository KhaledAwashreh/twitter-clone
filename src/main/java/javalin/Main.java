
package javalin;
import com.mitchellbosecke.pebble.PebbleEngine;

import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import io.javalin.http.staticfiles.Location;
import javalin.data.dao.TweetItemDAO;
import javalin.data.dao.UserDAO;
import javalin.data.repository.TweetItemRepositoryImpl;
import javalin.data.repository.UserRepositoryImpl;
import javalin.domain.repository.TweetItemRepository;
import javalin.domain.service.TweetItemService;
import javalin.domain.service.UserService;
import javalin.domain.service.impl.TweetItemServiceImpl;
import javalin.domain.service.impl.UserServiceImpl;
import javalin.presentation.controller.TweetItemController;
import javalin.presentation.controller.UserController;
import javalin.middleware.AuthenticationMiddleware;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinPebble;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        var app = Javalin.create(config -> {
            config.addStaticFiles("/static", Location.CLASSPATH);

        });


        Jdbi jdbi=Jdbi.create("jdbc:mariadb://localhost:3306/twitterdb?user=root&password=root");
        jdbi.installPlugin(new SqlObjectPlugin());
        TweetItemDAO tweetDao=jdbi.onDemand(TweetItemDAO.class);
        UserDAO userDao=jdbi.onDemand(UserDAO.class);


        TweetItemRepository tweetItemRepository=new TweetItemRepositoryImpl(tweetDao);
        TweetItemService tweetItemService=new TweetItemServiceImpl(tweetItemRepository);
        TweetItemController tweetItemController = new TweetItemController(tweetItemService);

        UserRepositoryImpl userRepository =new UserRepositoryImpl(userDao);
        UserService userService=new UserServiceImpl(userRepository);
        AuthenticationMiddleware authenticationController=new AuthenticationMiddleware(userService);
        UserController userController = new UserController(userService,authenticationController);
         app.routes(()->{
            path("/",()->{
                get("home", ctx -> ctx.render("page/home.peb"));
                get("login", ctx -> ctx.render("page/UsernameOrExternal.peb"));
                get("login2", ctx -> ctx.render("page/UsernameAndPassword.peb"));

            });
            path("/tweet",()->{
                get(tweetItemController::getAllTweets);
                post(tweetItemController::createTweetItem);
                path("{id}",()->{
                    get(tweetItemController::getTweetById);
                    put(tweetItemController::updateTweetItem);
                    delete(tweetItemController::deleteTweetItem);
                });
            });
            path("/user",()->{
                get(userController::returnALlUsers);
                path("{username}",()->{
                    get(userController::getUserByUsername);
                });
            });
            path("/auth",()->{
                path("/login",()->{
                    post(userController::login);
                });
                path("/signup",()->{
                    post(userController::signup);
                });
            });


        });
        setupJavalinPebble();
        app.start(8080);


    }
    private static void setupJavalinPebble() {
        Loader loader = new FileLoader();
        loader.setPrefix("src/main/resources/templates/pebble/");
        loader.setCharset("utf-8");
        PebbleEngine engine = new PebbleEngine.Builder()
                .loader(loader)
                .build();
        JavalinPebble.configure(engine);
    }



}