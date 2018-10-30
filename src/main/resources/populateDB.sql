DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaraunts;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaraunts (name)
VALUES  ('Ресторан1'),
        ('Ресторан2'),
        ('Ресторан3'),
        ('Ресторан4');

INSERT INTO votes (date, time, user_id, restaraunt_id)
VALUES  ('2018-10-26', '10:00:00', 100000, 100004),
        ('2018-10-26', '09:00:00', 100001, 100006),
        ('2018-10-26', '10:30:00', 100002, 100004);

INSERT INTO dishes (date, name, price, restaraunt_id)
VALUES  ('2018-10-26', 'Блюдо1', 20000, 100004),
        ('2018-10-26', 'Блюдо2', 10000, 100006),
        ('2018-10-26', 'Блюдо3', 15000, 100005),
        ('2018-10-26', 'Блюдо4', 13000, 100007),
        ('2018-10-26', 'Блюдо5', 14500, 100007),
        ('2018-10-26', 'Блюдо6', 17000, 100005),
        ('2018-10-26', 'Блюдо7', 20000, 100005),
        ('2018-10-26', 'Блюдо8', 22000, 100004),
        ('2018-10-26', 'Блюдо9', 18000, 100007);