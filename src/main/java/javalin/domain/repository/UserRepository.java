package javalin.domain.repository;
import javalin.data.dto.UserDto;
import java.util.List;

public interface UserRepository {
    public List<UserDto> returnAllUsers();
    public UserDto getUserbyId(long userId);
    public UserDto getUserByUsername(String username);
    public void updateUser(UserDto user);
    public void createUser(UserDto user);
    public void deleteUser(long userId);
}
