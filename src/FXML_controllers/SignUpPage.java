package FXML_controllers;

import book_store.AlertMessage;
import book_store.LegalEntity;
import book_store.Person;
import hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPage implements Initializable {
    public TextField phoneNumberF;
    public TextField emailF;
    public RadioButton personRad;
    public RadioButton LegalEntityRad;
    public TextField nameF;
    public TextField companyNameF;
    public TextField surnameF;
    public TextField loginF;
    public TextField passwordF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void saveAcc(ActionEvent actionEvent) throws IOException {
        if (phoneNumberF.getText().isBlank() || emailF.getText().isBlank() || loginF.getText().isBlank()
                || passwordF.getText().isBlank()){
            AlertMessage.generateMessage("input error", "All fields should be non-empty!");

            return;
        }

        if (personRad.isSelected()) {
            if (nameF.getText().isBlank() || surnameF.getText().isBlank()){
                AlertMessage.generateMessage("input error", "name and surname should be provided!");
                return;
            }
            userHibController.createUser(new Person(loginF.getText(), passwordF.getText(), emailF.getText(),
                    phoneNumberF.getText(), nameF.getText(), surnameF.getText()));
        } else {
            if (companyNameF.getText().isBlank()){
                AlertMessage.generateMessage("input error", "company name should be provided!");
                return;
            }
            userHibController.createUser(new LegalEntity(loginF.getText(), passwordF.getText(), emailF.getText(),
                    phoneNumberF.getText(), companyNameF.getText()));
        }
        returnToLogInPage();
    }

    public void returnToLogInPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/LoginPage.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setTitle("Log In Page");
        stage.setScene(scene);
        stage.show();
    }

    public void changeVisibility() {
        if (personRad.isSelected()) {
            nameF.setDisable(false);
            surnameF.setDisable(false);
            companyNameF.setDisable(true);
        } else {
            nameF.setDisable(true);
            surnameF.setDisable(true);
            companyNameF.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeVisibility();
    }
}
