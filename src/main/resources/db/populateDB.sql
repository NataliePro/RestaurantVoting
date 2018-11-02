DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name)
VALUES ('Restaurant1'),
  ('Restaurant2'),
  ('Restaurant3'),
  ('Restaurant4');

INSERT INTO votes (date, time, user_id, restaurant_id)
VALUES ('2018-10-26', '10:00:00', 100000, 100004),
  ('2018-10-26', '09:00:00', 100001, 100006),
  ('2018-10-26', '10:30:00', 100002, 100004);

INSERT INTO dishes (date, name, price, restaurant_id)
VALUES ('2018-10-26', 'Dish1', 20000, 100004),
  ('2018-10-26', 'Dish2', 10000, 100006),
  ('2018-10-26', 'Dish3', 15000, 100005),
  ('2018-10-26', 'Dish4', 13000, 100007),
  ('2018-10-26', 'Dish5', 14500, 100007),
  ('2018-10-26', 'Dish6', 17000, 100005),
  ('2018-10-26', 'Dish7', 20000, 100005),
  ('2018-10-26', 'Dish8', 22000, 100004),
  ('2018-10-26', 'Dish9', 18000, 100007);