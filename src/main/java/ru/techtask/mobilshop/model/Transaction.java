package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Transaction {
    Integer goodId;
    Integer amount;
    String status;
}
