package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import models.Transaction;
import models.UserSession;
import services.TransactionService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private TransactionService transactionService = new TransactionService();

    ObservableList<Transaction> transactions;

    @FXML
    private TableView<Transaction> tableView;

    @FXML
    private ComboBox<Transaction.Type> typeOptions;

    @FXML
    private Label lblUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            transactions = FXCollections.observableArrayList(transactionService.getByUserId(UserSession.getInstance().getUser().getId()));
            tableView.getItems().setAll(transactions);
            typeOptions.getItems().setAll(Transaction.Type.values());
            lblUser.setText(String.format("Logged in as: %s", UserSession.getInstance().getUser().getUsername()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
