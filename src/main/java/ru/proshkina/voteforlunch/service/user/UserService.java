package ru.proshkina.voteforlunch.service.user;

import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.to.UserTo;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();
}
