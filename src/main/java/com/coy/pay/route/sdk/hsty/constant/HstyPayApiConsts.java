package com.coy.pay.route.sdk.hsty.constant;

/**
 * 【汇商通盈支付api】常量类
 *
 * @author chenck
 * @date 2018/4/12 15:46
 */
public class HstyPayApiConsts {


    // 汇商支付网关地址
    public static final String PAY_HSTYPAY_URL = "pay.hstypay.api.url";
    // 汇商支付网关默认地址
    public static final String PAY_HSTYPAY_URL_DEFAULT = "https://pay.hstypay.com/pay/gateway";

    /**
     * 微信扫码支付接口
     */
    public static final String PAY_WECHAT_NATIVE = "pay.wechat.native";
    /**
     * 微信公众号支付接口
     */
    public static final String PAY_WECHAT_JSPAY = "pay.wechat.jspay";
    /**
     * 微信公众号支付接口(便于汇商区分是否走充值的费率)
     */
    public static final String PAY_WECHAT_JSPAY_CZ = "pay.wechat.jspay.cz";

    /**
     * 支付宝扫码支付接口
     */
    public static final String PAY_ALIPAY_NATIVE = "pay.alipay.native";
    /**
     * 支付宝 Wap(H5)支付接口
     */
    public static final String PAY_ALIPAY_JSPAY = "pay.alipay.jspay";


    /**
     * 交易查询API
     */
    public static final String UNIFIED_TRADE_QUERY = "unified.trade.query";
    /**
     * 退款API
     */
    public static final String UNIFIED_TRADE_REFUND = "unified.refund.apply";
    /**
     * 退款查询API
     */
    public static final String UNIFIED_TRADE_REFUNDQUERY = "unified.refund.query";


    /**
     * 表示HttpClient连接池最大并发连接数Key
     */
    public static final String HTTPCLIENT_HSTY_MAX_CONN_TOTAL_KEY = "httpclient.hsty.max.conn.total";
    /**
     * 表示HttpClient连接池单路由的最大并发连接数Key
     */
    public static final String HTTPCLIENT_HSTY_MAX_CONN_PER_ROUTE_KEY = "httpclient.hsty.max.conn.per.route";

    /**
     * 连接主机超时（单位：毫秒）默认10秒Key
     */
    public static final String HTTPCLIENT_HSTY_DEFAULT_CONNECT_TIME_OUT_KEY = "httpclient.hsty.max.conn.total";
    /**
     * 设置从主机读取数据超时（单位：毫秒）默认10秒Key
     */
    public static final String HTTPCLIENT_HSTY_DEFAULT_READ_TIME_OUT_KEY = "httpclient.hsty.max.conn.per.route";

    /**
     * 表示HttpClient连接池最大并发连接数,默认300
     */
    public static final int HTTPCLIENT_HSTY_DEFAULT_MAX_CONN_TOTAL = 300;
    /**
     * 表示HttpClient连接池单路由的最大并发连接数,默认150
     */
    public static final int HTTPCLIENT_HSTY_DEFAULT_MAX_CONN_PER_ROUTE = 150;
    /**
     * 设置从主机读取数据超时（单位：毫秒）默认10秒
     */
    public static final int HTTPCLIENT_HSTY_DEFAULT_CONNECT_TIME_OUT = 5 * 1000;
    /**
     * 设置从主机读取数据超时（单位：毫秒）默认10秒
     */
    public static final int HTTPCLIENT_HSTY_DEFAULT_READ_TIME_OUT = 5 * 1000;
}
