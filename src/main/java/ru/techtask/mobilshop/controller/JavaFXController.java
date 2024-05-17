package ru.techtask.mobilshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.techtask.mobilshop.model.Phone;
import ru.techtask.mobilshop.model.Transaction;
import ru.techtask.mobilshop.repository.*;

import java.util.Objects;

import static ru.techtask.mobilshop.Application.dataBaseInit;

public class JavaFXController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField basePassword;

    @FXML
    private TextField baseUrl;

    @FXML
    private TextField baseUser;

    @FXML
    private TextField addPhoneCamera;

    @FXML
    private TextField addPhoneDisplay;

    @FXML
    private TextField addPhoneMemory;

    @FXML
    private TextField addPhoneName;

    @FXML
    private TextField addPhonePrice;

    @FXML
    private ChoiceBox<String> addPhoneProcessor;

    @FXML
    private TextField addPhoneSize;

    @FXML
    private TextField addProcessor;

    @FXML
    private TextField addTransactionAmount;

    @FXML
    private ChoiceBox<String> addTransactionPhone;

    @FXML
    private ChoiceBox<String> addTransactionStatus;

    private final Processors processors = new ProcessorsImpl();
    private final Phones phones = new PhonesImpl();
    private final Transactions transactions = new TransactionsImpl();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onInitButtonClick() {
        dataBaseInit(baseUrl.getText(), baseUser.getText(), basePassword.getText());
        updateChoiceBoxProcessors();
        updateChoiceBoxTransactionPhone();
        setChoiceBoxStatus();
        welcomeText.setText("DataBase connect complete!");
    }

    @FXML
    void onAddProcessorButtonClick() {
        if (Objects.nonNull(processors.addProcessor(addProcessor.getText()))) {
            updateChoiceBoxProcessors();
            welcomeText.setText("Processor add success!");
        } else {
            welcomeText.setText("Error adding processor!");
        }
    }

    @FXML
    void onAddPhoneButtonClick() {
        if (Objects.nonNull(phones.addPhone(phoneBuild()))) {
            updateChoiceBoxTransactionPhone();
            welcomeText.setText("Phone add success!");
        } else {
            welcomeText.setText("Error phone add!");
        }
    }

    @FXML
    void onAddTransactionButtonClick() {
        // TO DO Check ARRIVED > SOLD
        if (Objects.nonNull(transactions.addTransaction(transactionBuild()))) {
            welcomeText.setText("Transaction add success!");
        } else {
            welcomeText.setText("Error adding transaction!");
        }
    }

    Phone phoneBuild() throws NumberFormatException {
        return Phone.builder()
                .name(addPhoneName.getText())
                .processorId(addPhoneProcessor.getSelectionModel().getSelectedIndex() + 1)
                .memorySize(tryParse(addPhoneMemory.getText()))
                .display(addPhoneDisplay.getText())
                .camera(addPhoneCamera.getText())
                .size(addPhoneSize.getText())
                .price(tryParse(addPhonePrice.getText()))
                .build();
    }

    Transaction transactionBuild() throws NumberFormatException {
        return Transaction.builder()
                .goodId(addTransactionPhone.getSelectionModel().getSelectedIndex() + 1)
                .amount(tryParse(addTransactionAmount.getText()))
                .status(addTransactionStatus.getValue())
                .build();
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    void updateChoiceBoxProcessors() {
        addPhoneProcessor.getItems().clear();
        processors.listProcessors().forEach(item -> addPhoneProcessor.getItems().add(item));
        addPhoneProcessor.setValue(addPhoneProcessor.getItems().get(0));
    }

    void updateChoiceBoxTransactionPhone() {
        addTransactionPhone.getItems().clear();
        phones.listPhoneNames().forEach(item -> addTransactionPhone.getItems().add(item));
        addTransactionPhone.setValue(addTransactionPhone.getItems().get(0));
    }

    void setChoiceBoxStatus() {
        addTransactionStatus.getItems().addAll("ARRIVED", "SOLD", "OTHER");
        addTransactionStatus.setValue(addTransactionStatus.getItems().get(0));
    }
}