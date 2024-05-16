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
        dataBaseInit();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void dataBaseInit() {
        String baseURL = "jdbc:postgresql://localhost:5432/postgres";
        String baseUser = "postgres";
        String basePassword = "postgres";
        var dataBase = DataBaseController.getInstance();
        dataBase.init(baseURL, baseUser, basePassword);
        dataBase.fillData("ru/techtask/mobilshop/schema.sql");
        dataBase.fillData("ru/techtask/mobilshop/data.sql");
    }
}