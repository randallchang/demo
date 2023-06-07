package com.jkos.appsvc.api.model.jkos_message.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkos.appsvc.api.constants.BoxedMessageBehaviorType;
import com.jkos.appsvc.api.model.msg_hub.response.RedirectInfo;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BehaviorParameter {

    /**
     * 推送類型, for app 用
     *
     * @see com.jkos.appsvc.api.constants.BoxedMessagePushType
     */
    @JsonProperty("PushType")
    private Integer pushType;

    @JsonProperty("WebUrl")
    private String webUrl;

    @JsonProperty("OrderID")
    private String orderId;

    @JsonProperty("ReservationID")
    private Long reservationId;

    @JsonProperty("ConsumeID")
    private Long consumeId;

    @JsonProperty("CommentID")
    private String commentId;

    @JsonProperty("FeedbackID")
    private String feedbackId;

    @JsonProperty("WaitingID")
    private String waitingId;

    public BehaviorParameter(
            BoxedMessageBehaviorType behaviorType,
            RedirectInfo redirectInfo) {

        this.webUrl = redirectInfo.getUrl();
        this.commentId = redirectInfo.getCommentId();
        this.consumeId = redirectInfo.getConsumeId();
        this.feedbackId = redirectInfo.getFeedbackId();
        this.orderId = redirectInfo.getOrderId();
        this.reservationId = redirectInfo.getReservationId();
        this.waitingId = redirectInfo.getWaitingId();
        this.pushType = behaviorType.getBoxedMessagePushType().getValue();
    }
}
