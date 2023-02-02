package javalin.services;
import javalin.models.User;
import javalin.repo.UserRepository;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    UserRepository userRepository= new UserRepository();

    public static final Map<String,User> TOKENS = new HashMap<>();
    public boolean authenticate(String username, String password ){
    User result = userRepository.getUserByUsername(username);
    if(result!=null)
        return StringUtils.equals(result.getPassword(),password);
    else
        return false;
    }public void createUser(User user){
        userRepository.createUser(user);
    }
    public void addToken(String token, User user){
        TOKENS.put(token,user);
    }
    public User validateToken(String token){
        return TOKENS.getOrDefault(token,null);
    }
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }


}
