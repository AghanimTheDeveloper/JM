package service.abstraction;

import model.User;

import java.util.List;

public interface DBService {
    User addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    User getUserByLogin(String login);

    long getIdByName(String name);

    User editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();
}
