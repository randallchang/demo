package com.jkos.appsvc.api.model.jkos_message.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JkosMessageResponse {

    @JsonProperty("Seq")
    @Builder.Default
    private Integer seq = 0;

    @JsonProperty("Type")
    @Builder.Default
    private Integer targetType = 1;

    @JsonProperty("MemberID")
    private String memberId;

    @JsonProperty("Date")
    @JsonAlias({"Date", "date"})
    private String date;

    @JsonProperty("Title")
    @JsonAlias({"Title", "title"})
    private String title;

    @JsonProperty("Content")
    @JsonAlias({"Content", "content"})
    private String content;

    @JsonProperty("TagType")
    @JsonAlias({"TagType", "tagType"})
    private Integer tagType;

    @JsonProperty("BehaviorParameters")
    @JsonAlias({"BehaviorParameters", "behaviorParameter"})
    private BehaviorParameter behaviorParameter;
}
