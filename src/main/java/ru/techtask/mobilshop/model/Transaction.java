package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class Transaction {
    Integer id;
    Integer goodId;
    Integer amount;
    String status;
    Timestamp data;
}
