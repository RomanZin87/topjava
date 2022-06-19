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

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2022-06-17 10:00:00', 'Завтрак', 500),
       (100000, '2022-06-17 13:00:00', 'Обед', 1000),
       (100000, '2022-06-17 20:00:00', 'Ужин', 500),
       (100000, '2022-06-18 00:00:00', 'Еда на граничное значение', 100),
       (100000, '2022-06-18 10:30:00', 'Завтрак', 1000),
       (100000, '2022-06-18 14:00:00', 'Обед', 500),
       (100000, '2022-06-18 19:30:00', 'Ужин', 410),
       (100001, '2022-06-01 14:00:00', 'Админ Ланч', 510),
       (100001, '2022-06-01 21:00:00', 'Админ Ужин', 1500);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
