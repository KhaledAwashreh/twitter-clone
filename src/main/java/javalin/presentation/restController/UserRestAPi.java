package javalin.presentation.restController;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import javalin.data.dto.TweetItemDto;
import javalin.data.dto.UserDto;
import javalin.presentation.controller.TweetItemController;
import javalin.presentation.controller.UserController;

public class UserRestAPi {
    private final UserController userController;

    public UserRestAPi(UserController controller) {
        this.userController=controller;
    }

    public void get(Context ctx){
        ctx.json(userController);
    }
    public void getByID(Context ctx){
        UserDto foundUser = userController.getUserById(ctx.pathParamAsClass("id",Long.class).get());
        if(foundUser!=null) {
            ctx.json(foundUser);
        }
        else {
            throw new NotFoundResponse();
        }
    }
    public void post(Context ctx){
        userController.createUser(ctx.bodyAsClass(UserDto.class));
        ctx.status(200);
    }
    public void put(Context ctx){
        userController.updateUser(ctx.bodyAsClass(UserDto.class));
    }
    public void delete (Context ctx){
        userController.deleteUser(ctx.pathParamAsClass("id",Long.class).get() );
        ctx.status(200);
    }
}
