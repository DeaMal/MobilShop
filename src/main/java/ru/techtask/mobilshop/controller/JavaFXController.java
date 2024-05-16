package ru.techtask.mobilshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JavaFXController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}