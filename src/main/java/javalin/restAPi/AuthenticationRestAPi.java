package javalin.restAPi;

import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import javalin.models.LoginRequest;
import javalin.models.LoginResponse;
import javalin.models.User;
import javalin.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;

public class AuthenticationRestAPi {
    UserService userService = new UserService();

    public void signup(Context ctx ){
    User toCreate = ctx.bodyAsClass(User.class);
    userService.createUser(toCreate);
    }
    public void login(Context ctx){
        LoginRequest toLogin = ctx.bodyAsClass(LoginRequest.class);
        boolean isAuth= userService.authenticate(toLogin.getUsername(),toLogin.getPassword());
        if(isAuth){
            LoginResponse response = new LoginResponse();
            String token = RandomStringUtils.random(30);
            userService.addToken(token, userService.getUserByUsername(toLogin.getUsername()));
            response.setToken(token);
            ctx.json(response);
        }
        else{
            throw new UnauthorizedResponse();
        }
    }
}
