package com.jkos.appsvc.api.model.msg_hub.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedirectInfo {

    @JsonAlias("url")
    private String webUrl;

    private String orderId;

    private Long reservationId;

    private Long consumeId;

    private String commentId;

    private String feedbackId;

    private String waitingId;
}
