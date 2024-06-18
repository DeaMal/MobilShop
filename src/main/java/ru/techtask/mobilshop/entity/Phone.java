package ru.techtask.mobilshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "processorid", nullable = false)
    private Integer processorid;

    @Column(name = "memorysize", nullable = false)
    private Integer memorysize;

    @Column(name = "display", nullable = false)
    private String display;

    @Column(name = "camera", nullable = false)
    private String camera;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "price", nullable = false)
    private Integer price;

    public Phone() {
    }
}
