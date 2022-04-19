package WebSerializers;

import book_store.Book;
import book_store.User;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class BookListSerializer implements JsonSerializer<List<Book>> {
    @Override
    public JsonElement serialize(List<Book> books, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        Gson parser = new GsonBuilder().create();

        for (Book b : books) {
            jsonArray.add(parser.toJson(b));
        }
        return jsonArray;
    }
}
