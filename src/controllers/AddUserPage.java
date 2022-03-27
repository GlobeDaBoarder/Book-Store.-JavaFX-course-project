package controllers;

import book_store.*;
import hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserPage implements Initializable {
    public TextField phoneNumberF;
    public TextField emailF;
    public RadioButton personRad;
    public ToggleGroup customerType;
    public RadioButton LegalEntityRad;
    public TextField nameF;
    public TextField companyNameF;
    public TextField surnameF;
    public TextField loginF;
    public TextField passwordF;
    public RadioButton EmployeeRad;
    public ComboBox employeePositionCB;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void saveAcc() {
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
        } else if (LegalEntityRad.isSelected()){
            if (companyNameF.getText().isBlank()){
                AlertMessage.generateMessage("input error", "company name should be provided!");
                return;
            }
            userHibController.createUser(new LegalEntity(loginF.getText(), passwordF.getText(), emailF.getText(),
                    phoneNumberF.getText(), companyNameF.getText()));
        }else{
            if(employeePositionCB.getValue() == null)
            {
                AlertMessage.generateMessage("input error", "company name should be provided!");
                return;
            }
            userHibController.createUser(new Employee(loginF.getText(), passwordF.getText(),
                    eEmpPositions.valueOf(employeePositionCB.getSelectionModel().getSelectedItem().toString())));
        }

        AlertMessage.generateMessage("User added!", "Press ok to continue");

    }

    public void changeVisibility() {
        if (personRad.isSelected()) {
            nameF.setDisable(false);
            surnameF.setDisable(false);

            companyNameF.setDisable(true);

            employeePositionCB.setDisable(true);
        } else if(LegalEntityRad.isSelected()){
            nameF.setDisable(true);
            surnameF.setDisable(true);

            companyNameF.setDisable(false);

            employeePositionCB.setDisable(true);
        }else{
            nameF.setDisable(true);
            surnameF.setDisable(true);

            companyNameF.setDisable(true);

            employeePositionCB.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeVisibility();
        employeePositionCB.getItems().addAll(eEmpPositions.values());
    }
}
