package com.jkos.appsvc.api.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoxedMessageTargetType {

    USER(1, "一般使用者"),
    MERCHANT(2, "商家"),
    ALL_USER(3, "全體用戶");

    private final int value;
    private final String description;
}