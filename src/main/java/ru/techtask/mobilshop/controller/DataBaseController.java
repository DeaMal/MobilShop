package ru.techtask.mobilshop.controller;

import lombok.Getter;
import ru.techtask.mobilshop.logging.Logger;
import ru.techtask.mobilshop.model.PhoneModel;
import ru.techtask.mobilshop.model.TransactionModel;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

@Getter
public class DataBaseController {
    private static final Logger logger = new Logger();
    private static DataBaseController instance;
    private Properties props;
    private String url;

    private static final String SUCCESS = " IS SUCCESS";
    private static final String FAIL = " IS FAIL";

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
            logger.logFillData(string + SUCCESS);
        } catch (Exception e) {
            logger.logFillData(string + FAIL);
            throw new RuntimeException(e);
        }
    }

    public Integer makeQuery(String sqlQuery) {
        Integer result = null;
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            if (statement.execute(sqlQuery)) {
                ResultSet resultSet =  statement.getResultSet();
                resultSet.next();
                result = resultSet.getInt(1);
            } else {
                result = statement.getUpdateCount();
            }
            logger.logMakeQuery(sqlQuery + SUCCESS);
        } catch (SQLException e) {
            logger.logMakeQuery(sqlQuery + FAIL);
        }
        return result;
    }

    public List<String> listQuery(String queryString) {
        var result = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            logger.logListQuery(queryString + SUCCESS);
        } catch (SQLException e) {
            logger.logListQuery(queryString + FAIL);
        }
        return result;
    }

    public List<PhoneModel> listPhonesQuery(String queryString) {
        var result = new ArrayList<PhoneModel>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//                result.add(Q2Obj.fromResultSet(resultSet, PhoneModel.class));
                PhoneModel newPhone = PhoneModel.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .processorId(resultSet.getInt("processorid"))
                        .memorySize(resultSet.getInt("memorysize"))
                        .display(resultSet.getString("display"))
                        .camera(resultSet.getString("camera"))
                        .size(resultSet.getString("size"))
                        .price(resultSet.getInt("price"))
                        .build();
                result.add(newPhone);
            }
            logger.logListPhonesQuery(queryString + SUCCESS);
        } catch (SQLException e) {
            logger.logListPhonesQuery(queryString + FAIL);
        }
        return result;
    }

    public List<TransactionModel> listTransactionsQuery(String queryString) {
        var result = new ArrayList<TransactionModel>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TransactionModel newTransaction = TransactionModel.builder()
                        .id(resultSet.getInt("id"))
                        .goodId(resultSet.getInt("goodid"))
                        .amount(resultSet.getInt("amount"))
                        .status(resultSet.getString("status"))
                        .date(resultSet.getTimestamp("date"))
                        .build();
                result.add(newTransaction);
            }
            logger.logListTransactionsQuery(queryString + SUCCESS);
        } catch (SQLException e) {
            logger.logListTransactionsQuery(queryString + FAIL);
        }
        return result;
    }
}
