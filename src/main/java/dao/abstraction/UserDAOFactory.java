package dao.abstraction;

import dao.implementation.UserDAOHibernateImpl;
import dao.implementation.UserDAOJDBCImpl;
import service.abstraction.DBService;
import service.implementation.DBServiceImpl;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public interface UserDAOFactory {

    static UserDAO getUserDAO()throws IOException{
        String usedTech = null;
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties")));
            usedTech = (properties.getProperty("used.tech"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(usedTech.equals("jdbc")){
            return new UserDAOJDBCImpl();
        } else if (usedTech.equals("hibernate")){
            return new UserDAOHibernateImpl();
        } else
            throw new IOException();
    }
}
