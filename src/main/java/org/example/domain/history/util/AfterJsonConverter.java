package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.example.domain.history.dto.Afterjson;

public class AfterJsonConverter implements AttributeConverter<Afterjson, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Afterjson after) {
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
    public Afterjson convertToEntityAttribute(String json) {
        if (json == null) {

            return null; // or throw new IllegalArgumentException("JSON string is null");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Afterjson.class);
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            throw new IllegalStateException("Error converting JSON to After object", e);
        }
    }


}
