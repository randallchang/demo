package com.jkos.appsvc.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkos.appsvc.api.constants.BoxedMessageBehaviorType;
import com.jkos.appsvc.api.model.jkos_message.response.BehaviorParameter;
import com.jkos.appsvc.api.model.msg_hub.response.BoxedMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@NoArgsConstructor
public class JkosMessageDto {

    /**
     * 序號, 現無用, 帶 0
     */
    @JsonProperty("Seq")
    private Integer seq;
    /**
     * 傳入的 authenticate 解出的 JkosId
     */
    @JsonProperty("MemberID")
    private String memberId;
    /**
     * 街口消息標題
     */
    @JsonProperty("Title")
    private String title;
    /**
     * 街口消息
     */
    @JsonProperty("Content")
    private String content;
    /**
     * 推送目標類別, 即 Target
     *
     * @see com.jkos.appsvc.api.constants.BoxedMessageTargetType
     */
    @JsonProperty("Type")
    private Integer targetType = 1;
    /**
     * 訊息類型
     */
    @JsonProperty("TagType")
    private Integer tagType;
    /**
     * 街口消息日期 yyyy/MM/dd HH:mm
     *
     * @mock 2022/09/30 00:00
     */
    @JsonProperty("Date")
    private String date;
    /**
     * 行為參數
     */
    @JsonProperty("BehaviorParameters")
    private BehaviorParameter behaviorParameters;

    public JkosMessageDto(String memberId, BoxedMessage boxedMessage) {
        this.seq = 0;
        this.memberId = memberId;
        this.title = boxedMessage.getTitle();
        this.content = boxedMessage.getContent();
        ZonedDateTime createdTime = ZonedDateTime.parse(boxedMessage.getCreatedTime(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.date = createdTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.tagType = boxedMessage.getTagType();
        if (Objects.nonNull(boxedMessage.getBehaviorType())) {
            this.behaviorParameters = new BehaviorParameter(
                    BoxedMessageBehaviorType.forValue(boxedMessage.getBehaviorType()),
                    boxedMessage.getRedirectInfo());
        }
    }

    // public JkosMessageDto(JkosMessageResponse.JkosMessage jkosMessage) {
    //     this.seq = jkosMessage.getSeq();
    //     this.title = jkosMessage.getTitle();
    //     this.content = jkosMessage.getContent();
    //     this.memberId = jkosMessage.getMemberId();
    //     this.date = jkosMessage.getDate();
    //     this.tagType = jkosMessage.getTagType();
    //     if (Objects.nonNull(jkosMessage.getBehaviorParameters())) {
    //         this.behaviorParameters = new BehaviorParametersDto(jkosMessage.getBehaviorParameters());
    //     }
    // }
}
