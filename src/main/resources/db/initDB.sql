create table role
(
    id   bigserial   not null
        constraint role_pkey
            primary key,
    name varchar(20) not null
);

create unique index role_name_uindex
    on role (name);

create table users
(
    id        bigserial    not null
        constraint users_pkey
            primary key,
    firstName varchar(40)  ,
    lastName  varchar(40)  ,
    roleId   integer      not null,
    email     varchar(254) not null,
    password  varchar(35)  not null
);

create unique index user_email_uindex
    on users (email);

create table author
(
    id              bigserial not null
        constraint author_pkey
            primary key,
    firstName varchar(40)  ,
    lastName  varchar(40)
);

create table tag
(
    id   bigserial   not null
        constraint tag_pkey
            primary key,
    name varchar(20) not null
);

create unique index tag_name_uindex
    on tag (name);

create table book
(
    id         bigserial  not null
        constraint book_pkey
            primary key,
    name       varchar(40)not null,
    authorId   integer    not null,
    about      varchar(255) not null,
    tagId integer    not null
);
