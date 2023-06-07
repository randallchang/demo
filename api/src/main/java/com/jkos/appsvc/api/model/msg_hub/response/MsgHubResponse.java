package com.jkos.appsvc.api.model.msg_hub.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MsgHubResponse {

    private String code;
    private String message;
    private List<BoxedMessage> data;
}
