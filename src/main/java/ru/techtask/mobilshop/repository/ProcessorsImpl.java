package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;

import java.sql.*;
import java.util.List;

public class ProcessorsImpl implements Processors {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public List<String> listProcessors() {
        String queryString ="SELECT mobile_shop.processors.description FROM mobile_shop.processors;";
        return data.listQuery(queryString);
    }

    @Override
    public Integer addProcessor(String processorName) {
        return data.makeQuery("insert into mobile_shop.processors(description) values ('" + processorName + "')");
    }

    @Override
    public Integer getProcessorId(String processorName) {
        Integer result = null;
        String queryString ="SELECT id FROM mobile_shop.processors WHERE mobile_shop.processors.description LIKE ?;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, processorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateProcessor(Integer processorId, String processorname) {
        String queryString ="UPDATE mobile_shop.processors SET description = '" + processorname
                + "' WHERE mobile_shop.processors.id = " + processorId +";";
        return data.makeQuery(queryString);
    }

    @Override
    public Integer deleteProcessor(String processorName, Boolean cascade) {
        String queryString = "";
        if (cascade) {
            queryString = "DELETE FROM mobile_shop.transaction WHERE goodid = "
                    + "(SELECT id FROM mobile_shop.phone WHERE processorid = "
                    + "(SELECT id FROM mobile_shop.processors WHERE description LIKE '" + processorName + "'));\n"
                    + "DELETE FROM mobile_shop.phone WHERE processorid = "
                    + "(SELECT id FROM mobile_shop.processors WHERE description LIKE '" + processorName + "');\n";
        }
        queryString += "DELETE FROM mobile_shop.processors WHERE description LIKE '" + processorName + "';";
        return data.makeQuery(queryString);
    }
}
