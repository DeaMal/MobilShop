package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.model.Transaction;

public interface Transactions {
    Integer addTransaction(Transaction newTransaction);
}
