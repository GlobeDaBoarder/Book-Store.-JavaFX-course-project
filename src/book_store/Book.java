package book_store;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Book {
    private int productID;
    private double price;
    private String name;
    private String author;
    private LocalDate releaseDate;
    private int pageCount;
    private eBookLang lang;
    private int quantityAvalible;
    private eBookGenre genre;


    public Book(int productID, double price, String name, String author,
                LocalDate releaseDate, int pageCount, eBookLang lang,
                int quantityAvalible, eBookGenre genre) {
        this.productID = productID;
        this.price = price;
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.pageCount = pageCount;
        this.lang = lang;
        this.quantityAvalible = quantityAvalible;
        this.genre = genre;
    }

    public Book(double price, String name, String author,
                LocalDate releaseDate, int pageCount, eBookLang lang,
                int quantityAvalible, eBookGenre genre) {
        this.productID = productID;
        this.price = price;
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.pageCount = pageCount;
        this.lang = lang;
        this.quantityAvalible = quantityAvalible;
        this.genre = genre;
    }
}
