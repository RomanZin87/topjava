DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2022-12-08T10:00', 'Завтрак', 500),
       (100000, '2022-12-08T12:00', 'Обед', 1000),
       (100001, '2022-12-08T10:30', 'Завтрак', 700),
       (100001, '2022-12-10T12:30', 'Обед', 900);