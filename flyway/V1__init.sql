create table articles
(
    id              bigserial primary key,
    author          varchar(50),
    title           varchar(50),
    prog_language   varchar(50),
    file_name       varchar(255),
    file_type       varchar(50),
    content         bigint not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table comments
(
    id                  bigserial primary key,
    username            varchar(30) not null,
    content             varchar(2000),
    article_id          bigint REFERENCES articles (id),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table images
(
    id              bigserial primary key,
    file_name       varchar(255),
    file_type       varchar(50),
    file_folder     varchar(255),
    article_id      bigint REFERENCES articles (id)
);

create table ratings
(
    id              bigserial primary key,
    user_score      int,
    username       varchar(30),
    article_id      bigint REFERENCES articles (id)
);

create table users
(
    id         bigserial primary key,
    first_name varchar(80) not null,
    last_name  varchar(80),
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id     bigint not null references users (id),
    role_id     bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, first_name, last_name, password, email)
values ('user', 'Bob', 'Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', 'John', 'Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);