package javalin.presentation.restController;

import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import javalin.data.models.LoginRequest;
import javalin.data.models.LoginResponse;
import javalin.data.models.User;
import javalin.presentation.controller.UserController;
import org.apache.commons.lang3.RandomStringUtils;

public class AuthenticationRestAPi {
    UserController userController = new UserController();

    public void signup(Context ctx ){
    User toCreate = ctx.bodyAsClass(User.class);
    userController.createUser(toCreate);
    }
    public void login(Context ctx){
        LoginRequest toLogin = ctx.bodyAsClass(LoginRequest.class);
        boolean isAuth= userController.authenticate(toLogin.getUsername(),toLogin.getPassword());
        if(isAuth){
            LoginResponse response = new LoginResponse();
            String token = RandomStringUtils.randomAlphanumeric(30);
            userController.addToken(token, userController.getUserByUsername(toLogin.getUsername()));
            response.setToken(token);
            ctx.json(response);
        }
        else{
            throw new UnauthorizedResponse();
        }
    }
}
