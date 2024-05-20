package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.Transaction;

import java.sql.*;
import java.util.List;

public class TransactionsImpl implements Transactions {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public Integer addTransaction(Transaction newTransaction) {
        String queryString = "insert into mobile_shop.transaction(goodid, amount, status) values ("
                + "(SELECT id FROM mobile_shop.phone WHERE name LIKE '" + newTransaction.getPhoneName() + "'), "
                + newTransaction.getAmount() + ", '" + newTransaction.getStatus() + "');";
        return data.makeQuery(queryString);
    }

    @Override
    public List<String> getListTransactionNames() {
        String queryString ="SELECT concat(mobile_shop.transaction.id, ' ', p.name, '_', "
        + "mobile_shop.transaction.amount, '_', mobile_shop.transaction.status) AS ab FROM mobile_shop.transaction "
        + "JOIN mobile_shop.phone p on p.id = transaction.goodid;";
        return data.listQuery(queryString);
    }

    @Override
    public Transaction getTransaction(Integer transactionId) {
        Transaction result = null;
        String queryString ="SELECT transaction.id AS id, goodid, amount, status, \"Date\", name "
                + "FROM mobile_shop.transaction JOIN mobile_shop.phone p on p.id = transaction.goodid "
                + "WHERE mobile_shop.transaction.id = ?;";
        try {
            Connection connection = DriverManager.getConnection(data.getUrl(), data.getProps());
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Transaction.builder()
                        .id(resultSet.getInt(1))
                        .goodId(resultSet.getInt(2))
                        .amount(resultSet.getInt(3))
                        .status(resultSet.getString(4))
                        .data(resultSet.getTimestamp(5))
                        .phoneName(resultSet.getString(6))
                        .build();
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateTransaction(Transaction updateTransaction) {
        String queryString ="UPDATE mobile_shop.transaction SET goodid = "
                + "(SELECT id FROM mobile_shop.phone WHERE name LIKE '" + updateTransaction.getPhoneName()
                + "'), amount = " + updateTransaction.getAmount() + ", status = '" + updateTransaction.getStatus()
                + "', \"Date\" = '" + updateTransaction.getData() + "' WHERE mobile_shop.transaction.id = "
                + updateTransaction.getId() +";";
        return data.makeQuery(queryString);
    }

    @Override
    public Integer deleteTransaction(Integer transactionId) {
        String queryString ="DELETE FROM mobile_shop.transaction WHERE mobile_shop.transaction.id = "
                + transactionId +";";
        return data.makeQuery(queryString);
    }

    @Override
    public List<Transaction> listTransactions() {
        String queryString ="SELECT * FROM mobile_shop.transaction;";
        return data.listTransactionsQuery(queryString);
    }
}
