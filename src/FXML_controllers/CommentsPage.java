package FXML_controllers;

import book_store.Book;
import hibernateControllers.BookHibController;
import javafx.scene.control.ListView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommentsPage {

    public ListView commentList;

    private int bookId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);

    public void setBookId(int bookId) {
        this.bookId = bookId;
        fillCommentList();
    }

    private void fillCommentList() {
        Book book = bookHibController.getBookById(bookId);
        commentList.getItems().addAll(book.getComments());
    }

}
