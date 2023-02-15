package javalin.data.repository;
import javalin.data.dao.UserDAO;
import javalin.data.dto.UserDto;
import javalin.domain.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDao;
    private long userId=0;

    public UserRepositoryImpl(UserDAO dao) {
        this.userDao=dao;
    }


    @Override
    public List<UserDto> returnAllUsers() {
        return userDao.getAll();
    }

    @Override
    public UserDto getUserbyId(long userId) {
        return userDao.get(userId);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }
    @Override
    public void updateUser(UserDto user) {
         userDao.update(user);
    }

    @Override
    public void createUser(UserDto user) {
        user.setUserId(getNextId());
        userDao.create(user);
    }

    @Override
    public void deleteUser(long userId) {
        userDao.delete(userId);
    }
    public long getNextId(){
        long newId=userId+1;
        return newId;
    }



}
