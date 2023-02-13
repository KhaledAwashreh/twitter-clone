package javalin.data.dao;

import javalin.data.dto.TweetItemDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.sql.SQLException;
import java.util.List;

public interface TweetItemDAO {

    @SqlQuery("SELECT * FROM tweet")
    @RegisterBeanMapper(TweetItemDto.class)
    List<TweetItemDto> getAll();
    @SqlQuery("SELECT * FROM tweet where tweet_id=:id")
    @RegisterBeanMapper(TweetItemDto.class)
    TweetItemDto get(@Bind("id") long Id);
    @SqlUpdate("INSERT into tweet(tweet_id,tweet_content,tweet_time,tweet_owner_id) VALUES (:t.tweetId, :t.tweetContent,:t.tweetDate,:t.tweetOwnerId)")
    void create(@BindBean("t") TweetItemDto t) ;
    @SqlUpdate("UPDATE tweet SET tweet_content=:t.tweetContent, tweet_time=:t.tweetDate WHERE tweet_id=:t.tweetId")
    void update(@BindBean("t") TweetItemDto t);
    @SqlUpdate("DELETE FROM tweet where tweet_id=:tweetId")
    void delete(@Bind("tweetId") long tweetId);
}
