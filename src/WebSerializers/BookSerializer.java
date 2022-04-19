package WebSerializers;

import book_store.Book;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class BookSerializer implements JsonSerializer<Book> {
    @Override
    public JsonElement serialize(Book book, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("productID", book.getProductID());
        jsonObject.addProperty("price", book.getPrice());
        jsonObject.addProperty("name", book.getName());
        jsonObject.addProperty("author", book.getAuthor());
        jsonObject.addProperty("description", book.getDescription());
        jsonObject.addProperty("releaseDate", String.valueOf(book.getReleaseDate()));
        jsonObject.addProperty("pageCount", book.getPageCount());
        jsonObject.addProperty("lang", String.valueOf(book.getLang()));
        jsonObject.addProperty("quantityAvalible", book.getQuantityAvalible());
        jsonObject.addProperty("genre", book.getName());
        jsonObject.addProperty("isAvailable", book.isAvailable());
        return jsonObject;
    }
}
