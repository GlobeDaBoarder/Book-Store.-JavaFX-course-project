package FXML_controllers;

import book_store.Book;
import book_store.Comment;
import hibernateControllers.BookHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Optional;

public class ViewBookPage {
    public Label bookTitle;
    public Label bookDescription;
    public Label bookGenre;
    public Label publDate;
    public Label pgNum;
    public Label bookAuthor;
    public Label bookPrice;
    public Label bookQuantity;
    public Label bookLang;

    private int bookId;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);

    public void setBookData(int id) {
        bookId = id;

        Book book = bookHibController.getBookById(id);
        bookTitle.setText(book.getName());
        bookDescription.setText(book.getDescription());
        bookGenre.setText(book.getGenre().toString());
        publDate.setText(book.getReleaseDate().toString());
        pgNum.setText(String.valueOf(book.getPageCount()));
        bookAuthor.setText(book.getAuthor());
        bookPrice.setText(String.valueOf(book.getPrice()));
        bookQuantity.setText(String.valueOf(book.getQuantityAvalible()));
        bookLang.setText(book.getLang().toString());
    }

    public void viewComment() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/CommentsPage.fxml"));
        Parent parent = fxmlLoader.load();
        CommentsPage commentsPage = fxmlLoader.getController();
        commentsPage.setBookId(bookId);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void leaveComment() {
        Book book = bookHibController.getBookById(bookId);

        TextInputDialog dialog = new TextInputDialog("enter comment text");
        dialog.setTitle(book.getName());
        dialog.setHeaderText("Say something good about this book:");
        dialog.setContentText("");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
            Comment bookComment = new Comment(result.get(), book);
            book.getComments().add(bookComment);
            bookHibController.editBook(book);
        }
    }
}
