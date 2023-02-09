package javalin.data.dao;

import javalin.data.dto.TweetItemDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.sql.SQLException;
import java.util.List;

public interface TweetItemDAO {

    @SqlQuery("SELECT * FROM tweet")
    @RegisterBeanMapper(TweetItemDto.class)
    List<TweetItemDto> getAll();
    @SqlQuery("SELECT * FROM tweet")
    @RegisterBeanMapper(TweetItemDto.class)
    TweetItemDto get(long Id) throws SQLException;
    @SqlUpdate("placeholder")
    void create(TweetItemDto t) throws SQLException;
    @SqlUpdate("placeholder")

    void update(TweetItemDto t) throws SQLException;
    @SqlUpdate("placeholder")

    void delete(TweetItemDto t);
}
