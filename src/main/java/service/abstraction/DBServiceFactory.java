package service.abstraction;

import service.implementation.DBServiceHibernateImpl;
import service.implementation.DBServiceJDBCImpl;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public interface DBServiceFactory {

    static DBService getDBService()throws IOException{
        String usedTech = null;
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties")));
            usedTech = (properties.getProperty("used.tech"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(usedTech.equals("jdbc")){
            return new DBServiceJDBCImpl();
        } else if (usedTech.equals("hibernate")){
            return new DBServiceHibernateImpl();
        } else
            throw new IOException();
    }
}
