package dao.implementation;

import dao.abstraction.UserDAO;
import dao.util.DBHelper;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO {
    private Connection connection;

    public UserDAOJDBCImpl() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    @Override
    public User addUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (id, ?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id=?")){
            ps.setLong(1, id);
            ResultSet set = ps.executeQuery();
            set.next();
            user = new User(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE name=?")){
            ps.setString(1, name);
            ResultSet set = ps.executeQuery();
            set.next();
            user = new User(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT  * FROM users WHERE login=?")){
            ps.setString(1, login);
            ResultSet set = ps.executeQuery();
            set.next();
            user = new User(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public long getIdByName(String name){
        Long id = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE name=?")){
            ps.setString(1, name);
            ResultSet set = ps.executeQuery();
            set.next();
            id = set.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public User editUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE users SET name=?, login=?, password=?, role=? WHERE id=?")){
            ps.setString(1,user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?")){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users")){
            ResultSet set = ps.executeQuery();
            userList = new ArrayList<>();
            getUser(set, userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;

    }

    @Override
    public void getUser(ResultSet set, List<User> userList) throws SQLException {
        while (set.next()){
            userList.add(
                    new User(
                            set.getLong(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5)
                    )
            );
        }
    }
}
