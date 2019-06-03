package service.implementation;

import dao.abstraction.UserDAO;
import dao.implementation.UserDAOImpl;
import model.User;
import service.abstraction.DBService;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBServiceJDBCImpl implements DBService {
    private final UserDAO userDAO;

    public DBServiceJDBCImpl() {
        Connection connection = null;

        try {
            connection = getMysqlConnection();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        this.userDAO = new UserDAOImpl(connection);
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
    public long getIdByName(String name) throws SQLException{
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

    private static Connection getMysqlConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance());

        StringBuilder url = new StringBuilder();

        url.
                append("jdbc:mysql://").        //db type
                append("localhost:").           //host name
                append("3306/").                //port
                append("jm-preproject?").       //db name
                append("user=root&").          //login
                append("password=root");       //password

        System.out.println("URL: " + url + "\n");

        return DriverManager.getConnection(url.toString());
    }
}
