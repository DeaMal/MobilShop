package ru.techtask.mobilshop.controller;

import lombok.Getter;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

@Getter
public class DataBaseController {
    private static DataBaseController instance;
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

    public Integer makeQuery(String sqlQuery) {
        Integer result = null;
        try {
            Connection connection = DriverManager.getConnection(url, props);
            Statement statement = connection.createStatement();
            if (statement.execute(sqlQuery)) {
                ResultSet resultSet =  statement.getResultSet();
                resultSet.next();
                result = resultSet.getInt(1);
            } else {
                result = statement.getUpdateCount();
            }
            connection.close();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
        return result;
    }

}
