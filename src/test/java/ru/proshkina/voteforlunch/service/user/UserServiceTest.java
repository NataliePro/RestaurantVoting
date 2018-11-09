package ru.proshkina.voteforlunch.service.user;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.proshkina.voteforlunch.model.Role;
import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.proshkina.voteforlunch.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.service.user.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), List.of(ADMIN, newUser, USER, USER2, USER3), "registered", "roles");
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), List.of(ADMIN, USER2, USER3), "registered", "roles");
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER, "registered", "roles");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER, "registered", "roles");
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated, "registered", "roles");
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, List.of(ADMIN, USER, USER2,USER3), "registered", "roles");
    }

}