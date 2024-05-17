package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhonesImpl implements Phones {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public Integer addPhone(Phone newPhone) {
        String queryString = "insert into mobile_shop.phone values (default, '" + newPhone.getName() + "', "
                + newPhone.getProcessorId() + ", " + newPhone.getMemorySize() + ", '" + newPhone.getDisplay() +
                "', '" + newPhone.getCamera() + "', '" + newPhone.getSize() + "', " + newPhone.getPrice() + ")";
        return data.makeQuery(queryString);
    }

    @Override
    public List<String> listPhoneNames() {
        var listPhones = new ArrayList<String>();
        String queryString ="SELECT mobile_shop.phone.name FROM mobile_shop.phone;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                listPhones.add(resultSet.getString(1));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPhones;
    }
}
