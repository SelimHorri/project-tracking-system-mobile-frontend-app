package com.selimhorri.pack.pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GsonPattern {

    private static GsonPattern instance;
    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    public static synchronized GsonPattern getInstance() {
        if (instance == null)
            instance = new GsonPattern();

        return instance;
    }

    public Gson configDeserialization() {
        return gsonBuilder.setPrettyPrinting()
                 .serializeNulls()
                 .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                     @Override
                     public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                         return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
                     }
                 })
                 .create();
    }

    public Gson configDeserialization(final String datePattern) {
        return gsonBuilder.setPrettyPrinting()
                .serializeNulls()
                .setDateFormat(datePattern)
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(datePattern));
                    }
                })
                .create();
    }

}
