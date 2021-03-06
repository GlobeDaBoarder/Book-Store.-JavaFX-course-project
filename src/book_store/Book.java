package book_store;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    private double price;
    private String name;
    private String author;
    private String description;
    private LocalDate releaseDate;
    private int pageCount;
    @Enumerated
    private eBookLang lang;
    private int quantityAvalible;
    @Enumerated
    private eBookGenre genre;
    private boolean isAvailable = false;

    @OneToMany(mappedBy = "bookComment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;
    @ManyToMany
    private List<ShopingCart> inCarts;

    public Book(double price, String name, String author, String description, LocalDate releaseDate, int pageCount,
                eBookLang lang, int quantityAvalible, eBookGenre genre) {
        this.price = price;
        this.name = name;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
        this.pageCount = pageCount;
        this.lang = lang;
        this.quantityAvalible = quantityAvalible;
        this.genre = genre;

        if (this.quantityAvalible > 0) { this.isAvailable = true;}
    }

    public Book(String name, String author) {
        this.price = 0;
        this.name = name;
        this.author = author;
        this.description = description;
        this.releaseDate = LocalDate.now();
        this.pageCount = 0;
        this.lang = eBookLang.English;
        this.quantityAvalible = 0;
        this.genre = eBookGenre.SCI_FI;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "productID=" + productID +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", pageCount=" + pageCount +
                ", lang=" + lang +
                ", quantityAvalible=" + quantityAvalible +
                ", genre=" + genre +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public void setQuantityAvalible(int quantityAvalible) {
        this.quantityAvalible = quantityAvalible;
        if (this.quantityAvalible < 1)
            this.isAvailable = false;
        else
            this.isAvailable = true;
    }
}
