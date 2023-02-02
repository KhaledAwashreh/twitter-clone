package javalin.repo;
import org.apache.commons.lang3.StringUtils;
import javalin.models.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private int userId=0;
    private static  final Map<Long, User> USERS = new HashMap<  >();

    public User getUserByUsername(String username){
        return USERS.values().stream()
                .filter(user-> StringUtils.equals(username,user.getUsername()))
                .findFirst().orElse(null);

    }

    public void createUser(User user){
        user.setUserId(getNextId());
        USERS.put(user.getUserId(),user);
    }
    private long getNextId(){

        userId++;
        return userId;
    }
}
