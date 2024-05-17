package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.lang.reflect.Field;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Service {
    public static <T> Optional<T> getValueFromResponse(HttpResponse<String> response,TypeReference<T> typeReference)  {
        if(response.statusCode()==204) return Optional.empty();
        String entitiesJson = response.body();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return Optional.of(objectMapper.readValue(entitiesJson, typeReference));

        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public static Map<String, String> toMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value != null) {
                map.put(field.getName(), value.toString());
            }
        }
        return map;
    }
}
