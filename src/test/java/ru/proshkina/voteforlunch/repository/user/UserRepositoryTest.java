package ru.proshkina.voteforlunch.repository.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.proshkina.voteforlunch.model.Role;
import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.repository.AbstractRepositoryTest;

import java.util.Arrays;
import java.util.List;

import static ru.proshkina.voteforlunch.repository.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.repository.user.UserTestData.*;

public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void save() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = userRepository.save(newUser);
        newUser.setId(created.getId());
        assertMatch(userRepository.getAll(), Arrays.asList(ADMIN, newUser, USER, USER2, USER3), "registered", "roles");
    }

    @Test
    public void delete() {
        Assert.assertTrue(userRepository.delete(USER_ID));
        assertMatch(userRepository.getAll(), Arrays.asList(ADMIN, USER2, USER3), "registered", "roles");
    }

    @Test
    public void deleteNotFound() {
        Assert.assertFalse(userRepository.delete(123));
    }

    @Test
    public void get() {
        User user = userRepository.get(USER_ID);
        assertMatch(user, USER, "registered", "roles");
    }

    @Test
    public void getNotFound() {
        Assert.assertNull(userRepository.get(123));
    }

    @Test
    public void getByEmail() {
        User user = userRepository.getByEmail("user@yandex.ru");
        assertMatch(user, USER, "registered", "roles");
    }

    @Test
    public void getNotFoundByEmail() {
        Assert.assertNull(userRepository.getByEmail("user111@yandex.ru"));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        userRepository.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void getAll() {
        List<User> all = userRepository.getAll();
        assertMatch(all, Arrays.asList(ADMIN, USER, USER2, USER3), "registered", "roles");
    }
}