
package javalin;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.DelegatingLoader;
import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import io.javalin.http.staticfiles.Location;
import javalin.data.dao.TweetItemDAO;
import javalin.data.dao.UserDAO;
import javalin.data.dto.TweetItemDto;
import javalin.data.dto.UserDto;
import javalin.data.repository.TweetItemRepositoryImpl;
import javalin.data.repository.UserRepositoryImpl;
import javalin.domain.repository.TweetItemRepository;
import javalin.domain.service.TweetItemService;
import javalin.domain.service.UserService;
import javalin.domain.service.impl.TweetItemServiceImpl;
import javalin.domain.service.impl.UserServiceImpl;
import javalin.presentation.controller.TweetItemController;
import javalin.presentation.controller.UserController;
import javalin.presentation.restController.AuthenticationControllerProvider;
import javalin.presentation.restController.TweetItemRestApi;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinPebble;
import javalin.presentation.restController.UserRestAPi;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        var app = Javalin.create(config -> {

            config.addStaticFiles("src/main/resources/webapp", Location.EXTERNAL);
            config.addSinglePageRoot("/","src/main/resources/webapp", Location.EXTERNAL);
        });


        Jdbi jdbi=Jdbi.create("jdbc:mariadb://localhost:3306/twitterdb?user=root&password=root");
        jdbi.installPlugin(new SqlObjectPlugin());
        TweetItemDAO tweetDao=jdbi.onDemand(TweetItemDAO.class);
        UserDAO userDao=jdbi.onDemand(UserDAO.class);


        TweetItemRepository tweetItemRepository=new TweetItemRepositoryImpl(tweetDao);
        TweetItemService tweetItemService=new TweetItemServiceImpl(tweetItemRepository);
        TweetItemController tweetItemController = new TweetItemController(tweetItemService);
        TweetItemRestApi tweetItems= new TweetItemRestApi(tweetItemController);

        UserRepositoryImpl userRepository =new UserRepositoryImpl(userDao);
        UserService userService=new UserServiceImpl(userRepository);
        UserController userController = new UserController(userService);
        AuthenticationControllerProvider authenticationController=new AuthenticationControllerProvider(userController);
        UserRestAPi users= new UserRestAPi(userController,authenticationController);

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

            path("/user",()->{
                get(users::get);
            });
            path("/auth",()->{
                path("/login",()->{
                    post(users::login);
                });
                path("/signup",()->{
                    post(users::signup);
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