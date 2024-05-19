package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class Transaction {
    Integer id;
    Integer goodId;
    String phoneName;
    Integer amount;
    String status;
    Timestamp data;

    @Override
    public String toString() {
        return "{id:" + id + ", phoneId:'" + goodId + "', " + "amount:" + amount
                + ", status:'" + status + "', data:'" + data + "'}";
    }
}
