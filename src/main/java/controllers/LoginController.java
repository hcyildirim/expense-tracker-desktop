package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import services.UserService;
import utilities.AlertHelper;

public class LoginController {

    private UserService userService = new UserService();

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event) {

        Window owner = submitButton.getScene().getWindow();

        if (emailIdField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your username");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = authenticate(emailId, password);

        if (!isAuthenticated) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Warning", "User not found");
        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Login successful");
        }
    }

    private boolean authenticate(String username, String password) {
        return userService.authenticate(username, password);
    }
}
