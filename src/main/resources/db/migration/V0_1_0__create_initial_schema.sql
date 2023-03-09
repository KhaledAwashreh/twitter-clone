CREATE TABLE tweet(
tweet_id bigint not null,
tweet_content varchar(255) not null,
tweet_time date not null,
tweet_owner_id bigint not null
);

CREATE TABLE user(
username varchar(25) not null,
password varchar(25) not null,
user_id bigint not null
);