package com.coy.pay.route.adapter.consts;

public class PayConstants {

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final String CHARSET_GBK = "GBK";
    //威富通网关地址
    public static final String PAY_WFT_URL = "pay.wft.api.url";
    //威富通默认网关地址
    public static final String PAY_WFT_URL_DEFAULT = "https://pay.swiftpass.cn/pay/gateway";
    //威富通支付接口名
    public static final String PAY_WFT_TRADE_API = "unified.trade.pay";
    public static final String PAY_WFT_JSPAY_API = "pay.weixin.jspay";

    public static final String PAY_WFT_JSPAY_API_V2 = "pay.weixin.jspayv2";

    public static final String PAY_WFT_NATIVE_API = "pay.weixin.native";
    public static final String PAY_WFT_DEVICE_INFO = "pay.wft.device.info";//默认设备号

    //充值费率通道模式，汇商充值费率通道(pay.wechat.jspay.cz)，默认走原来费率(pay.wechat.jspay)
    public static final String SERVICE_PAY_WECHAT_MODE = "service.pay.wechat.mode";

    public static final int MD5 = 1;

    public static final int RSA = 2;

    // 发起支付的客户端类型
    public static final int APP = 1;// app客户端
    public static final int H5 = 2;// H5客户端
    public static final int PC = 3;// PC客户端
    public static final int WXA = 4;//小程序
}
