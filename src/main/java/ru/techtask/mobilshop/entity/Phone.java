package ru.techtask.mobilshop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer processorid;
    private Integer memorysize;
    private String display;
    private String camera;
    private String size;
    private Integer price;

    public Phone() {
    }
}
