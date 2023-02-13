package javalin.domain.service.impl;

import javalin.data.dto.UserDto;
import javalin.domain.repository.UserRepository;
import javalin.domain.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> returnAllUsers() {
        return userRepository.returnAllUsers();
    }

    @Override
    public UserDto getUserById(long userId) {
        return userRepository.getUserbyId(userId);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void updateUser( UserDto user) {
        userRepository.updateUser(user);
    }

    @Override
    public void createNewUser(UserDto user) {
        userRepository.createUser(user);
    }

    @Override
    public void deleteUser(long deleteUser) {
        userRepository.deleteUser(deleteUser);
    }
}
