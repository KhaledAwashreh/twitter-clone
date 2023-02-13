package javalin.presentation.controller;
import javalin.data.dto.UserDto;
import javalin.domain.service.UserService;

import java.util.List;

public class UserController {
    private final UserService  userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public List<UserDto> returnALlUsers(){
        return userService.returnAllUsers();
    }
    public UserDto getUserById(long userId){
        return userService.getUserById(userId);
    }
    public void updateUser(UserDto user){
        userService.updateUser(user);
    }
    public void createUser(UserDto user){
        userService.createNewUser(user);
    }
    public void deleteUser(long userId){
        userService.deleteUser(userId);
    }

}
