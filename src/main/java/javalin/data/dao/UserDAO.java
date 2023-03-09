package javalin.data.dao;
import javalin.data.dto.UserDto;
import java.sql.SQLException;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDAO {
    @SqlQuery("SELECT * FROM user")
    @RegisterBeanMapper(UserDto.class)
    List<UserDto> getAll();
    @SqlQuery("SELECT * FROM user where user_id=:id")
    @RegisterBeanMapper(UserDto.class)
    UserDto get(@Bind("id") long Id);
    @SqlQuery("SELECT * FROM user where username=:username")
    @RegisterBeanMapper(UserDto.class)
    UserDto getByUsername(@Bind("username") String username);
    @SqlUpdate("INSERT INTO user(user_id,username,password) VALUES (:u.userId,:u.username,:u.password)")
    void create(@BindBean("u") UserDto u);
    @SqlUpdate("placeholder")
    void update(UserDto u);
    @SqlUpdate("DELETE FROM user where user_id=:userId")
    void delete(@Bind("userId")  long userId);

    @SqlQuery("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1")
    int getLastUserId();
}
