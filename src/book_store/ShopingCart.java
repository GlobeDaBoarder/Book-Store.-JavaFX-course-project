package book_store;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShopingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate cartCreateDate;
    @Enumerated
    private eCartStatus cartStatus;
    @ManyToOne
    private User buyer;
    @ManyToMany(mappedBy = "inCarts", cascade = {CascadeType.ALL})
    @OrderBy("productID ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;

    public ShopingCart(User buyer, Book book) {
        this.cartCreateDate = LocalDate.now();
        this.cartStatus = eCartStatus.ACTIVE;
        this.buyer = buyer;
        this.books = new ArrayList<Book>(0);
        this.books.add(book);
    }

    public ShopingCart() {
    }

    public void addBookToCart(Book book){
        this.books.add(book);
    }

}

