package javalin.presentation.view;


import io.javalin.http.Context;
import javalin.data.dto.UserDto;
import javalin.data.dto.UserLoginDto;
import javalin.domain.service.UserService;
import javalin.presentation.model.LoginPage;


import java.util.Map;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

public class LoginView {

    private final UserService userService;
    public LoginView(UserService service){
        this.userService=service;
    }
    public void checkUsernameAndRoute(Context ctx){
        UserLoginDto userlogin=null ;
        LoginPage model = new LoginPage();
        if(ctx.contentLength()!=-1) {
            userlogin=new UserLoginDto();
            userlogin.setUsername(ctx.formParam("username"));
        }
        if(userlogin!=null) {
            model= generatePageFromUserInfo(userlogin);
        }
        ctx.render("page/UsernameOrExternal.peb", asMap(model));
    }

    public void checkUsernameAndPassword(Context ctx){
        UserLoginDto userlogin=new UserLoginDto();
            userlogin.setUsername(ctx.formParam("username"));
            userlogin.setPassword(ctx.formParam("password"));
        if(userService.getUserByUsername(userlogin.getUsername()).getPassword().equals(userlogin.getPassword()))
            ctx.redirect("/home");
    }

    public static Map<String, Object> asMap(Object data){
        return model("model",data);
    }

    public LoginPage generatePageFromUserInfo(UserLoginDto loginDto){
        LoginPage model= new LoginPage();
        UserDto foundUser=null;
        if(loginDto!=null){
            foundUser = userService.getUserByUsername(loginDto.getUsername());
            if(foundUser!=null) {
                model.setUsername(foundUser.getUsername());
                model.setUserExists(true);
                model.setHasUsername(true);
            }
            else {
                model.setUserExists(false);
                model.setHasUsername(true);
            }
        }
        return model;

    }




}
