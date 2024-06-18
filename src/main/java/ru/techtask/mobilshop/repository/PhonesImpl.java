package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.PhoneModel;

import java.sql.*;
import java.util.List;

public class PhonesImpl implements Phones {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public Integer addPhone(PhoneModel newPhone) {
        String queryString = "insert into mobile_shop.phone(name, processorid, memorysize, display, camera, size, price) values ('"
                + newPhone.getName() + "', (SELECT id FROM mobile_shop.processors WHERE description LIKE '"
                + newPhone.getProcessorName() + "'), " + newPhone.getMemorySize() + ", '" + newPhone.getDisplay()
                + "', '" + newPhone.getCamera() + "', '" + newPhone.getSize() + "', " + newPhone.getPrice() + ")";
        return data.makeQuery(queryString);
    }

    @Override
    public List<String> listPhoneNames() {
        String queryString ="SELECT mobile_shop.phone.name FROM mobile_shop.phone;";
        return data.listQuery(queryString);
    }

    @Override
    public PhoneModel getPhone(String findPhone) {
        PhoneModel result = null;
        String queryString ="SELECT phone.id AS id, name, processorid, memorysize, display, camera, size, price, "
                + "description FROM mobile_shop.phone join mobile_shop.processors p on p.id = phone.processorid "
                + "WHERE mobile_shop.phone.name LIKE ?;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, findPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = PhoneModel.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .processorId(resultSet.getInt(3))
                        .memorySize(resultSet.getInt(4))
                        .display(resultSet.getString(5))
                        .camera(resultSet.getString(6))
                        .size(resultSet.getString(7))
                        .price(resultSet.getInt(8))
                        .processorName(resultSet.getString(9))
                        .build();
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updatePhone(PhoneModel updatePhone) {
        String queryString ="UPDATE mobile_shop.phone SET name = '" + updatePhone.getName()
                + "', processorid = (SELECT id FROM mobile_shop.processors WHERE description LIKE '"
                + updatePhone.getProcessorName() + "'), memorysize = " + updatePhone.getMemorySize() + ", display = '"
                + updatePhone.getDisplay() + "', camera = '" + updatePhone.getCamera() + "', size = '"
                + updatePhone.getSize() + "', price = " + updatePhone.getPrice() + " WHERE mobile_shop.phone.id = "
                + updatePhone.getId() +";";
        return data.makeQuery(queryString);
    }

    @Override
    public Integer deletePhone(String findPhone, Boolean cascade) {
        String queryString = "";
        if (cascade) {
            queryString ="DELETE FROM mobile_shop.transaction WHERE goodid = "
                    + "(SELECT id FROM mobile_shop.phone WHERE name LIKE '" + findPhone + "');\n";
        }
        queryString += "DELETE FROM mobile_shop.phone WHERE name LIKE '" + findPhone + "';";
        return data.makeQuery(queryString);
    }

    @Override
    public List<PhoneModel> listPhones() {
        String queryString ="SELECT * FROM mobile_shop.phone;";
        return data.listPhonesQuery(queryString);
    }
}
