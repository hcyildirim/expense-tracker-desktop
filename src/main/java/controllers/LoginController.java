package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import services.AuthenticationService;
import utilities.AlertHelper;

import java.io.IOException;

public class LoginController {

    private AuthenticationService authenticationService = new AuthenticationService();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button goToRegisterButton;

    @FXML
    public void login(ActionEvent event) throws IOException {
        Window owner = submitButton.getScene().getWindow();

        if (usernameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your username");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your password");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = authenticationService.logIn(username, password);

        if (!isAuthenticated) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Warning", "Username or email is wrong!");
        } else {
            gotoDashboard((Stage) owner);
        }
    }

    public void gotoDashboard(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.setMaximized(true);
        stage.show();

        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
        Scene scene = new Scene(loader.load(), 800, 500);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }
}
