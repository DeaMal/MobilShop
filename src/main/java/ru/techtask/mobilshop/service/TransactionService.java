package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Transaction;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.TransactionModel;
import ru.techtask.mobilshop.repository.TransactionRepo;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction addNewTransaction(Transaction newTransaction) {
        if (Objects.isNull(newTransaction.getDate())) {
            newTransaction.setDate(new Timestamp(System.currentTimeMillis()));
        }
        return transactionRepo.save(newTransaction);
    }

    public TransactionModel getTransactionById(Integer transactionId) {
        return TransactionModel.toModel(transactionRepo.findById(transactionId).orElseThrow(() -> new ItemNotFoundException("Transaction not found")));
    }

    public Integer deleteTransactionById(Integer transactionId) {
        transactionRepo.deleteById(transactionId);
        return transactionId;
    }
}
