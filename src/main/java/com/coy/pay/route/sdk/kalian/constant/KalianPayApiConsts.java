package com.coy.pay.route.sdk.kalian.constant;

/**
 * 【中旅银行支付相关】常量类
 * @author yehl
 * @date 2018/8/15 11:30
 */
public class KalianPayApiConsts {

    /**
     * 微信数据地址：地址：http://woap.kalian168.com/AMPS/pay/gateway
     *
     * 支付宝数据地址：地址：http://woap.kalian168.com/AMPS/aliPay/gateway
     *
     * 测试商户号：472100000003313  测试签名KEY：3fgaaswkg84ipvm469tz0839dw07ey3u 微信
     * 测试商户号：472100000473713  测试签名KEY：4vj6xfxxjwmi3wuhpi0m5eeg5mfjnx9p 银联微信
     *
     */
    /**  卡联微信支付接口地址 */
    public static final String KALIAN_WEIXIN_PAY_API = "pay.kalian.weixin.api.url";
    public static final String KALIAN_DEFAULT_WEIXIN_PAY_API = "http://woap.kalian168.com/AMPS/pay/gateway";
    public static final String KALIAN_UNION_WEIXIN_PAY_API = "pay.kalian.union.weixin.api.url";
    public static final String KALIAN_UNION_DEFAULT_WEIXIN_PAY_API = "https://jhzf.jzctb.com/AMPS/uwxPay/gateway";

    /** 表示HttpClient连接池最大并发连接数Key */
    public static final String HTTPCLIENT_CTSBANK_MAX_CONN_TOTAL_KEY = "httpclient.kalian.max.conn.total";
    /** 表示HttpClient连接池单路由的最大并发连接数Key */
    public static final String HTTPCLIENT_CTSBANK_MAX_CONN_PER_ROUTE_KEY = "httpclient.kalian.max.conn.per.route";

    /** 连接主机超时（单位：毫秒）默认10秒Key */
    public static final String HTTPCLIENT_CTSBANK_DEFAULT_CONNECT_TIME_OUT_KEY = "httpclient.kalian.max.conn.total";
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒Key */
    public static final String HTTPCLIENT_CTSBANK_DEFAULT_READ_TIME_OUT_KEY = "httpclient.kalian.max.conn.per.route";

    /** 表示HttpClient连接池最大并发连接数,默认300 */
    public static final int HTTPCLIENT_CTSBANK_DEFAULT_MAX_CONN_TOTAL = 300;
    /** 表示HttpClient连接池单路由的最大并发连接数,默认150 */
    public static final int HTTPCLIENT_CTSBANK_DEFAULT_MAX_CONN_PER_ROUTE = 150;
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒 */
    public static final int HTTPCLIENT_CTSBANK_DEFAULT_CONNECT_TIME_OUT = 5 * 1000;
    /** 设置从主机读取数据超时（单位：毫秒）默认10秒 */
    public static final int HTTPCLIENT_CTSBANK_DEFAULT_READ_TIME_OUT = 5 * 1000;
}
