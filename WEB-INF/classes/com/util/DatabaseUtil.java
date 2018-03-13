package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
           return connection;
        else {
            try {
            	//Properties prop = new Properties();
                //InputStream inputStream = DatabaseUtil.class.getClassLoader().getResourceAsStream("/db.properties");
                //prop.load(inputStream);
                //String driver = prop.getProperty("driver");
//                String url = prop.getProperty("localhost");
//                String dbName = prop.getProperty("osn");
//                String port = prop.getProperty("3306");
//                String user = prop.getProperty("root");
//                String password = prop.getProperty"root");
                  String url = "localhost";
              String dbName = "osn";
                String port = "3306";
                String user = "root";
                String password = "root";
                
                Class.forName("com.mysql.jdbc.Driver");
                String jdbcUrl = "jdbc:mysql://" + url + ":" + port + "/" + dbName + "?user=" + user + "&password=" + password;
                connection = DriverManager.getConnection(jdbcUrl);          
          
            
                
                
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
                
            
        }

    }
            return connection;
    
    }
}
