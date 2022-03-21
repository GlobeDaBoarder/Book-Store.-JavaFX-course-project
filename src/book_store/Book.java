package book_store;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Book {
    private int productID;
    private double price;
    private String name;
    private String author;
    private String description;
    private LocalDate releaseDate;
    private int pageCount;
    private eBookLang lang;
    private int quantityAvalible;
    private eBookGenre genre;

    public Book(int productID, double price, String name, String author, String description, LocalDate releaseDate,
                int pageCount, eBookLang lang, int quantityAvalible, eBookGenre genre) {
        this.productID = productID;
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
                '}';
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public eBookLang getLang() {
        return lang;
    }

    public void setLang(eBookLang lang) {
        this.lang = lang;
    }

    public int getQuantityAvalible() {
        return quantityAvalible;
    }

    public void setQuantityAvalible(int quantityAvalible) {
        this.quantityAvalible = quantityAvalible;
    }

    public eBookGenre getGenre() {
        return genre;
    }

    public void setGenre(eBookGenre genre) {
        this.genre = genre;
    }
}
