package org.example.domain.history.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.domain.history.dto.After;

public class AfterJsonConverter implements AttributeConverter<After, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(After after) {
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
    public After convertToEntityAttribute(String json) {
        // This conversion is not required in your case as you are only storing the JSON representation
        // and not converting it back to the entity.
        return null;
    }
}
