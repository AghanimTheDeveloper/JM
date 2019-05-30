package dao.implementation;

import dao.abstraction.UserDAO;
import model.User;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO {
    private Executor executor;

    public UserDAOJDBCImpl(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public long addUser(User user) {
        executor.execUpdate(String.format("insert into users (name, password, login) values ('%s', '%s', '%s')",
                user.getName(),
                user.getPassword(),
                user.getLogin()));
        return user.getId();
    }

    @Override
    public User getUserById(long id) {
        return executor.execQuery(String.format("SELECT * FROM users where id='%s'", id), result -> {
            result.next();

            return new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        });
    }

    @Override
    public User getUserByName(String name) {
        return executor.execQuery(String.format("SELECT * FROM users where name='%s'", name), result -> {
            result.next();

            return new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        });
    }

    @Override
    public long getIdByName(String name){
        return executor.execQuery(String.format("select id from users where name = '%s'", name), result -> {
            result.next();
            return result.getLong(1);
        });
    }

    @Override
    public void editUser(User user) {
        executor.execUpdate(String.format("UPDATE users SET name='%s', password='%s', login='%s'' WHERE id='%s'",
                user.getName(),
                user.getPassword(),
                user.getLogin(),
                user.getId()));
    }

    @Override
    public void deleteUser(long id) {
        executor.execUpdate(String.format("delete from users where id = '%s'", id));
    }

    @Override
    public List<User> getAllUsers() {
        return executor.execQuery("select * from users", result -> {
            List<User> userList = new ArrayList<>();
            getUser(result, userList);
            return userList;
        });
    }

    @Override
    public void getUser(ResultSet result, List<User> userList) throws SQLException {
        while (result.next()){
            userList.add(
                    new User(
                            result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4)
                    )
            );
        }
    }
}
