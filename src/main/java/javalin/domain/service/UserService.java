package javalin.domain.service;

import javalin.data.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> returnAllUsers();
    public UserDto getUserById(long userId);
    public UserDto getUserByUsername(String userName);
    public void updateUser(UserDto userDto);
    public void createNewUser(UserDto user);
    public void deleteUser(long userId);
}
