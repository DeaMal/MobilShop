package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.model.TransactionModel;

import java.util.List;

public interface Transactions {
    Integer addTransaction(TransactionModel newTransaction);

    List<String> getListTransactionNames();

    TransactionModel getTransaction(Integer transactionId);

    Integer updateTransaction(TransactionModel updateTransaction);

    Integer deleteTransaction(Integer transactionId);

    List<TransactionModel> listTransactions();
}
