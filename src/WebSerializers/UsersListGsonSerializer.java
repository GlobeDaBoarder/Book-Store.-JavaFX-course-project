/*
package WebSerializers;

import com.google.gson.*;
import dsbook.User;

import java.lang.reflect.Type;
import java.util.List;

public class UsersListGsonSerializer implements JsonSerializer<List<User>> {
    @Override
    public JsonElement serialize(List<User> users, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonArray jsonArray = new JsonArray();
        Gson parser = new GsonBuilder().create();

        for (User u : users) {
            jsonArray.add(parser.toJson(u));
        }
        return jsonArray;
    }
}
*/
