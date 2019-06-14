package service.implementation;

import dao.abstraction.UserDAO;
import dao.abstraction.UserDAOFactory;
import model.User;
import service.abstraction.DBService;

import java.io.IOException;
import java.util.List;

public class DBServiceImpl implements DBService {
    private UserDAO userDAO = null;

    public DBServiceImpl() {
        try {
            this.userDAO = UserDAOFactory.getUserDAO();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public User getUserByLogin(String login) { return userDAO.getUserByLogin(login); }

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
