package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.example.domain.history.dto.Afterjsonsw;

public class AfterJsonConverterSw implements AttributeConverter<Afterjsonsw, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Afterjsonsw after) {
        if (after == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(after);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error converting After object to JSON", e);
        }
    }
    @Override
    public Afterjsonsw convertToEntityAttribute(String json) {
        if (json == null) {

            return null; // or throw new IllegalArgumentException("JSON string is null");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Afterjsonsw.class);
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            throw new IllegalStateException("Error converting JSON to After object", e);
        }
    }


}
