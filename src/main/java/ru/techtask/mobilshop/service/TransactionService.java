package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Processors;
import ru.techtask.mobilshop.entity.Transaction;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.TransactionModel;
import ru.techtask.mobilshop.repository.PhoneRepo;
import ru.techtask.mobilshop.repository.TransactionRepo;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private PhoneRepo phoneRepo;

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

    public Transaction updateTransaction(Transaction transaction, Integer id) throws ItemAlreadyExistException {
        transactionRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Transaction not found"));
        phoneRepo.findById(transaction.getGoodid()).orElseThrow(() -> new ItemNotFoundException("Phone with goodId=" + transaction.getGoodid() + " not found"));
        if (Objects.isNull(transaction.getDate())) {
            transaction.setDate(new Timestamp(System.currentTimeMillis()));
        }
        transaction.setId(id);
        return transactionRepo.save(transaction);
    }
}
