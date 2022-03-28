package book_store;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentText;
    @ManyToOne
    private Book bookComment;

    public Comment(String commentText, Book bookComment) {
        this.commentText = commentText;
        this.bookComment = bookComment;
    }

    public Comment() {

    }

    @Override
    public String toString() {
        return this.commentText;
    }
}
