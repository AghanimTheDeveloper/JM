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
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user;
        Session session = sessionFactory.openSession();
        user = session.load(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user;
        Session session = sessionFactory.openSession();
        user = session.load(User.class, name);
        return user;
    }

    @Override
    public long getIdByName(String name) {
        long id;
        Session session = sessionFactory.openSession();
        id = session.load(User.class, name).getId();
        return id;
    }

    @Override
    public User editUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        return user;
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        return (List<User>) session.createQuery("from User").getResultList();
    }

    @Override
    @SuppressWarnings("UnusedDeclaration")
    public void getUser(ResultSet result, List<User> urList) throws SQLException {

    }


}
