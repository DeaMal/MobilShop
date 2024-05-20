package ru.techtask.mobilshop.controller;

import lombok.Getter;
import ru.techtask.mobilshop.logging.Logger;
import ru.techtask.mobilshop.model.Phone;
import ru.techtask.mobilshop.model.Transaction;

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

    public List<Phone> listPhonesQuery(String queryString) {
        var result = new ArrayList<Phone>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                Phone newPhone = Phone.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .processorId(resultSet.getInt(3))
                        .memorySize(resultSet.getInt(4))
                        .display(resultSet.getString(5))
                        .camera(resultSet.getString(6))
                        .size(resultSet.getString(7))
                        .price(resultSet.getInt(8))
                        .build();
                result.add(newPhone);
            }
            logger.logListPhonesQuery(queryString + SUCCESS);
        } catch (SQLException e) {
            logger.logListPhonesQuery(queryString + FAIL);
        }
        return result;
    }

    public List<Transaction> listTransactionsQuery(String queryString) {
        var result = new ArrayList<Transaction>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                Transaction newTransaction = Transaction.builder()
                        .id(resultSet.getInt(1))
                        .goodId(resultSet.getInt(2))
                        .amount(resultSet.getInt(3))
                        .status(resultSet.getString(4))
                        .data(resultSet.getTimestamp(5))
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
