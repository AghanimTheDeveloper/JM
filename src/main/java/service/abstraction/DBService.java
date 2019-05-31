package service.abstraction;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface DBService {
    User addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    long getIdByName(String name) throws SQLException;

    User editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();
}
