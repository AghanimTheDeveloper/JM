package service.implementation;

import dao.abstraction.UserDAO;
import dao.implementation.UserDAOImpl;
import model.User;
import service.abstraction.DBService;
import java.util.List;

public class DBServiceJDBCImpl implements DBService {
    private final UserDAO userDAO;

    public DBServiceJDBCImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public User addUser(User user) {
        userDAO.addUser(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public long getIdByName(String name){
        return userDAO.getIdByName(name);
    }

    @Override
    public User editUser(User user) {
        userDAO.editUser(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
