module ru.techtask.mobilshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires org.slf4j;
    requires org.apache.commons.collections4;

    opens ru.techtask.mobilshop to javafx.fxml;
    exports ru.techtask.mobilshop;
    exports ru.techtask.mobilshop.controller;
    exports ru.techtask.mobilshop.model;
    opens ru.techtask.mobilshop.controller to javafx.fxml;
}