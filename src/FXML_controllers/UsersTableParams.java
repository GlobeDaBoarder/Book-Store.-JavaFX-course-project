package FXML_controllers;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;


public class UsersTableParams {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty userType = new SimpleStringProperty();
    private SimpleStringProperty login = new SimpleStringProperty();
    private SimpleStringProperty createDate = new SimpleStringProperty();

    public UsersTableParams(SimpleStringProperty id, SimpleStringProperty userType, SimpleStringProperty login, SimpleStringProperty createDate) {
        this.id = id;
        this.userType = userType;
        this.login = login;
        this.createDate = createDate;
    }

    public UsersTableParams(String id, String userType, String login, String createDate) {
        this.id = new SimpleStringProperty(id);
        this.userType = new SimpleStringProperty(userType);
        this.login = new SimpleStringProperty(login);
        this.createDate = new SimpleStringProperty(createDate);
    }

    public UsersTableParams() {
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getUserType() {
        return userType.get();
    }

    public SimpleStringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
}
