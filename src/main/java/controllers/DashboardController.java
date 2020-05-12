package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import models.Transaction;
import models.UserSession;
import services.TransactionService;
import utilities.AlertHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private TransactionService transactionService = new TransactionService();

    ObservableList<Transaction> transactions;

    @FXML
    private TableView<Transaction> tableView;

    @FXML
    private Label lblUser;

    @FXML
    private ComboBox<Transaction.Type> typeOptions;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField amountField;

    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateTableItems();
            populateTransactionTypeOptions();
            setLoggedInLabelText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addTransaction(ActionEvent event) throws IOException {
        Window owner = addButton.getScene().getWindow();

        if (descriptionField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a description");
            return;
        }

        if (amountField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter an amount");
            return;
        }

        if (typeOptions.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Select your transaction type");
            return;
        }

        String description = descriptionField.getText();
        BigDecimal amount = new BigDecimal(amountField.getText());
        Transaction.Type type = typeOptions.getValue();

        transactionService.create(description, amount, type, UserSession.getInstance().getUser().getId());

        populateTableItems();
        clearInputs();
    }

    private void populateTableItems() throws IOException {
        transactions = FXCollections.observableArrayList(transactionService.getByUserId(UserSession.getInstance().getUser().getId()));
        tableView.getItems().setAll(transactions);
    }

    private void populateTransactionTypeOptions() {
        typeOptions.getItems().setAll(Transaction.Type.values());
    }

    private void setLoggedInLabelText() {
        lblUser.setText(String.format("Logged in as: %s", UserSession.getInstance().getUser().getUsername()));
    }

    private void clearInputs() {
        descriptionField.clear();
        amountField.clear();
        typeOptions.getSelectionModel().clearSelection();
    }
}
