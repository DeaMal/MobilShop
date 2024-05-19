package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.model.Transaction;

import java.util.List;

public interface Transactions {
    Integer addTransaction(Transaction newTransaction);

    List<String> getListTransactionNames();

    Transaction getTransaction(Integer transactionId);

    Integer updateTransaction(Transaction updateTransaction);

    Integer deleteTransaction(Integer transactionId);

    List<Transaction> listTransactions();
}
