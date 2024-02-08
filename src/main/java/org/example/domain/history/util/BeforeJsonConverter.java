package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.domain.history.dto.Beforejson;

@Converter
public class BeforeJsonConverter implements AttributeConverter<Beforejson, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Beforejson before) {
        if (before == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(before);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error converting After object to JSON", e);
        }
    }
    @Override
    public Beforejson convertToEntityAttribute(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Beforejson.class);
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null; // or throw an exception depending on your error handling strategy
        }
    }

}
