package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessorsImpl implements Processors {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public List<String> listProcessors() {
        var listProcessors = new ArrayList<String>();
        String queryString ="SELECT mobile_shop.processors.description FROM mobile_shop.processors;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                listProcessors.add(resultSet.getString(1));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProcessors;
    }

    @Override
    public Integer addProcessor(String processor) {
        return data.makeQuery("insert into mobile_shop.processors(description) values ('" + processor + "')");
    }
}
