package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class TransactionModel {
    Integer id;
    Integer goodId;
    Integer amount;
    String status;
    Timestamp data;
    String phoneName;

    @Override
    public String toString() {
        return "{id:" + id + ", phoneId:'" + goodId + "', " + "amount:" + amount
                + ", status:'" + status + "', data:'" + data + "'}";
    }
}
