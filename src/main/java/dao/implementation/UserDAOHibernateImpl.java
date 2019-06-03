package dao.implementation;

import dao.abstraction.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {
    private final SessionFactory sessionFactory;
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    public UserDAOHibernateImpl() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
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

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jm-preproject");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
