--
--create table comments
--(
--    id                  bigserial primary key,
--    username            varchar(30) not null,
--    consumer            varchar(30) not null,
--    content             varchar(255),
--    product_id          bigint REFERENCES products (id),
--    created_at          timestamp default current_timestamp,
--    updated_at          timestamp default current_timestamp
--);
create table articles
(
    id              bigserial primary key,
    author          varchar(50),
    title           varchar(50),
    prog_language   varchar(50),
    file_name       varchar(255),
    file_type       varchar(50),
    content         bigint not null
);

create table images
(
    id              bigserial primary key,
    file_name       varchar(255),
    file_type       varchar(50),
    file_folder     varchar(255),
    article_id      bigint REFERENCES articles (id)
);
--insert into articles (content)
--insert into articles (content) values ('<h2>Что такое ООП?</h2> Объектно-ориентированное программирование (ООП) — методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определенного класса, а классы образуют иерархию наследования. <p></p> <ol> <li class="liText">объектно-ориентированное программирование использует в качестве основных логических конструктивных элементов объекты, а не алгоритмы;</li> <li class="liText">каждый объект является экземпляром определенного класса</li> <li class="liText">классы образуют иерархии.</li> </ol> Программа считается объектно-ориентированной, только если выполнены все три указанных требования. В частности, программирование, не использующее наследование, называется не объектно-ориентированным, а программированием с помощью абстрактных типов данных. <p></p>Согласно парадигме ООП программа состоит из объектов, обменивающихся сообщениями. Объекты могут обладать состоянием, единственный способ изменить состояние объекта - послать ему сообщение, в ответ на которое, объект может изменить собственное состояние. <p></p> <h2>Назовите основные принципы ООП.</h2> <ol> <li class="liText">Инкапсуляция - сокрытие реализации.</li> <li class="liText">Наследование - создание новой сущности на базе уже существующей.</li> <li class="liText">Полиморфизм - возможность иметь разные формы для одной и той же сущности.</li> <li class="liText">Абстракция - набор общих характеристик.</li> <li class="liText">Посылка сообщений - форма связи, взаимодействия между сущностями.</li> <li class="liText">Переиспользование- все что перечислено выше работает на повторное использование кода.</li> </ol> Это единственно верный порядок парадигм ООП, так как каждая последующая использует предыдущие. <p></p>');

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