package com.jkos.appsvc.api.model.jkos_message.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JkosMessageResponse {

    @JsonProperty("Seq")
    private Integer seq;

    @JsonProperty("MemberID")
    private String memberId;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("TagType")
    private Integer tagType;

    @JsonProperty("Type")
    private Integer targetType = 1;

    @JsonProperty("BehaviorParameters")
    private BehaviorParameter behaviorParameter;
}
