package ru.techtask.mobilshop.entity;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
@Check(constraints = "status IN ('ARRIVED','SOLD','OTHER')")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goodid", nullable = false)
    private Integer goodid;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date", nullable = false)
    @ColumnDefault("current_timestamp::TIMESTAMP(0)")
    private Timestamp date;

    public Transaction() {
    }
}
