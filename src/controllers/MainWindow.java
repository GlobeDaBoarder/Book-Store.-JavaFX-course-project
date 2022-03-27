package controllers;

import book_store.*;
import hibernateControllers.BookHibController;
import hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.DBoperations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    public CheckBox check;
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
    public Tab OrdersTab;
    public Tab ManageBooksTab;
    public ComboBox bookLang;

    private int userId;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    private void modifyAccess() {
        User user = userHibController.getUserById(userId);
        if (user.getClass() != Employee.class){
            ManageBooksTab.setDisable(true);
        }else{
            ManageBooksTab.setDisable(false);
        }
    }

    public void addBook() {
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
        books.forEach(b -> bookList.getItems().add(b));
        books = bookHibController.getAllBooks(false);
        books.forEach(b -> bookListMngr.getItems().add(b));
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
}
