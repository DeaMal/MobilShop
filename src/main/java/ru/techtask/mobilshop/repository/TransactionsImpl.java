package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.Transaction;

public class TransactionsImpl implements Transactions {
    private final DataBaseController data = DataBaseController.getInstance();

    @Override
    public Integer addTransaction(Transaction newTransaction) {
        String queryString = "insert into mobile_shop.transaction(goodid, amount, status) values ("
                + newTransaction.getGoodId() + ", " + newTransaction.getAmount() + ", '"
                + newTransaction.getStatus() + "');";
        return data.makeQuery(queryString);
    }
}
