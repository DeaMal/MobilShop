module ru.techtask.mobilshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens ru.techtask.mobilshop to javafx.fxml;
    exports ru.techtask.mobilshop;
    exports ru.techtask.mobilshop.controller;
    opens ru.techtask.mobilshop.controller to javafx.fxml;
}