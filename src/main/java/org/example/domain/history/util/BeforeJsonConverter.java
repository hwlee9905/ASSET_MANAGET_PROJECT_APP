package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.domain.history.dto.Before;

@Converter
public class BeforeJsonConverter implements AttributeConverter<Before, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Before before) {
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
    public Before convertToEntityAttribute(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Before.class);
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null; // or throw an exception depending on your error handling strategy
        }
    }

}
