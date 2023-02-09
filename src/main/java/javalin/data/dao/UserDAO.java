package javalin.data.dao;
import javalin.data.dto.UserDto;
import java.sql.SQLException;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDAO {
    @SqlQuery("SELECT * FROM user")
    @RegisterBeanMapper(UserDto.class)
    List<UserDto> getAll() throws SQLException;
    @SqlQuery("SELECT tweet_id, tweet_content, tweet_time, tweet_owner_id + from tweet where tweet_id= :id ")
    @RegisterBeanMapper(UserDto.class)
    UserDto get(@Bind("id") long Id) throws SQLException;
    @SqlUpdate("placeholder")
    void create(UserDto t) throws SQLException;
    @SqlUpdate("placeholder")
    void update(UserDto t) throws SQLException;
    @SqlUpdate("placeholder")
    void delete(UserDto t);
}
