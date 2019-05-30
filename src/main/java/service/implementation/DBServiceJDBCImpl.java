package service.implementation;

import dao.abstraction.UserDAO;
import dao.implementation.UserDAOJDBCImpl;
import model.User;
import service.abstraction.DBService;

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
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        this.userDAO = new UserDAOJDBCImpl(connection);
    }

    @Override
    public long addUser(User user) {
        return userDAO.addUser(user);
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
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    private static Connection getMysqlConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

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
