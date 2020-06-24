package com.coy.pay.route.adapter.type;

/**
 * 自定义api标识
 * 说明：定义支付通道支持的功能的api列表，通过该自定义api标识获取支付通道对应的api，然后调用
 *
 * @author chenck
 * @date 2018/8/22 13:29
 */
public enum CustomApiIdEnum {

    PAY_WEIXIN_NATIVE("PAY_WEIXIN_NATIVE", "【微信】扫码支付"),
    PAY_WEIXIN_JSPAY("PAY_WEIXIN_JSPAY", "【微信】公众号&小程序支付"),

    PAY_ALIPAY_NATIVE("PAY_ALIPAY_NATIVE", "【支付宝】扫码支付"),
    PAY_ALIPAY_JSPAY("PAY_ALIPAY_JSPAY", "【支付宝】服务窗支付"),//

    TRADE_QUERY("TRADE_QUERY", "交易单查询"),
    TRADE_REFUND("TRADE_REFUND", "退款"),
    TRADE_REFUND_QUERY("TRADE_REFUND_QUERY", "退款单查询"),;

    private CustomApiIdEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;// 自定义api标识 用于通过该标识获取支付通道对应的api
    private String name;// 名称

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getName(String value) {
        for (CustomApiIdEnum type : CustomApiIdEnum.values()) {
            if (type.getValue().equals(value)) {
                return type.getName();
            }
        }
        return null;
    }
}
