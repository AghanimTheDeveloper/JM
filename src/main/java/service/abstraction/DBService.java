package service.abstraction;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface DBService {
    long addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    long getIdByName(String name) throws SQLException;

    void editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();
}
