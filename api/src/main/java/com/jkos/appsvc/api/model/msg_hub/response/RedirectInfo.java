package com.jkos.appsvc.api.model.msg_hub.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RedirectInfo {

    private String url;
    private Long consumeId;
    private Long reservationId;
    private String commentId;
    private String feedbackId;
    private String waitingId;
    private String orderId;
}
