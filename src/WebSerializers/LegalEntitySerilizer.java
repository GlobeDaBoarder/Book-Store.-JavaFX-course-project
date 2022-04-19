package WebSerializers;

import book_store.LegalEntity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class LegalEntitySerilizer implements JsonSerializer<LegalEntity> {
    @Override
    public JsonElement serialize(LegalEntity legalEntity, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", legalEntity.getId());
        jsonObject.addProperty("login", legalEntity.getLogin());
        jsonObject.addProperty("password", legalEntity.getPassword());
        jsonObject.addProperty("email", legalEntity.getEmail());
        jsonObject.addProperty("phone", legalEntity.getPhone());
        jsonObject.addProperty("companyName", legalEntity.getCompanyName());
        return null;
    }
}
