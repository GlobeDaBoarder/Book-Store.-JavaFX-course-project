package controllers;

import book_store.*;
import hibernateControllers.BookHibController;
import hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.DBoperations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    public ListView bookList;
    @FXML
    public TextField bookTitle;
    public TextArea bookDescription;
    public ComboBox bookGenre;
    public DatePicker publDate;
    public TextField pgNum;
    public TextField bookAuthor;
    public TextField bookPrice;
    public TextField bookQuantity;
    public ListView bookListMngr;
    public Tab BookShopListTab;
    public Tab ManageBooksTab;
    public ComboBox bookLang;
    public Tab ShoppingCartTab;
    public Tab ManageUsersTab;

    private int userId;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    private void modifyAccess() {
        User user = userHibController.getUserById(userId);
        if (user.getClass() != Employee.class){
            ManageBooksTab.setDisable(true);
            ManageUsersTab.setDisable(true);
        }else{
            ManageBooksTab.setDisable(false);
            ManageUsersTab.setDisable(false);
        }
    }

    public void addBook() {
        if(bookPrice.getText().isBlank() || bookTitle.getText().isBlank() || bookAuthor.getText().isBlank()
                || bookDescription.getText().isBlank() || publDate.getValue() == null || pgNum.getText().isBlank()
                || bookLang.getValue() == null || bookQuantity.getText().isBlank() || bookGenre.getValue() == null){
            AlertMessage.generateMessage("input error", "All fields should be non-empty");
            return;
        }
        Book book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), bookDescription.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()),
                eBookLang.valueOf(bookLang.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(bookQuantity.getText()),
                eBookGenre.valueOf(bookGenre.getSelectionModel().getSelectedItem().toString()));
        bookHibController.createBook(book);
        refreshTable();
    }

    private void refreshTable() {
        bookList.getItems().clear();
        bookListMngr.getItems().clear();
        List<Book> books = bookHibController.getAllBooks(true);
        books.forEach(b -> bookList.getItems().add(b.getName() + " by " + b.getAuthor()));
        books = bookHibController.getAllBooks(false);
        books.forEach(b -> bookListMngr.getItems().add(b.getProductID() + ": " + b.getName() + " by " + b.getAuthor() + "(Available: " + b.isAvailable() + ")"));
    }

    public void setUserId(int userId) {
        this.userId = userId;
        modifyAccess();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookGenre.getItems().addAll(eBookGenre.values());
        bookLang.getItems().addAll(eBookLang.values());
        refreshTable();
    }

    public void openAddUserPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/AddUserPage.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Book store");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void editSelected(ActionEvent actionEvent) {
    }

    public void deleteSelected(ActionEvent actionEvent) {
        System.out.println(bookListMngr.getSelectionModel().getSelectedItem().toString());
        bookHibController.removeBook(Integer.parseInt(bookListMngr.getSelectionModel().getSelectedItem().toString().split(":")[0]));
        refreshTable();
    }
}
