package com.jkos.appsvc.api.model.msg_hub.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jkos.appsvc.api.deserializer.BehaviorTypeTypeDeserializer;
import com.jkos.appsvc.api.deserializer.DateTimeIsoToSimpleDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxedMessage {

    @JsonAlias("createdTime")
    @JsonDeserialize(using = DateTimeIsoToSimpleDeserializer.class)
    private String date;

    private String title;

    private String content;

    private Integer tagType;

    @JsonAlias("behaviorType")
    @JsonDeserialize(using = BehaviorTypeTypeDeserializer.class)
    private Integer pushType;

    private RedirectInfo behaviorParameter;
}
