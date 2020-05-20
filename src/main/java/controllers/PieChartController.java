package controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import models.UserSession;
import services.TransactionService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {

    private TransactionService transactionService = new TransactionService();

    @FXML
    private PieChart piechart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Data> pieChartData = FXCollections.observableArrayList(transactionService.getPieChartData(UserSession.getInstance().getUser().getId()));
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " ", data.pieValueProperty()
                            )
                    )
            );


            piechart.setTitle("Your current look");
            piechart.setData(pieChartData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
