package javalin.presentation.restController;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import javalin.data.dto.TweetItemDto;
import javalin.data.dto.UserDto;
import javalin.data.dto.UserLoginDto;
import javalin.presentation.controller.TweetItemController;
import javalin.presentation.controller.UserController;

public class UserRestAPi {
    private final UserController userController;
    private final AuthenticationControllerProvider authorizationController;

    public UserRestAPi(UserController controller,AuthenticationControllerProvider authorization) {
        this.userController=controller;
        this.authorizationController=authorization;
    }


    public void get(Context ctx){
        ctx.json(userController.returnALlUsers());
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
    public void getUserByUsername(Context ctx){
        UserDto foundUser = userController.getUserById(ctx.pathParamAsClass("id",Long.class).get());
        if(foundUser!=null) {
            ctx.json(foundUser);
        }
        else {
            throw new NotFoundResponse();
        }
    }
    public void login(Context ctx){
        UserLoginDto user = ctx.bodyAsClass(UserLoginDto.class);
        UserDto foundUser = userController.getUserByUsername(user.getUsername());
        if(foundUser!=null) {
            if(authorizationController.login(user,foundUser)) {
                ctx.json(foundUser);
                ctx.status(200);
            }
            else
                throw new UnauthorizedResponse();
        }
        else {
            throw new NotFoundResponse();
        }
    }
    public void signup(Context ctx){
        authorizationController.signup(ctx.bodyAsClass(UserDto.class));
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
