package controllers;

import book_store.Book;
import book_store.eBookGenre;
import book_store.eBookLang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindow {
    @FXML
    public ListView bookList;
    @FXML
    public CheckBox check;
    public TextField bookTitle;
    public TextArea bookDescription;
    public ComboBox bookGenre;
    public DatePicker publDate;
    public TextField pgNum;
    public TextField bookEdition;
    public TextField bookAuthor;
    public TextField bookPrice;
    public TextField bookQuantity;

    public void addBook(ActionEvent actionEvent) {
        Book book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()), eBookLang.English,
                Integer.parseInt(bookQuantity.getText()), eBookGenre.SCI_FI);
        System.out.println(book);
    }
}
