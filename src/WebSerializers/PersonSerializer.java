package WebSerializers;

import book_store.Person;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PersonSerializer implements JsonSerializer<Person> {
    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", person.getId());
        jsonObject.addProperty("login", person.getLogin());
        jsonObject.addProperty("password", person.getPassword());
        jsonObject.addProperty("email", person.getEmail());
        jsonObject.addProperty("phone", person.getPhone());
        jsonObject.addProperty("name", person.getName());
        jsonObject.addProperty("surname", person.getSurname());
        return jsonObject;
    }
}
