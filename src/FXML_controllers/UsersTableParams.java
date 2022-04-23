package FXML_controllers;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;


public class UsersTableParams {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty userType = new SimpleStringProperty();
    private SimpleStringProperty login = new SimpleStringProperty();
    private SimpleStringProperty createDate = new SimpleStringProperty();
    private SimpleStringProperty pos = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty companyName = new SimpleStringProperty();

    public UsersTableParams(SimpleStringProperty id, SimpleStringProperty userType, SimpleStringProperty login,
                            SimpleStringProperty createDate, SimpleStringProperty pos, SimpleStringProperty email,
                            SimpleStringProperty phone, SimpleStringProperty name, SimpleStringProperty surname,
                            SimpleStringProperty companyName) {
        this.id = id;
        this.userType = userType;
        this.login = login;
        this.createDate = createDate;
        this.pos = pos;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
    }

    public UsersTableParams(String id, String userType, String login, String createDate, String pos, String email,
                            String phone, String name, String surname, String companyName) {
        this.id = new SimpleStringProperty(id);
        this.userType = new SimpleStringProperty(userType);
        this.login = new SimpleStringProperty(login);
        this.createDate = new SimpleStringProperty(createDate);
        this.pos =  new SimpleStringProperty(pos);
        this.email =  new SimpleStringProperty(email);
        this.phone =  new SimpleStringProperty(phone);
        this.name =  new SimpleStringProperty(name);
        this.surname =  new SimpleStringProperty(surname);
        this.companyName =  new SimpleStringProperty(companyName);
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

    public String getPos() {
        return pos.get();
    }

    public SimpleStringProperty posProperty() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos.set(pos);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}
