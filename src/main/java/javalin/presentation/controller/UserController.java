package javalin.presentation.controller;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import javalin.data.dto.UserDto;
import javalin.data.dto.UserLoginDto;
import javalin.domain.service.UserService;
import javalin.middleware.AuthenticationMiddleware;

public class UserController {
    private final UserService  userService;
    private final AuthenticationMiddleware authorizationMiddleware;

    public UserController(UserService userService,AuthenticationMiddleware authorization ) {
        this.authorizationMiddleware=authorization;
        this.userService = userService;
    }
    public void returnAllUsers(Context ctx){
        ctx.json(userService.returnAllUsers());
    }
    public void getUserById(Context ctx){
        UserDto foundUser = userService.getUserById(ctx.pathParamAsClass("id",Long.class).get());
        if(foundUser!=null) {
            ctx.json(foundUser);
        }
        else {
            throw new NotFoundResponse();
        }
    }
    public UserDto getUserByUsername(Context ctx){
        UserDto foundUser = userService.getUserByUsername(ctx.pathParamAsClass("username",String.class).get());
        if(foundUser!=null) {
            ctx.json(foundUser);
        }
        else {
            throw new NotFoundResponse();
        }
        return foundUser;
    }
    public void login(Context ctx){
        UserLoginDto user = ctx.bodyAsClass(UserLoginDto.class);
        UserDto foundUser = userService.getUserByUsername(user.getUsername());
        if(foundUser!=null) {
            if(authorizationMiddleware.login(user,foundUser)) {
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
        authorizationMiddleware.signup(ctx.bodyAsClass(UserDto.class));
        ctx.status(200);
    }
    public void updateUser(Context ctx){
        userService.updateUser(ctx.bodyAsClass(UserDto.class));
    }
    public void deleteUser (Context ctx){
        userService.deleteUser(ctx.pathParamAsClass("id",Long.class).get() );
        ctx.status(200);
    }
}
