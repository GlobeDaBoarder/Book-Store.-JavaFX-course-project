package book_store;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
}
