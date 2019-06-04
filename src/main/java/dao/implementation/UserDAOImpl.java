package dao.implementation;

import dao.abstraction.UserDAO;
import lombok.SneakyThrows;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private PreparedStatement ps;

    @SneakyThrows
    private Connection getConnection() {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jm-preproject?user=root&password=root");
    }

    public UserDAOImpl() {
        this.connection = getConnection();
    }


    @Override
    @SneakyThrows
    public User addUser(User user) {
            ps = connection.prepareStatement("INSERT INTO users VALUES (id, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        return user;
    }

    @Override
    @SneakyThrows
    public User getUserById(long id) {
            ps = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setLong(1, id);
            ResultSet set = ps.executeQuery();
            set.next();
            return new User(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
            );
    }

    @Override
    @SneakyThrows
    public User getUserByName(String name) {
            ps = connection.prepareStatement("SELECT * FROM users WHERE name=?");
            ps.setString(1, name);
            ResultSet set = ps.executeQuery();
            set.next();
            return new User(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
            );
    }

    @Override
    @SneakyThrows
    public long getIdByName(String name){
            ps = connection.prepareStatement("SELECT * FROM users WHERE name=?");
            ps.setString(1, name);
            ResultSet set = ps.executeQuery();
            set.next();
            return set.getLong(1);
    }

    @Override
    @SneakyThrows
    public User editUser(User user) {
            ps = connection.prepareStatement("UPDATE users SET name=?, login=?, password=? WHERE id=?");
            ps.setString(1,user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getId());
            ps.executeUpdate();
        return user;
    }

    @Override
    @SneakyThrows
    public void deleteUser(long id) {
            ps = connection.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
    }

    @Override
    @SneakyThrows
    public List<User> getAllUsers() {
        List<User> userList;
            ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet set = ps.executeQuery();
            userList = new ArrayList<>();
            getUser(set, userList);
        return userList;

    }

    @Override
    @SneakyThrows
    public void getUser(ResultSet set, List<User> userList) {
        while (set.next()){
            userList.add(
                    new User(
                            set.getLong(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4)
                    )
            );
        }
    }
}
