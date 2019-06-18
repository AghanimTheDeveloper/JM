package dao.util;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppStart {
    private static Connection connection;
    private static String scriptLine;
    private static StringBuilder scriptBuilder;

    public AppStart() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    public static void init(){
        String delimiter = ";";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("db_init.sql")))){
            while ((scriptLine = bufferedReader.readLine()) != null){
                scriptBuilder.append(scriptLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        String[] script = scriptBuilder.toString().split(";");

        try (Statement statement = connection.createStatement()){
            for (String scriptElement : script){
                if(!scriptElement.trim().equals("")){
                    statement.executeUpdate(scriptElement);
                    System.out.println(">>" + scriptElement);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
