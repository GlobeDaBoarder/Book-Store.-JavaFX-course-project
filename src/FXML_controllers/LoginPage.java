package FXML_controllers;

import book_store.AlertMessage;
import book_store.User;
import hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginPage {
    public TextField loginF;
    public TextField passwordF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void LogIn(ActionEvent actionEvent) throws IOException {
        User user = userHibController.getUserByLoginData(loginF.getText(), passwordF.getText());
        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/mainWindow.fxml"));
            Parent parent = fxmlLoader.load();

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setUserId(user.getId());

            Scene scene = new Scene(parent);
            Stage stage = (Stage) loginF.getScene().getWindow();
            stage.setTitle("Globe Book Store");
            stage.setScene(scene);
            stage.show();
        } else {
            AlertMessage.generateMessage("Incorrect Login or Password!", "Try again.");
        }
    }

    public void SignUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/SignUpPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setTitle("Sign Up Page");
        stage.setScene(scene);
        stage.show();
    }
}
