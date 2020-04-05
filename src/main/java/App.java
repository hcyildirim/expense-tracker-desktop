import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        String[] files = {"users.txt", "transactions.txt"};

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println(fileName + " file created successfully.");
            } else {
                System.out.println(fileName + " already exists in the project root directory.");
            }
        }

        launch(args);
    }

}