package controllers;

import book_store.Book;
import book_store.eBookGenre;
import book_store.eBookLang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.DBoperations;

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

    public void addBook(ActionEvent actionEvent) throws SQLException {
        Book book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), bookDescription.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()), eBookLang.English,
                Integer.parseInt(bookQuantity.getText()), eBookGenre.SCI_FI);
        System.out.println(book);

        Connection connection = DBoperations.connectToDB();
        String insertQuery = "INSERT INTO books(`price`, `name`, `author`, `description`, `releaseDate`, `pageCount`, `lang`,`quantityAvalible`, `genre`) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setDouble(1, book.getPrice());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getDescription());
        preparedStatement.setDate(5, Date.valueOf(book.getReleaseDate()));
        preparedStatement.setInt(6, book.getPageCount());
        preparedStatement.setString(7, String.valueOf(book.getLang()));
        preparedStatement.setInt(8, book.getQuantityAvalible());
        preparedStatement.setString(9, String.valueOf(book.getGenre()));
        preparedStatement.execute();
        DBoperations.disconnectFromDB(connection, preparedStatement);
        //Insert Query
        //run query
        //close connection
        refreshTable();
    }

    private void refreshTable() {
        bookList.getItems().clear();
        bookListMngr.getItems().clear();
        ArrayList<Book> books = DBoperations.getAllBooksFromDb();
        books.forEach(b -> bookList.getItems().add(b));
        books.forEach(b -> bookListMngr.getItems().add(b));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }
}
