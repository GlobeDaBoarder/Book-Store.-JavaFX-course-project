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
    private LocalDate orderDate;
    @ManyToOne
    private User buyer;
    @ManyToMany(mappedBy = "inOrders", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("productID ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;
}

