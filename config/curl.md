### curl samples (application deployed in application context `voteforlunch`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/voteforlunch/rest/admin/users --user admin@gmail.com:admin`

#### get user 100001
`curl -s http://localhost:8080/voteforlunch/rest/admin/users/100001 --user admin@gmail.com:admin`

#### create new user
`curl -s -X POST -d '{"name":"New user","email":"newuser@gmail.com","password":"newPass","roles":["ROLE_USER","ROLE_ADMIN"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforlunch/rest/admin/users --user admin@gmail.com:admin`

#### update user 100000
`curl -s -X PUT -d '{"id":100000,"name":"UpdatedName","email":"user@yandex.ru","password":"password","roles":["ROLE_ADMIN"]}' -H 'Content-Type: application/json' http://localhost:8080/voteforlunch/rest/admin/users/100000 --user admin@gmail.com:admin`

#### delete user 100000
`curl -s -X DELETE http://localhost:8080/voteforlunch/rest/admin/users/100000 --user admin@gmail.com:admin`

#### get user by email admin@gmail.com
`curl -s http://localhost:8080/voteforlunch/rest/admin/users/by?email=admin@gmail.com --user admin@gmail.com:admin`

#### create new restaurant
`curl -s -X POST -d '{"name":"Created restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforlunch/rest/admin/restaurants --user admin@gmail.com:admin`

#### update restaurant 100004
`curl -s -X PUT -d '{"id":100004,"name":"Updated restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/voteforlunch/rest/admin/restaurants/100004 --user admin@gmail.com:admin`

#### delete restaurant 100004
`curl -s -X DELETE http://localhost:8080/voteforlunch/rest/admin/restaurants/100004 --user admin@gmail.com:admin`

#### get restaurant 100004
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/100004  --user admin@gmail.com:admin`

#### get all restaurants
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants  --user admin@gmail.com:admin`

#### create new dish for restaurant 100004 and date 2018-10-26
`curl -s -X POST -d '{"name":"New dish","date":"2018-10-26","priceInCents":5000,"restaurant":{"id":100004,"name":"Restaurant1"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes --user admin@gmail.com:admin`

#### update dish for restaurant 100004
`curl -s -X PUT -d '{"id":100013,"name":"Updated dish1","date":"2018-10-26","priceInCents":2000,"restaurant":{"id":100004,"name":"Restaurant1"}}' -H 'Content-Type: application/json' http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes/100013 --user admin@gmail.com:admin`

#### delete dish for restaurant 100004
`curl -s -X DELETE http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes/100013 --user admin@gmail.com:admin`

#### get dish 100013 for restaurant 100004
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes/100013  --user admin@gmail.com:admin`

#### get All dishes for all restaurants for all the time
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/dishes --user admin@gmail.com:admin`

#### get All dishes for all restaurants and date 2018-10-26
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/dishes?date=2018-10-26 --user admin@gmail.com:admin`

#### get All dishes for restaurant 100004 for all the time
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes --user admin@gmail.com:admin`

#### get All dishes for restaurant 100004 and date 2018-10-26
`curl -s http://localhost:8080/voteforlunch/rest/admin/restaurants/100004/dishes?date=2018-10-26 --user admin@gmail.com:admin`

#### get user profile
`curl -s "http://localhost:8080/voteforlunch/rest/profile" --user user@yandex.ru:password`

#### delete user profile
`curl -s DELETE http://localhost:8080/voteforlunch/rest/profile --user user@yandex.ru:password`

#### register new user 
`curl -s -X POST -d '{"name":"newName","email":"newemail@ya.ru","password":"newPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforlunch/rest/profile/register`

#### update user profile
`curl -s -X PUT -d '{"name":"Updated","email":"user@yandex.ru","password":"updatedPassword"}' -H 'Content-Type: application/json' http://localhost:8080/voteforlunch/rest/profile --user user@yandex.ru:password`

#### user make his vote for restaurant 100004 
`curl -s -X POST -d '{"date":"2019-01-12","time":"14:21","restaurant":{"id":100004,"name":"Restaurant1"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforlunch/rest/profile/restaurants/100004/votes --user user@yandex.ru:password`

#### get vote's results for date 2018-10-26
`curl -s "http://localhost:8080/voteforlunch/rest/profile/restaurants/votes?date=2018-10-26" --user user@yandex.ru:password`

#### get restaurants with dishes for date 2018-10-26
`curl -s "http://localhost:8080/voteforlunch/rest/profile/restaurants/dishes?date=2018-10-26" --user user@yandex.ru:password`








#### get user's vote for date 2018-10-25
`curl -s "http://localhost:8080/voteforlunch/profile/restaurants/100004/votes?date=2018-10-25" --user user@yandex.ru:password`





#### filter Meals
`curl -s "http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2015-05-30&startTime=07:00:00&endDate=2015-05-31&endTime=11:00:00" --user user@yandex.ru:password`

#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/profile/meals/100008 --user user@yandex.ru:password`




#### validate with Error
`curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`
`curl -s -X PUT -d '{"dateTime":"2015-05-30T07:00"}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals/100003 --user user@yandex.ru:password`
