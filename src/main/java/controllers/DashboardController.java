package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            transactions = FXCollections.observableArrayList(transactionService.getByUserId(UserSession.getInstance().getUser().getId()));
            tableView.getItems().setAll(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
