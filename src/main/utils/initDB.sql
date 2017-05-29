DROP TABLE IF EXISTS persistent_logins CASCADE;
DROP TABLE IF EXISTS status CASCADE;
DROP TABLE IF EXISTS period CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS task CASCADE;
DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS status_id_seq;
DROP SEQUENCE IF EXISTS task_id_seq;
DROP SEQUENCE IF EXISTS period_id_seq;
DROP SEQUENCE IF EXISTS project_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq;
CREATE SEQUENCE task_id_seq;
CREATE SEQUENCE status_id_seq;
CREATE SEQUENCE period_id_seq;
CREATE SEQUENCE project_id_seq;

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

CREATE TABLE public.period(
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('likes_id_seq'::regclass),
  period_name CHARACTER VARYING(64) NOT NULL,
  start_date timestamp WITH TIME ZONE,
  end_date timestamp WITH TIME ZONE,
  user_id integer not null,
  FOREIGN KEY (user_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE public.project(
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('likes_id_seq'::regclass),
  project_name CHARACTER VARYING(64) NOT NULL,
  short_name CHARACTER VARYING(3) NOT NULL,
  user_id integer not null,
  FOREIGN KEY (user_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.status(
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('likes_id_seq'::regclass),
  description CHARACTER VARYING(64) NOT NULL,
  status CHARACTER VARYING(64) NOT NULL
);

CREATE TABLE public.task (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('task_id_seq'::regclass),
  postdatetime TIMESTAMP WITHOUT TIME ZONE,
  name CHARACTER VARYING(45),
  text CHARACTER VARYING(255),
  user_id INTEGER,
  period_id INTEGER,
  project_id INTEGER,
  status_id INTEGER,
  attachment CHARACTER VARYING(255),
  FOREIGN KEY (user_id) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (period_id) REFERENCES period (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (project_id) REFERENCES project (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (status_id) REFERENCES status (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE public.persistent_logins (
  username CHARACTER VARYING(64) NOT NULL,
  series CHARACTER VARYING(64) PRIMARY KEY NOT NULL,
  token CHARACTER VARYING(64) NOT NULL,
  last_used TIMESTAMP WITHOUT TIME ZONE NOT NULL
);




