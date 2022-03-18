package todoApp.rest.todo;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    	
    	System.out.println("getAsString : " + json.getAsString());

        LocalDate localDate = LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate;
    } // deserialize
}
