package controllers;

import book_store.Book;
import book_store.eBookGenre;
import book_store.eBookLang;
import hibernateControllers.BookHibController;
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

    private int userId;

    private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);

    public void addBook(ActionEvent actionEvent) throws SQLException {
        Book book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), bookDescription.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()), eBookLang.English,
                Integer.parseInt(bookQuantity.getText()), eBookGenre.SCI_FI);
        bookHibController.createBook(book);
        refreshTable();
    }

    private void refreshTable() {
       /* bookList.getItems().clear();
        bookListMngr.getItems().clear();
        ArrayList<Book> books = DBoperations.getAllBooksFromDb();
        books.forEach(b -> bookList.getItems().add(b));
        books.forEach(b -> bookListMngr.getItems().add(b));*/
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }
}
