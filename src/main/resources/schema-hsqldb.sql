create table measurement
(
  id             INTEGER IDENTITY primary key,
  amount         varchar(255) not null,
  created        timestamp not null,
  measurement_id INTEGER not null,
  user_id        INTEGER not null,
  version        bigint default 0
);

create table users
(
  id                   INTEGER IDENTITY primary key,
  version              bigint default 0,
  email                varchar(255) not null,
  password             varchar(255) not null,
  date_of_registration timestamp
);

create table measurement_type
(
  id   INTEGER IDENTITY primary key,
  name varchar(255) not null,
  type varchar(255) not null
);

create sequence measurement_id_seq start with 100 increment by 1;
create sequence measurement_type_id_seq start with 100 increment by 1;
create sequence users_id_seq start with 100 increment by 1;