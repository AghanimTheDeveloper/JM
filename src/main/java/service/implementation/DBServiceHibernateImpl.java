package service.implementation;

import dao.abstraction.UserDAO;
import dao.implementation.UserDAOHibernateImpl;
import model.User;
import service.abstraction.DBService;

import java.util.List;

public class DBServiceHibernateImpl implements DBService {
    private final UserDAO userDAO;

    public DBServiceHibernateImpl() {
        this.userDAO = new UserDAOHibernateImpl();
    }


    @Override
    public User addUser(User user) {
        userDAO.addUser(user);
        return user;
    }

    @Override
    public User getUserById(long id) { return userDAO.getUserById(id); }

    @Override
    public User getUserByName(String name) { return userDAO.getUserByName(name); }

    @Override
    public long getIdByName(String name) { return userDAO.getIdByName(name); }

    @Override
    public User editUser(User user) {
        userDAO.editUser(user);
        return user;
    }

    @Override
    public void deleteUser(long id) { userDAO.deleteUser(id); }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}