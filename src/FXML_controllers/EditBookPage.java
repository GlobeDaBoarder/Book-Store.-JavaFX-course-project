package FXML_controllers;

import book_store.AlertMessage;
import book_store.Book;
import book_store.eBookGenre;
import book_store.eBookLang;
import hibernateControllers.BookHibController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookPage implements Initializable {
    public TextField bookTitle;
    public TextArea bookDescription;
    public ComboBox bookGenre;
    public DatePicker publDate;
    public TextField pgNum;
    public TextField bookAuthor;
    public TextField bookPrice;
    public TextField bookQuantity;
    public ComboBox bookLang;

    private int bookId;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);


    public void setBookData(int id) {
        bookId = id;

        Book book = bookHibController.getBookById(id);
        bookTitle.setText(book.getName());
        bookDescription.setText(book.getDescription());
        bookGenre.setValue(book.getGenre());
        publDate.setValue(book.getReleaseDate());
        pgNum.setText(String.valueOf(book.getPageCount()));
        bookAuthor.setText(book.getAuthor());
        bookPrice.setText(String.valueOf(book.getPrice()));
        bookQuantity.setText(String.valueOf(book.getQuantityAvalible()));
        bookLang.setValue(book.getLang());
    }

    public void saveEdit(ActionEvent actionEvent) {

        if(bookPrice.getText().isBlank() || bookTitle.getText().isBlank() || bookAuthor.getText().isBlank()
                || bookDescription.getText().isBlank() || publDate.getValue() == null || pgNum.getText().isBlank()
                || bookLang.getValue() == null || bookQuantity.getText().isBlank() || bookGenre.getValue() == null){
            AlertMessage.generateMessage("input error", "All fields should be non-empty");
            return;
        }

        Book old_book = bookHibController.getBookById(bookId);
        Book new_book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), bookDescription.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()),
                eBookLang.valueOf(bookLang.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(bookQuantity.getText()),
                eBookGenre.valueOf(bookGenre.getSelectionModel().getSelectedItem().toString()));


        bookHibController.editBook(old_book, new_book);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookGenre.getItems().addAll(eBookGenre.values());
        bookLang.getItems().addAll(eBookLang.values());
    }
}
