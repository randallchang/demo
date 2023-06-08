package com.jkos.appsvc.api.model.msg_hub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgHubResponse {

    private String code;
    private String message;
    private List<BoxedMessage> data;
}
