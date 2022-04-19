package webControllers;

import WebSerializers.*;
import book_store.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hibernateControllers.BookHibController;
import hibernateControllers.UserHibController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class BookWeb {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);

    @RequestMapping(value = "/book/getBook", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getWebBook(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        Book book = bookHibController.getBookById(Integer.parseInt(data.getProperty("id")));

        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer())
                .registerTypeAdapter(Book.class, new BookSerializer());

        return  (builder.create().toJson(book, Book.class));

    }

    @RequestMapping(value = "/book/addBook", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addWebBook(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        bookHibController.createBook(new Book(Double.parseDouble(data.getProperty("price")), data.getProperty("name"),
                data.getProperty("author"), data.getProperty("description"), LocalDate.parse(data.getProperty("releaseDate")),
                Integer.parseInt(data.getProperty("pageCount")), eBookLang.valueOf(data.getProperty("lang")), Integer.parseInt(data.getProperty("quantityAvalible")),
                eBookGenre.valueOf(data.getProperty("genre"))));
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer());
        return "Sucess!";
    }


    @RequestMapping(value = "/book/deleteBook/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserWeb(@PathVariable(name = "id") int id) {
        bookHibController.removeBook(id);

        return "Success!";
    }


    @RequestMapping(value = "/book/getAllBooks", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllWebBooks() {
        List<Book> books = bookHibController.getAllBooks(false);

        GsonBuilder gson = new GsonBuilder();
        Type bookList = new TypeToken<List<Book>>() {
        }.getType();
        gson.registerTypeAdapter(bookList, new BookListSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer())
                .registerTypeAdapter(Book.class, new BookSerializer());
        Gson parser = gson.create();
        return parser.toJson(books);
    }


    @RequestMapping(value = "/book/updateBook", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateWebBook(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        Book book =  bookHibController.getBookById(Integer.parseInt(data.getProperty("id")));
        book.setPrice(Integer.parseInt(data.getProperty("price")));
        book.setName(data.getProperty("name"));
        book.setAuthor(data.getProperty("author"));
        book.setDescription(data.getProperty("description"));
        book.setReleaseDate(LocalDate.parse(data.getProperty("releaseDate")));
        book.setPageCount(Integer.parseInt(data.getProperty("pageCount")));
        book.setLang(eBookLang.valueOf(data.getProperty("lang")));
        book.setQuantityAvalible(Integer.parseInt(data.getProperty("quantityAvalible")));
        book.setGenre(eBookGenre.valueOf(data.getProperty("genre")));
        bookHibController.editBook(book);
        return "Updated";

    }
}
