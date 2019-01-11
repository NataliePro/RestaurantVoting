DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('User2', 'user2@yandex.ru', '{noop}password2'),
  ('User3', 'user3@yandex.ru', '{noop}password3'),
  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003);

INSERT INTO restaurants (name)
VALUES ('Restaurant1'),
  ('Restaurant2'),
  ('Restaurant3'),
  ('Restaurant4'),
  ('Restaurant5');

INSERT INTO votes (date, time, user_id, restaurant_id)
VALUES ('2018-10-26', '10:00:00', 100000, 100004),
  ('2018-10-26', '09:00:00', 100001, 100006),
  ('2018-10-25', '09:00:00', 100000, 100004),
  ('2018-10-26', '10:30:00', 100002, 100004);

INSERT INTO dishes (date, name, price, restaurant_id)
VALUES ('2018-10-26', 'Dish1', 2000, 100004),
  ('2018-10-26', 'Dish2', 1000, 100006),
  ('2018-10-26', 'Dish3', 1500, 100005),
  ('2018-10-26', 'Dish4', 1300, 100007),
  ('2018-10-26', 'Dish5', 1450, 100007),
  ('2018-10-26', 'Dish6', 1700, 100005),
  ('2018-10-26', 'Dish7', 2000, 100005),
  ('2018-10-26', 'Dish8', 2200, 100004),
  ('2018-10-26', 'Dish9', 1800, 100007),
  ('2018-10-25', 'Dish10', 2200, 100004);