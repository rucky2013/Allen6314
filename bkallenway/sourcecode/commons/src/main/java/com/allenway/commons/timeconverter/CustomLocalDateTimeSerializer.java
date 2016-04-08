package com.allenway.commons.timeconverter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    
    private static DateTimeFormatter formatter = DateTimeFormat
            .forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator,
                          SerializerProvider serializerProvider)
            throws IOException {
        generator.writeString(formatter.print(value));
    	//generator.writeString( value.toString() );
    }

}
