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
public class BehaviorParameter {

    @JsonProperty("PushType")
    private Integer pushType;

    @JsonProperty("WebUrl")
    @JsonAlias({"WebUrl", "webUrl"})
    private String webUrl;

    @JsonProperty("OrderID")
    @JsonAlias({"OrderID", "orderId"})
    private String orderId;

    @JsonProperty("ReservationID")
    @JsonAlias({"ReservationID", "reservationId"})
    private Long reservationId;

    @JsonProperty("ConsumeID")
    @JsonAlias({"ConsumeID", "consumeId"})
    private Long consumeId;

    @JsonProperty("CommentID")
    @JsonAlias({"CommentID", "commentId"})
    private String commentId;

    @JsonProperty("FeedbackID")
    @JsonAlias({"FeedbackID", "feedbackId"})
    private String feedbackId;

    @JsonProperty("WaitingID")
    @JsonAlias({"WaitingID", "waitingId"})
    private String waitingId;
}
