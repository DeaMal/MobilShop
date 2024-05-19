package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.Phone;

import java.sql.*;
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
        String queryString ="SELECT mobile_shop.phone.name FROM mobile_shop.phone;";
        return data.listQuery(queryString);
    }

    @Override
    public Phone getPhone(String findPhone) {
        Phone result = null;
        String queryString ="SELECT * FROM mobile_shop.phone WHERE mobile_shop.phone.name LIKE ?;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, findPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Phone.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .processorId(resultSet.getInt(3))
                        .memorySize(resultSet.getInt(4))
                        .display(resultSet.getString(5))
                        .camera(resultSet.getString(6))
                        .size(resultSet.getString(7))
                        .price(resultSet.getInt(8))
                        .build();
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updatePhone(Phone updatePhone) {
        String queryString ="UPDATE mobile_shop.phone SET name = '" + updatePhone.getName() + "', processorid = "
                + updatePhone.getProcessorId() + ", memorysize = " + updatePhone.getMemorySize() + ", display = '"
                + updatePhone.getDisplay() + "', camera = '" + updatePhone.getCamera() + "', size = '"
                + updatePhone.getSize() + "', price = " + updatePhone.getPrice() + " WHERE mobile_shop.phone.id = "
                + updatePhone.getId() +";";
        return data.makeQuery(queryString);
    }
}
