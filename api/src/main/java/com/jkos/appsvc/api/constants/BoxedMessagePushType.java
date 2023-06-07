package com.jkos.appsvc.api.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoxedMessagePushType {

    USER_NORMAL(0, "[會員] 一般推播"),
    USER_WAITING_NEAR(1, "[會員] 餐廳候位提早叫號"),
    USER_WAITING_CALL(2, "[會員] 餐廳候位叫號"),
    USER_ORDER_GET(3, "[會員] 餐廳取餐通知"),
    USER_BOOKING_CANCEL(5, "[會員] 餐廳訂位取消"),
    USER_BOXED_MESSAGE(6, "[會員] 街口消息"),
    USER_TRANSACTION_REFUND(7, "[會員] 交易退款通知"),
    USER_MERCHANT_REPLY_COMMENT(8, "[會員] 店家回覆評論"),
    USER_MERCHANT_MESSAGE(9, "[會員] 店家推播訊息"),
    USER_MERCHANT_COUPON(10, "[會員] 店家發送優惠券"),
    USER_JKOS_FEEDBACK(11, "[會員] 街口回覆意見回饋"),
    USER_RESTAURANT_PAY(12, "[會員] 街口支付成功通知"),
    USER_RESTAURANT_PAY_V1(13, "[會員] 街口支付成功通知 (開啟可收藏店舖付款結果頁)"),
    USER_DELIVERY(14, "[會員] 外送通知"),
    USER_TAXI_CANCEL(15, "叫車取消通知"),
    USER_TAXI_ARRIVE(16, "司機抵達通知"),
    USER_TAXI_CUSTOMER_GET_IN(17, "乘車確認通知"),
    USER_BOOKING_CHECK(18, "訂位確認通知"),
    USER_PARKING_NOTICE(19, "停車繳費通知"),
    USER_TAIWAN_POWER_NOTICE(21, "台電繳費通知"),
    USER_TAIWAN_POWER_MARKETING(22, "台電活動訊息夾通知"),
    USER_BILL_NOTICE(25, "繳費訊息通知網頁"),
    USER_CO_BRANDED_NOTICE(27, "聯名卡快速綁卡通知網頁"),
    USER_ROUTER(28, "App轉導連結"),
    MERCHANT_NORMAL(51, "[店家] 一般推播"),
    MERCHANT_POSITIVE_PAY(52, "[店家] 正掃成功通知推播");

    private final int value;
    private final String description;
}