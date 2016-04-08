package com.allenway.commons.timeconverter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            return ISODateTimeFormat.dateTimeParser().parseLocalDateTime(str);
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new LocalDateTime(jp.getLongValue());
        }
        throw ctxt.mappingException(handledType());
    }
}
