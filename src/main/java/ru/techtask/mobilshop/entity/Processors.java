package ru.techtask.mobilshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "processors")
public class Processors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    public Processors() {
    }
}
