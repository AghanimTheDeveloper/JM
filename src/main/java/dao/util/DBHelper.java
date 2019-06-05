package dao.util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBHelper {
    private static DBHelper instance = new DBHelper();

    public static DBHelper getInstance() {
        return instance;
    }

    private DBHelper() {
    }

    public Connection getConnection() {
        Properties properties = new Properties();
        Connection connection = null;

        try {
            properties.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties")));
            Class.forName(properties.getProperty("driver.class"));
            connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        Properties properties = new Properties();

        try {
            properties.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.setProperties(properties);
        return configuration;
    }

    public SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
