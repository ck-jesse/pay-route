package com.coy.pay.route.sdk.swiftpass.constant;

/**
 * 【威富通支付api】常量类
 *
 * @Author chenck
 * @Date 2018/3/9 14:58
 */
public class SwiftpassPayApiConsts {

    /**
     * 统一下单API
     */
    // 【微信】【扫码支付：统一下单API】初始化扫码请求，通过该请求生成二维码来进行扫码支付。 返回结果+通知：后台请求交互模式+后台通知交互模式
    public static final String PAY_WEIXIN_NATIVE = "pay.weixin.native";

    // 【微信】【公众号&小程序支付：初始化请求API】 初始化JSAPI请求，通过生成pay_info来进行交互验证。 返回结果+通知：后台请求交互模式+后台通知交互模式
    public static final String PAY_WEIXIN_JSPAY = "pay.weixin.jspay";

    // 【微信】【APP支付：非原生态预下单API】 初始化支付请求，通过该请求得到支持的支付类型和授权码。返回结果+通知：后台请求交互模式+后台通知交互模式
    public static final String UNIFIED_TRADE_PAY = "unified.trade.pay";

    // 【微信】【刷卡支付：提交刷卡支付API】收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付对用户进行收款
    public static final String UNIFIED_TRADE_MICROPAY = "unified.trade.micropay";

    // 【微信】银联JSAPI（暂未使用）
    public static final String PAY_WEIXIN_JSPAYV2 = "pay.weixin.jspayv2";

    // 【支付宝】【扫码支付：支付API】
    public static final String PAY_ALIPAY_NATIVE = "pay.alipay.native";

    // 【支付宝】【服务窗支付：统一下单API】
    public static final String PAY_ALIPAY_JSPAY = "pay.alipay.jspay";

    /**
     * 交易查询API
     */
    public static final String UNIFIED_TRADE_QUERY = "unified.trade.query";
    /**
     * 退款API
     */
    public static final String UNIFIED_TRADE_REFUND = "unified.trade.refund";
    /**
     * 退款查询API
     */
    public static final String UNIFIED_TRADE_REFUNDQUERY = "unified.trade.refundquery";


    /**
     * 退款来源
     * 注：用于限制对于电子卡业务产生的支付订单，必须通过双方约定的退款来源字段进行验证后才能退款
     */
    public static final String REFUND_SOURCE_KEY = "MEMEPPCD67DC18FC53451296032BBAEB1850403";

    // 威富通不同的商户存在不同的签名类型
    public static final String MD5 = "MD5";
    public static final String RSA_1_256 = "RSA_1_256";

    /** 0表示成功，非0表示失败此字段是通信标识 */
    public static final String SUCCESS_CODE = "0";


    /** 表示HttpClient连接池最大并发连接数Key */
    public static final String HTTPCLIENT_SWIFTPASS_MAX_CONN_TOTAL_KEY = "httpclient.swiftpass.max.conn.total";
    /** 表示HttpClient连接池单路由的最大并发连接数Key */
    public static final String HTTPCLIENT_SWIFTPASS_MAX_CONN_PER_ROUTE_KEY = "httpclient.swiftpass.max.conn.per.route";

    /** 连接主机超时（单位：毫秒）默认10秒Key */
    public static final String HTTPCLIENT_SWIFTPASS_DEFAULT_CONNECT_TIME_OUT_KEY = "httpclient.swiftpass.max.conn.total";
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒Key */
    public static final String HTTPCLIENT_SWIFTPASS_DEFAULT_READ_TIME_OUT_KEY = "httpclient.swiftpass.max.conn.per.route";

    /** 表示HttpClient连接池最大并发连接数,默认300 */
    public static final int HTTPCLIENT_SWIFTPASS_DEFAULT_MAX_CONN_TOTAL = 300;
    /** 表示HttpClient连接池单路由的最大并发连接数,默认150 */
    public static final int HTTPCLIENT_SWIFTPASS_DEFAULT_MAX_CONN_PER_ROUTE = 150;
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒 */
    public static final int HTTPCLIENT_SWIFTPASS_DEFAULT_CONNECT_TIME_OUT = 5 * 1000;
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒 */
    public static final int HTTPCLIENT_SWIFTPASS_DEFAULT_READ_TIME_OUT = 5 * 1000;

}
