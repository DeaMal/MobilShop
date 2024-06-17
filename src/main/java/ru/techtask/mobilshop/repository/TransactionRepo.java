package ru.techtask.mobilshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.techtask.mobilshop.entity.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
