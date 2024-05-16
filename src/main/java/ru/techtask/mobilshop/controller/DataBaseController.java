package ru.techtask.mobilshop.controller;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DataBaseController {
    private static DataBaseController instance;
//    private DataSource data;
    private Properties props;
    private String url;

    private DataBaseController(){}

    public static synchronized DataBaseController getInstance() {
        if (instance == null) {
            instance = new DataBaseController();
        }
        return instance;
    }

    public void init(String url, String user, String password) {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(url);
//        config.setUsername(user);
//        config.setPassword(password);
//        try {
//            data = new HikariDataSource(config);
//        } catch (HikariPool.PoolInitializationException e) {
//            System.out.println("ERROR: Check your database connection settings");
//            System.exit(-1);
//        }
        props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        this.url = url;
    }

    public void fillData(String string) {
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            InputStream inputStream = DataBaseController.class.getClassLoader().getResourceAsStream(string);
            assert inputStream != null;
            Scanner is = new Scanner(inputStream).useDelimiter(";");
            while (is.hasNext()) {
                statement.executeUpdate(is.next());
            }
            is.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public DataSource getDataSource() {
//        return data;
//    }
}
