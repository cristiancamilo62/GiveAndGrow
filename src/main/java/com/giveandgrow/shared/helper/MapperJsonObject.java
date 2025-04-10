package com.giveandgrow.shared.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MapperJsonObject {

    public Optional<String> execute(Object object){

        try {
            ObjectMapper mapper = new ObjectMapper();

            return Optional.ofNullable(mapper.writeValueAsString(object));
        } catch (JsonProcessingException exception) {
            return Optional.empty();
        }
    }

    public <T> Optional<T> execute(String json, Class<T> clazz){
        try {
            ObjectMapper mapper = new ObjectMapper();

            return Optional.ofNullable(mapper.readValue(json,clazz));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<String> executeGson(Object object){
        try {
            Gson gson = new GsonBuilder().serializeNulls().
                    registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext
                                                         context) throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_DATE_TIME);
                        }
                    }).create();

            String json = gson.toJson(object);
            return  Optional.ofNullable(json);
        }catch (Exception exception){
            return Optional.empty();
        }
    }
}
