package ru.techtask.mobilshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import ru.techtask.mobilshop.model.Phone;
import ru.techtask.mobilshop.model.Transaction;
import ru.techtask.mobilshop.repository.*;
import java.util.Objects;

import static ru.techtask.mobilshop.Application.dataBaseInit;
import static ru.techtask.mobilshop.utils.Utils.convertStringToTimestamp;
import static ru.techtask.mobilshop.utils.Utils.tryParse;

@Slf4j
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

    @FXML
    private ChoiceBox<String> updateChoicePhone;

    @FXML
    private ChoiceBox<String> updateChoiceProcessor;

    @FXML
    private ChoiceBox<String> updateChoiceTransaction;

    @FXML
    private TextField updateTransactionAmount;

    @FXML
    private TextField updateTransactionData;

    @FXML
    private Label updateTransactionId;

    @FXML
    private ChoiceBox<String> updateTransactionPhone;

    @FXML
    private ChoiceBox<String> updateTransactionStatus;

    @FXML
    private TextField updatePhoneCamera;

    @FXML
    private TextField updatePhoneDisplay;

    @FXML
    private TextField updatePhoneMemory;

    @FXML
    private TextField updatePhoneName;

    @FXML
    private TextField updatePhonePrice;

    @FXML
    private ChoiceBox<String> updatePhoneProcessor;

    @FXML
    private TextField updatePhoneSize;

    @FXML
    private Label updatePhoneId;

    @FXML
    private TextField updateProcessor;

    @FXML
    private Label updateProcessorId;

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
        updateChoiceTransaction();
        setChoiceBoxStatus();
        welcomeText.setText("DataBase connect complete!");
    }

    @FXML
    void onAddProcessorButtonClick() {
        log.info("AddProcessor: {}", addProcessor.getText());
        if (Objects.nonNull(processors.addProcessor(addProcessor.getText()))) {
            updateChoiceBoxProcessors();
            log.info("Processor added: {}", addProcessor.getText());
            welcomeText.setText("Processor add success!");
        } else {
            log.info("Error adding processor: {}", addProcessor.getText());
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
            updateChoiceTransaction();
            welcomeText.setText("Transaction add success!");
        } else {
            welcomeText.setText("Error adding transaction!");
        }
    }

    @FXML
    void onUpdateChoicePhoneButtonClick() {
        if (Objects.nonNull(updateChoicePhone.getValue())) {
            Phone findPhone = phones.getPhone(updateChoicePhone.getValue());
            updatePhoneName.setText(findPhone.getName());
            updatePhoneId.setText(findPhone.getId().toString());
            updatePhoneProcessor.getItems().clear();
            processors.listProcessors().forEach(item -> updatePhoneProcessor.getItems().add(item));
            updatePhoneProcessor.setValue(updatePhoneProcessor.getItems().get(findPhone.getProcessorId() - 1));
            updatePhoneMemory.setText(findPhone.getMemorySize().toString());
            updatePhoneDisplay.setText(findPhone.getDisplay());
            updatePhoneCamera.setText(findPhone.getCamera());
            updatePhoneSize.setText(findPhone.getSize());
            updatePhonePrice.setText(findPhone.getPrice().toString());
        }
    }

    @FXML
    void onUpdatePhoneButtonClick() {
        if (Objects.nonNull(phones.updatePhone(phoneUpdateBuild()))) {
            updateChoiceBoxTransactionPhone();
            welcomeText.setText("Phone update success!");
        } else {
            welcomeText.setText("Error phone update!");
        }
    }

    @FXML
    void onUpdateChoiceProcessorButtonClick() {
        if (Objects.nonNull(updateChoiceProcessor.getValue())) {
            Integer findProcessorId = processors.getProcessorId(updateChoiceProcessor.getValue());
            updateProcessor.setText(updateChoiceProcessor.getValue());
            updateProcessorId.setText(findProcessorId.toString());
        }
    }

    @FXML
    void onUpdateProcessorButtonClick() {
        if (Objects.nonNull(processors.updateProcessor(tryParse(updateProcessorId.getText()), updateProcessor.getText()))) {
            updateChoiceBoxProcessors();
            welcomeText.setText("Processor update success!");
        } else {
            welcomeText.setText("Error update processor!");
        }
    }

    @FXML
    void onUpdateChoiceTransactionButtonClick() {
        if (Objects.nonNull(updateChoiceTransaction.getValue())) {
            Transaction findTransaction = transactions.getTransaction(tryParse(updateChoiceTransaction.getValue().split(" ")[0]));
            updateTransactionId.setText(findTransaction.getId().toString());
            updateTransactionPhone.getItems().clear();
            phones.listPhoneNames().forEach(item -> updateTransactionPhone.getItems().add(item));
            updateTransactionPhone.setValue(updateTransactionPhone.getItems().get(findTransaction.getGoodId() - 1));
            updateTransactionAmount.setText(findTransaction.getAmount().toString());
            updateTransactionStatus.setValue(findTransaction.getStatus());
            updateTransactionData.setText(findTransaction.getData().toString());
        }
    }

    @FXML
    void onUpdateTransactionButtonClick() {
        if (Objects.nonNull(transactions.updateTransaction(transactionUpdateBuild()))) {
            updateChoiceTransaction();
            welcomeText.setText("Transaction update success!");
        } else {
            welcomeText.setText("Error transaction update!");
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

    Phone phoneUpdateBuild() throws NumberFormatException {
        return Phone.builder()
                .id(tryParse(updatePhoneId.getText()))
                .name(updatePhoneName.getText())
                .processorId(updatePhoneProcessor.getSelectionModel().getSelectedIndex() + 1)
                .memorySize(tryParse(updatePhoneMemory.getText()))
                .display(updatePhoneDisplay.getText())
                .camera(updatePhoneCamera.getText())
                .size(updatePhoneSize.getText())
                .price(tryParse(updatePhonePrice.getText()))
                .build();
    }

    Transaction transactionBuild() throws NumberFormatException {
        return Transaction.builder()
                .goodId(addTransactionPhone.getSelectionModel().getSelectedIndex() + 1)
                .amount(tryParse(addTransactionAmount.getText()))
                .status(addTransactionStatus.getValue())
                .build();
    }

    Transaction transactionUpdateBuild() throws NumberFormatException {
        return Transaction.builder()
                .id(tryParse(updateTransactionId.getText()))
                .goodId(updateTransactionPhone.getSelectionModel().getSelectedIndex() + 1)
                .amount(tryParse(updateTransactionAmount.getText()))
                .status(updateTransactionStatus.getValue())
                .data(convertStringToTimestamp(updateTransactionData.getText()))
                .build();
    }

    void updateChoiceBoxProcessors() {
        addPhoneProcessor.getItems().clear();
        updateChoiceProcessor.getItems().clear();
        processors.listProcessors().forEach(item -> {
            addPhoneProcessor.getItems().add(item);
            updateChoiceProcessor.getItems().add(item);
        });
        addPhoneProcessor.setValue(addPhoneProcessor.getItems().get(0));
        updateChoiceProcessor.setValue(updateChoiceProcessor.getItems().get(0));
    }

    void updateChoiceBoxTransactionPhone() {
        addTransactionPhone.getItems().clear();
        updateChoicePhone.getItems().clear();
        updateTransactionPhone.getItems().clear();
        phones.listPhoneNames().forEach(item -> {
            addTransactionPhone.getItems().add(item);
            updateChoicePhone.getItems().add(item);
            updateTransactionPhone.getItems().add(item);
        });
        addTransactionPhone.setValue(addTransactionPhone.getItems().get(0));
        updateChoicePhone.setValue(updateChoicePhone.getItems().get(0));
    }

    void updateChoiceTransaction() {
        updateChoiceTransaction.getItems().clear();
        transactions.getListTransactions().forEach(item -> updateChoiceTransaction.getItems().add(item));
        updateChoiceTransaction.setValue(updateChoiceTransaction.getItems().get(0));
    }

    void setChoiceBoxStatus() {
        addTransactionStatus.getItems().addAll("ARRIVED", "SOLD", "OTHER");
        addTransactionStatus.setValue(addTransactionStatus.getItems().get(0));
        updateTransactionStatus.getItems().addAll("ARRIVED", "SOLD", "OTHER");
    }
}