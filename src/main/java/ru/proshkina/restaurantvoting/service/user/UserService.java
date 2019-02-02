package ru.proshkina.restaurantvoting.service.user;

import ru.proshkina.restaurantvoting.model.User;
import ru.proshkina.restaurantvoting.to.UserTo;
import ru.proshkina.restaurantvoting.util.exception.NotFoundException;

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
