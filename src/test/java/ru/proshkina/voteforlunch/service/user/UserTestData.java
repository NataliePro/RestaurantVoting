package ru.proshkina.voteforlunch.service.user;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.proshkina.voteforlunch.TestUtil;
import ru.proshkina.voteforlunch.model.Role;
import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.web.json.JsonUtil;

import java.util.List;

import static ru.proshkina.voteforlunch.TestUtil.readFromJsonMvcResult;
import static ru.proshkina.voteforlunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final User USER2 = new User(USER_ID + 1, "User2", "user2@yandex.ru", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(USER_ID + 2, "User3", "user3@yandex.ru", "password3", Role.ROLE_USER);


    public static void assertMatch(User actual, User expected) {
        TestUtil.assertMatch(actual, expected, "registered", "votes", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        TestUtil.assertMatch(actual, List.of(expected), "registered", "votes", "password");
    }

    public static ResultMatcher getUserMatcher(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
