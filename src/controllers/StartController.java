package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends Application {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField pswF;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/Start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Book Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void logIn(ActionEvent actionEvent) {
        System.out.println(loginF.getText() + ' ' + pswF.getText());
    }

    public void SignUp(ActionEvent actionEvent) {
    }
}
