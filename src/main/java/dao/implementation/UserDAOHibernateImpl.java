package dao.implementation;

import dao.abstraction.UserDAO;
import dao.util.DBHelper;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAOHibernateImpl() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        sessionFactory = DBHelper.getInstance().createSessionFactory(configuration);
    }

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        return session.load(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        return session.load(User.class, name);
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        return session.load(User.class, login);
    }

    @Override
    public long getIdByName(String name) {
        Session session = sessionFactory.openSession();
        return session.load(User.class, name).getId();
    }

    @Override
    public User editUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = (List<User>) session.createQuery("from User").getResultList();
        session.close();
        return users;
    }

    @Override
    @SuppressWarnings("UnusedDeclaration")
    public void getUser(ResultSet result, List<User> urList) throws SQLException {

    }


}
