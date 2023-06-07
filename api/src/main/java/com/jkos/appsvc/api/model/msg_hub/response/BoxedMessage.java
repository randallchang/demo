package com.jkos.appsvc.api.model.msg_hub.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoxedMessage {

    private String title;
    private String content;
    private RedirectInfo redirectInfo;
    private Integer tagType;
    private Integer behaviorType;
    private String createdTime;
}
