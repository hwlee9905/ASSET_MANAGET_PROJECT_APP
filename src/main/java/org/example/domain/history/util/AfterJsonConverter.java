package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.example.domain.history.dto.Afterjsonhw;

public class AfterJsonConverter implements AttributeConverter<Afterjsonhw, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Afterjsonhw after) {
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
    public Afterjsonhw convertToEntityAttribute(String json) {
        if (json == null) {

            return null; // or throw new IllegalArgumentException("JSON string is null");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Afterjsonhw.class);
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            throw new IllegalStateException("Error converting JSON to After object", e);
        }
    }


}
