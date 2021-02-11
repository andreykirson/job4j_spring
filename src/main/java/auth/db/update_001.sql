create table person (
   id serial primary key not null,
   login varchar(2000),
   password varchar(2000)
);

insert into person (login, password) values ('jhon', '123');
insert into person (login, password) values ('bah', '456');
insert into person (login, password) values ('leon', '789');