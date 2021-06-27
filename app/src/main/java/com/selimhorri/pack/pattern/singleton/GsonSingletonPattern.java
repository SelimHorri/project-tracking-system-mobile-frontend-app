package com.selimhorri.pack.pattern.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GsonSingletonPattern {

    private static GsonSingletonPattern instance;
    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    public static synchronized GsonSingletonPattern getInstance() {
        if (instance == null)
            instance = new GsonSingletonPattern();

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

    public Gson configDeserialization(final LocalDate now, final String datePattern) {
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

    public Gson configDeserialization(final LocalDateTime now, final String datePattern) {
        return gsonBuilder.setPrettyPrinting()
                .serializeNulls()
                .setDateFormat(datePattern)
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(datePattern));
                    }
                })
                .create();
    }

    public Gson configDeserialization(final ZonedDateTime now, final String datePattern) {
        return gsonBuilder.setPrettyPrinting()
                .serializeNulls()
                .setDateFormat(datePattern)
                .registerTypeAdapter(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
                    @Override
                    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(datePattern));
                    }
                })
                .create();
    }



}
