package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Window;
import javafx.util.Callback;
import models.Transaction;
import models.UserSession;
import services.TransactionService;
import utilities.AlertHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    private TransactionService transactionService = new TransactionService();

    private ObservableList<Transaction> transactions;

    @FXML
    private TableView<Transaction> tableView;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblTotal;

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
            setTotalLabelText();
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
        Float amount = Float.valueOf(amountField.getText());
        Transaction.Type type = typeOptions.getValue();

        transactionService.create(description, amount, type, UserSession.getInstance().getUser().getId());

        populateTableItems();
        setTotalLabelText();
        clearInputs();
    }

    private void populateTableItems() throws IOException {
        transactions = FXCollections.observableArrayList(transactionService.getByUserId(UserSession.getInstance().getUser().getId()));

        tableView.setRowFactory(new Callback<TableView<Transaction>, TableRow<Transaction>>() {
            @Override
            public TableRow<Transaction> call(TableView<Transaction> tableView) {
                final TableRow<Transaction> row = new TableRow<Transaction>() {
                    @Override
                    protected void updateItem(Transaction data, boolean empty) {
                        super.updateItem(data, empty);
                        if (data != null && data.getType().equals(Transaction.Type.INCOME)) {
                            setStyle("-fx-text-background-color: green;");
                        } else {
                            setStyle("-fx-text-background-color: red;");
                        }
                    }
                };

                return row;
            }
        });
        tableView.getItems().setAll(transactions);
    }

    private void populateTransactionTypeOptions() {
        typeOptions.getItems().setAll(Transaction.Type.values());
    }

    private void setLoggedInLabelText() {
        lblUser.setText(String.format("Logged in as: %s", UserSession.getInstance().getUser().getUsername()));
    }

    private void setTotalLabelText() {
        float sum = transactions.stream().collect(Collectors.summingDouble(o -> o.getAmount())).floatValue();
        lblTotal.setText(String.format("Total: %.2f", sum));

        if (sum == 0) {
            lblTotal.setTextFill(Paint.valueOf("black"));
        }  else if (sum > 0) {
            lblTotal.setTextFill(Paint.valueOf("green"));
        } else {
            lblTotal.setTextFill(Paint.valueOf("red"));
        }
    }

    private void clearInputs() {
        descriptionField.clear();
        amountField.clear();
        typeOptions.getSelectionModel().clearSelection();
    }
}
