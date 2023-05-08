drop table if exists post;

create table post
(
    id         int          not null auto_increment,
    title      varchar(50)  not null,
    body       varchar(500) not null,
    primary key (id)
);
