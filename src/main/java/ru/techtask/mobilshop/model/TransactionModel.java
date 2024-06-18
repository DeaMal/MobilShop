package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;
import ru.techtask.mobilshop.entity.Transaction;

import java.sql.Timestamp;

@Builder
@Data
public class TransactionModel {
    Integer id;
    Integer goodId;
    Integer amount;
    String status;
    Timestamp date;
    String phoneName;

    public static TransactionModel toModel(Transaction transaction) {
        return TransactionModel.builder()
                .id(transaction.getId())
                .goodId(transaction.getGoodid())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .date(transaction.getDate())
                .build();
    }

    @Override
    public String toString() {
        return "{id:" + id + ", phoneId:'" + goodId + "', " + "amount:" + amount
                + ", status:'" + status + "', data:'" + date + "'}";
    }
}
