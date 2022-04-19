package WebSerializers;

import book_store.Employee;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EmployeeSerializer implements JsonSerializer<Employee> {

    @Override
    public JsonElement serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", employee.getId());
        jsonObject.addProperty("login", employee.getLogin());
        jsonObject.addProperty("password", employee.getPassword());
        jsonObject.addProperty("pos", String.valueOf(employee.getPos()));
        return  jsonObject;
    }
}
