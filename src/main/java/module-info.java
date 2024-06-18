module ru.techtask.mobilshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires org.slf4j;
    requires org.apache.commons.collections4;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires java.persistence;
    requires spring.data.commons;
    requires spring.beans;
    requires spring.context;
    requires org.hibernate.orm.core;

    opens ru.techtask.mobilshop to javafx.fxml;
    exports ru.techtask.mobilshop;
    exports ru.techtask.mobilshop.controller;
    exports ru.techtask.mobilshop.model;
    exports ru.techtask.mobilshop.entity;
    opens ru.techtask.mobilshop.controller to javafx.fxml;
}