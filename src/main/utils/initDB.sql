DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS tweet;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS likes_id_seq;
DROP SEQUENCE IF EXISTS comments_id_seq;
DROP SEQUENCE IF EXISTS followers_id_seq;
DROP SEQUENCE IF EXISTS tweet_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq;
CREATE SEQUENCE tweet_id_seq;
CREATE SEQUENCE comments_id_seq;
CREATE SEQUENCE followers_id_seq;
CREATE SEQUENCE likes_id_seq;

-- create table users (
--   id int primary key,
--   username varchar(25) UNIQUE not null,
--   password varchar(25) not null,
--   first_name varchar(50) not null,
--   last_name varchar(50) not null,
--   email varchar(50) not null
-- );
--
-- create table tweet (
--   id int primary key,
--   user_id integer not null,
--   text varchar(255) not null,
--   postDateTime TIMESTAMP DEFAULT now() not null,
--   foreign key (user_id) references users(id)
-- );

CREATE TABLE public.users (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  email CHARACTER VARYING(255),
  first_name CHARACTER VARYING(255),
  last_name CHARACTER VARYING(255),
  password CHARACTER VARYING(255) NOT NULL,
  username CHARACTER VARYING(255) NOT NULL,
  avatar CHARACTER VARYING(255) DEFAULT '/resources/pics/useravatar.png'
);

CREATE UNIQUE INDEX unique_username ON users USING BTREE (username);
CREATE TABLE public.tweet (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('tweet_id_seq'::regclass),
  postdatetime TIMESTAMP WITHOUT TIME ZONE,
  text CHARACTER VARYING(255),
  user_id INTEGER,
  image CHARACTER VARYING(255),
  likes INTEGER DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.comments (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
  text CHARACTER VARYING(255),
  tweet_id INTEGER,
  user_id INTEGER,
  postdatetime TIMESTAMP WITHOUT TIME ZONE,
  FOREIGN KEY (user_id) REFERENCES users (id)
  MATCH SIMPLE  ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (tweet_id) REFERENCES tweet (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.followers (
  id INTEGER NOT NULL DEFAULT nextval('followers_id_seq'::regclass),
  followed_id INTEGER,
  follower_id INTEGER,
  FOREIGN KEY (followed_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (follower_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.likes (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('likes_id_seq'::regclass),
  tweet_id INTEGER,
  user_id INTEGER,
  FOREIGN KEY (tweet_id) REFERENCES public.tweet (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (user_id) REFERENCES public.users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.persistent_logins (
  username CHARACTER VARYING(64) NOT NULL,
  series CHARACTER VARYING(64) PRIMARY KEY NOT NULL,
  token CHARACTER VARYING(64) NOT NULL,
  last_used TIMESTAMP WITHOUT TIME ZONE NOT NULL
);