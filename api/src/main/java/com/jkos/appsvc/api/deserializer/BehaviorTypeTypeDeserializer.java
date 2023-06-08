package com.jkos.appsvc.api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.jkos.appsvc.api.constants.BoxedMessageBehaviorType;

import java.io.IOException;

public class BehaviorTypeTypeDeserializer extends StdDeserializer<Integer> {

    public BehaviorTypeTypeDeserializer() {
        this(null);
    }

    public BehaviorTypeTypeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Integer deserialize(
            JsonParser jsonparser, DeserializationContext context)
                throws IOException {

        return BoxedMessageBehaviorType
                .forValue(Integer.parseInt(jsonparser.getText()))
                .getBoxedMessagePushType()
                .getValue();
    }
}
