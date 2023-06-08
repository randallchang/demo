package com.jkos.appsvc.api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeIsoToSimpleDeserializer extends StdDeserializer<String> {

    private static final DateTimeFormatter SIMPLE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public DateTimeIsoToSimpleDeserializer() {
        this(null);
    }

    public DateTimeIsoToSimpleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        return ZonedDateTime.parse(
                jsonparser.getText(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .format(SIMPLE_FORMATTER);
    }
}
