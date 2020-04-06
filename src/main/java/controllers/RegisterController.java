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

public class RegisterController {

    private UserService userService = new UserService();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void register(ActionEvent event) {
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

        boolean isExists = userService.isUsernameExists(username);

        if (isExists) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Warning", "Username already taken. Please choose another one.");
        } else {
            userService.create(username, password);

            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Your registration completed successfully.");
        }
    }
}
