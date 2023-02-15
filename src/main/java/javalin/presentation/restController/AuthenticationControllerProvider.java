package javalin.presentation.restController;

import javalin.data.dto.UserDto;
import javalin.data.dto.UserLoginDto;
import javalin.presentation.controller.UserController;

public class AuthenticationControllerProvider {

    private final UserController userController ;

    public AuthenticationControllerProvider(UserController controller){
        this.userController=controller;
    }

    public void signup(UserDto user){
        userController.createUser(user);
    }
    public boolean login(UserLoginDto user,UserDto foundUser){
      return user.getPassword().equals(foundUser.getPassword());
    }
}
