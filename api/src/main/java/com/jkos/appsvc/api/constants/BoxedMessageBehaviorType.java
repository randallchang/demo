package com.jkos.appsvc.api.constants;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_BILL_NOTICE;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_BOOKING_CANCEL;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_BOOKING_CHECK;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_BOXED_MESSAGE;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_DELIVERY;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_JKOS_FEEDBACK;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_MERCHANT_COUPON;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_MERCHANT_REPLY_COMMENT;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_ORDER_GET;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_PARKING_NOTICE;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_ROUTER;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_TAIWAN_POWER_MARKETING;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_TAIWAN_POWER_NOTICE;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_TAXI_CANCEL;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_TRANSACTION_REFUND;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_WAITING_CALL;
import static com.jkos.appsvc.api.constants.BoxedMessagePushType.USER_WAITING_NEAR;

@Getter
public enum BoxedMessageBehaviorType {

    BOXED_MESSAGE(1, "街口發的推播訊息", USER_BOXED_MESSAGE),
    CALL_NUM_FOR_MEAL(2, "點餐(內用/外帶)叫號", USER_BOXED_MESSAGE),
    CALL_PREVIOUS_NUM(3, "候位前一號叫號", USER_WAITING_NEAR),
    CALL_CURRENT_NUM(4, "候位到號叫號", USER_WAITING_CALL),
    CANCEL_ORDER(5, "點餐(內用/外帶)取消訂單", USER_ORDER_GET),
    CANCEL_DELIVERY_ORDER(6, "外送取消訂單", USER_DELIVERY),
    CANCEL_RESERVATION(7, "訂位取消", USER_BOOKING_CANCEL),
    CANCEL_CALL_TAXI(8, "叫車取消通知", USER_TAXI_CANCEL),
    REFUND(9, "退款通知", USER_TRANSACTION_REFUND),
    COUPON(10, "店家發送優惠券通知", USER_MERCHANT_COUPON),
    REPLY(11, "店家回覆評論", USER_MERCHANT_REPLY_COMMENT),
    FEED_BACK(12, "客服回覆客訴/意見回報", USER_JKOS_FEEDBACK),
    RESERVATION_CONFIRM(13, "訂位確認推播訊息", USER_BOOKING_CHECK),
    CREDIT_CARD_EVENT_END(14, "信用卡活動結束通知", USER_BOXED_MESSAGE),
    PROMOTION_EVENT_END(15, "行銷交易折抵活動結束通知", USER_BOXED_MESSAGE),
    PARKING_NOTICE(19, "停車繳費通知", USER_PARKING_NOTICE),
    TAIWAN_POWER_NOTICE(21, "台電繳費通知", USER_TAIWAN_POWER_NOTICE),
    TAIWAN_POWER_MARKETING(22, "台電活動通知", USER_TAIWAN_POWER_MARKETING),
    BILL_NOTICE(25, "繳費訊息通知網頁", USER_BILL_NOTICE),
    ROUTER(26, "App轉導連結", USER_ROUTER);

    private final int value;
    private final BoxedMessagePushType boxedMessagePushType;
    private final String description;

    @Getter
    private static Map<Integer, BoxedMessageBehaviorType> mappings = new HashMap<>(32);

    private BoxedMessageBehaviorType(
            int value,
            String description,
            BoxedMessagePushType boxedMessagePushType) {

        this.value = value;
        this.boxedMessagePushType = boxedMessagePushType;
        this.description = description;

        getMappings().put(value, this);
    }

    public static BoxedMessageBehaviorType forValue(int value) {

        BoxedMessageBehaviorType rtn = getMappings().get(value);
        if(rtn == null){
            return BOXED_MESSAGE;
        }

        return rtn;
    }
}