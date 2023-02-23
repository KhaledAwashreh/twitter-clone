package javalin.middleware;

import javalin.data.dto.UserDto;
import javalin.data.dto.UserLoginDto;
import javalin.data.models.User;
import javalin.domain.service.UserService;
import javalin.presentation.controller.UserController;

public class AuthenticationMiddleware {

    private final UserService userService ;

    public AuthenticationMiddleware(UserService userService){
        this.userService=userService;
    }

    public void signup(UserDto user){
        userService.createNewUser(user);
    }
    public boolean login(UserLoginDto user,UserDto foundUser){
      return user.getPassword().equals(foundUser.getPassword());
    }
    public UserDto findUserByUsername(UserLoginDto user){
        UserDto foundUser = userService.getUserByUsername(user.getUsername());
        return foundUser;
    }
}
