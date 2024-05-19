package ru.techtask.mobilshop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.techtask.mobilshop.controller.DataBaseController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("PhoneBase!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void dataBaseInit() {
        var dataBase = DataBaseController.getInstance();
        dataBase.fillData("ru/techtask/mobilshop/drop.sql");
        dataBase.fillData("ru/techtask/mobilshop/schema.sql");
        dataBase.fillData("ru/techtask/mobilshop/data.sql");
    }

    public static void dataBaseConnect(String baseURL, String baseUser, String basePassword) {
        var dataBase = DataBaseController.getInstance();
        dataBase.init(baseURL, baseUser, basePassword);
        dataBase.fillData("ru/techtask/mobilshop/schema.sql");
    }
}