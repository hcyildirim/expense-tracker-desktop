package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.UserService;
import utilities.AlertHelper;

import java.io.IOException;

public class LoginController {

    private UserService userService = new UserService();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button goToRegisterButton;

    @FXML
    public void login(ActionEvent event) {
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

        boolean isAuthenticated = userService.authenticate(username, password);

        if (!isAuthenticated) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Warning", "User not found");
        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Login successful");
        }
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/register.fxml"));

        Scene scene = new Scene(loader.load(), 800, 500);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
//        Parent root;
//
//        try {
//            root = loader.load();
//        } catch (IOException ioe) {
//            return;
//        }
//
//        goToRegisterButton.getScene().setRoot(root);
    }
}
