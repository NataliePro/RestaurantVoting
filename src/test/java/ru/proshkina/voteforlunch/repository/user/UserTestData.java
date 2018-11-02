package ru.proshkina.voteforlunch.repository.user;

import ru.proshkina.voteforlunch.model.Role;
import ru.proshkina.voteforlunch.model.User;

import static ru.proshkina.voteforlunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final User USER2 = new User(USER_ID + 1, "User2", "user2@yandex.ru", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(USER_ID + 2, "User3", "user3@yandex.ru", "password3", Role.ROLE_USER);
}
