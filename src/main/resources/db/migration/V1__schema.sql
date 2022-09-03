create table article
(
id serial primary key ,
header varchar (55) not null unique,
content text not null
);