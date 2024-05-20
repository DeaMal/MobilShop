package ru.techtask.mobilshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.techtask.mobilshop.model.Phone;
import ru.techtask.mobilshop.model.Transaction;
import ru.techtask.mobilshop.repository.*;

import java.util.Objects;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static ru.techtask.mobilshop.Application.dataBaseConnect;
import static ru.techtask.mobilshop.Application.dataBaseInit;
import static ru.techtask.mobilshop.utils.Utils.convertStringToTimestamp;
import static ru.techtask.mobilshop.utils.Utils.tryParse;

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

    @FXML
    private ChoiceBox<String> deleteChoicePhone;

    @FXML
    private ChoiceBox<String> deleteChoiceProcessor;

    @FXML
    private ChoiceBox<String> deleteChoiceTransaction;

    @FXML
    private CheckBox deletePhoneCheckBox;

    @FXML
    private CheckBox deleteProcessorCheckBox;

    @FXML
    private ListView<String> readPhonesList;

    @FXML
    private ListView<String> readProcessorsList;

    @FXML
    private ListView<String> readTransactionsList;

    private final Processors processors = new ProcessorsImpl();
    private final Phones phones = new PhonesImpl();
    private final Transactions transactions = new TransactionsImpl();

    @FXML
    void onConnectButtonClick() {
        dataBaseConnect(baseUrl.getText(), baseUser.getText(), basePassword.getText());
        setChoiceBoxStatus();
        refresh();
        welcomeText.setText("DataBase connect complete!");
    }

    @FXML
    void onInitButtonClick() {
        dataBaseInit();
        refresh();
        welcomeText.setText("DataBase clean and initialization complete!");
    }

    void refresh() {
        updateChoiceBoxProcessors();
        updateChoiceBoxTransactionPhone();
        updateChoiceTransaction();
    }

    @FXML
    void onAddProcessorButtonClick() {
        if (!addProcessor.getText().isEmpty()) {
            if (Objects.nonNull(processors.addProcessor(addProcessor.getText()))) {
                updateChoiceBoxProcessors();
                welcomeText.setText("Processor add success!");
            } else {
                welcomeText.setText("Error adding processor!");
            }
        }
    }

    @FXML
    void onAddPhoneButtonClick() {
        if (Objects.nonNull(addPhoneProcessor.getValue())) {
            if (Objects.nonNull(phones.addPhone(phoneBuild()))) {
                updateChoiceBoxTransactionPhone();
                welcomeText.setText("Phone add success!");
            } else {
                welcomeText.setText("Error phone add!");
            }
        }
    }

    @FXML
    void onAddTransactionButtonClick() {
        if (Objects.nonNull(addTransactionPhone.getValue())) {
            // TO DO Check ARRIVED > SOLD
            if (Objects.nonNull(transactions.addTransaction(transactionBuild()))) {
                updateChoiceTransaction();
                welcomeText.setText("Transaction add success!");
            } else {
                welcomeText.setText("Error adding transaction!");
            }
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
            updatePhoneProcessor.setValue(findPhone.getProcessorName());
            updatePhoneMemory.setText(findPhone.getMemorySize().toString());
            updatePhoneDisplay.setText(findPhone.getDisplay());
            updatePhoneCamera.setText(findPhone.getCamera());
            updatePhoneSize.setText(findPhone.getSize());
            updatePhonePrice.setText(findPhone.getPrice().toString());
        }
    }

    @FXML
    void onUpdatePhoneButtonClick() {
        if (Objects.nonNull(updatePhoneProcessor.getValue())) {
            if (Objects.nonNull(phones.updatePhone(phoneUpdateBuild()))) {
                updateChoiceBoxTransactionPhone();
                welcomeText.setText("Phone update success!");
            } else {
                welcomeText.setText("Error phone update!");
            }
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
        if (!updateProcessor.getText().isEmpty()) {
            if (Objects.nonNull(processors.updateProcessor(tryParse(updateProcessorId.getText()), updateProcessor.getText()))) {
                updateChoiceBoxProcessors();
                welcomeText.setText("Processor update success!");
            } else {
                welcomeText.setText("Error update processor!");
            }
        }
    }

    @FXML
    void onUpdateChoiceTransactionButtonClick() {
        if (Objects.nonNull(updateChoiceTransaction.getValue())) {
            Transaction findTransaction = transactions.getTransaction(tryParse(updateChoiceTransaction.getValue().split(" ")[0]));
            updateTransactionId.setText(findTransaction.getId().toString());
            updateTransactionPhone.getItems().clear();
            phones.listPhoneNames().forEach(item -> updateTransactionPhone.getItems().add(item));
            updateTransactionPhone.setValue(findTransaction.getPhoneName());
            updateTransactionAmount.setText(findTransaction.getAmount().toString());
            updateTransactionStatus.setValue(findTransaction.getStatus());
            updateTransactionData.setText(findTransaction.getData().toString());
        }
    }

    @FXML
    void onUpdateTransactionButtonClick() {
        if (Objects.nonNull(updateTransactionPhone.getValue())) {
            if (Objects.nonNull(transactions.updateTransaction(transactionUpdateBuild()))) {
                updateChoiceTransaction();
                welcomeText.setText("Transaction update success!");
            } else {
                welcomeText.setText("Error transaction update!");
            }
        }
    }

    @FXML
    void onDeletePhoneButtonClick() {
        if (Objects.nonNull(deleteChoicePhone.getValue())) {
            if (Objects.nonNull(phones.deletePhone(deleteChoicePhone.getValue(), deletePhoneCheckBox.isSelected()))) {
                updateChoiceBoxTransactionPhone();
                updateChoiceTransaction();
                welcomeText.setText("Phone delete success!");
            } else {
                welcomeText.setText("Error Phone delete!");
            }
        }
    }

    @FXML
    void onDeleteProcessorButtonClick() {
        if (Objects.nonNull(deleteChoiceProcessor.getValue())) {
            if (Objects.nonNull(processors.deleteProcessor(deleteChoiceProcessor.getValue(), deleteProcessorCheckBox.isSelected()))) {
                refresh();
                welcomeText.setText("Processor delete success!");
            } else {
                welcomeText.setText("Error Processor delete!");
            }
        }
    }

    @FXML
    void onDeleteTransactionButtonClick() {
        if (Objects.nonNull(deleteChoiceTransaction.getValue())) {
            Integer findTransaction = tryParse(deleteChoiceTransaction.getValue().split(" ")[0]);
            if (Objects.nonNull(transactions.deleteTransaction(findTransaction))) {
                updateChoiceTransaction();
                welcomeText.setText("Transaction delete success!");
            } else {
                welcomeText.setText("Error transaction delete!");
            }
        }
    }

    @FXML
    void onReadPhoneButtonClick() {
        readPhonesList.getItems().clear();
        phones.listPhones().forEach(item -> readPhonesList.getItems().add(item.toString()));
    }

    @FXML
    void onReadProcessorsButtonClick() {
        readProcessorsList.getItems().clear();
        processors.listProcessors().forEach(item -> readProcessorsList.getItems().add(item));
    }

    @FXML
    void onReadTransactionsButtonClick() {
        readTransactionsList.getItems().clear();
        transactions.listTransactions().forEach(item -> readTransactionsList.getItems().add(item.toString()));
    }

    Phone phoneBuild() throws NumberFormatException {
        return Phone.builder()
                .name(addPhoneName.getText())
                .processorName(addPhoneProcessor.getValue())
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
                .processorName(updatePhoneProcessor.getValue())
                .memorySize(tryParse(updatePhoneMemory.getText()))
                .display(updatePhoneDisplay.getText())
                .camera(updatePhoneCamera.getText())
                .size(updatePhoneSize.getText())
                .price(tryParse(updatePhonePrice.getText()))
                .build();
    }

    Transaction transactionBuild() throws NumberFormatException {
        return Transaction.builder()
                .phoneName(addTransactionPhone.getValue())
                .amount(tryParse(addTransactionAmount.getText()))
                .status(addTransactionStatus.getValue())
                .build();
    }

    Transaction transactionUpdateBuild() throws NumberFormatException {
        return Transaction.builder()
                .id(tryParse(updateTransactionId.getText()))
                .phoneName(updateTransactionPhone.getValue())
                .amount(tryParse(updateTransactionAmount.getText()))
                .status(updateTransactionStatus.getValue())
                .data(convertStringToTimestamp(updateTransactionData.getText()))
                .build();
    }

    void updateChoiceBoxProcessors() {
        addPhoneProcessor.getItems().clear();
        updateChoiceProcessor.getItems().clear();
        deleteChoiceProcessor.getItems().clear();
        processors.listProcessors().forEach(item -> {
            addPhoneProcessor.getItems().add(item);
            updateChoiceProcessor.getItems().add(item);
            deleteChoiceProcessor.getItems().add(item);
        });
        if (isNotEmpty(addPhoneProcessor.getItems())) {
            addPhoneProcessor.setValue(addPhoneProcessor.getItems().get(0));
        }
        if (isNotEmpty(updateChoiceProcessor.getItems())) {
            updateChoiceProcessor.setValue(updateChoiceProcessor.getItems().get(0));
        }
        if (isNotEmpty(deleteChoiceProcessor.getItems())) {
            deleteChoiceProcessor.setValue(deleteChoiceProcessor.getItems().get(0));
        }
    }

    void updateChoiceBoxTransactionPhone() {
        addTransactionPhone.getItems().clear();
        updateChoicePhone.getItems().clear();
        updateTransactionPhone.getItems().clear();
        deleteChoicePhone.getItems().clear();
        phones.listPhoneNames().forEach(item -> {
            addTransactionPhone.getItems().add(item);
            if(Objects.isNull(addTransactionPhone.getValue())) {
                addTransactionPhone.setValue(item);
            }
            updateChoicePhone.getItems().add(item);
            if (Objects.isNull(updateChoicePhone.getValue())) {
                updateChoicePhone.setValue(item);
            }
            updateTransactionPhone.getItems().add(item);
            deleteChoicePhone.getItems().add(item);
            if (Objects.isNull(deleteChoicePhone.getValue())) {
                deleteChoicePhone.setValue(item);
            }
        });
    }

    void updateChoiceTransaction() {
        updateChoiceTransaction.getItems().clear();
        deleteChoiceTransaction.getItems().clear();
        transactions.getListTransactionNames().forEach(item -> {
            updateChoiceTransaction.getItems().add(item);
            deleteChoiceTransaction.getItems().add(item);
        });
        if (isNotEmpty(updateChoiceTransaction.getItems())) {
            updateChoiceTransaction.setValue(updateChoiceTransaction.getItems().get(0));
        }
        if (isNotEmpty(deleteChoiceTransaction.getItems())) {
            deleteChoiceTransaction.setValue(deleteChoiceTransaction.getItems().get(0));
        }
    }

    void setChoiceBoxStatus() {
        addTransactionStatus.getItems().addAll("ARRIVED", "SOLD", "OTHER");
        addTransactionStatus.setValue(addTransactionStatus.getItems().get(0));
        updateTransactionStatus.getItems().addAll("ARRIVED", "SOLD", "OTHER");
    }
}