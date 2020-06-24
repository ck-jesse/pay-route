package com.coy.pay.route.exception;

/**
 * 第1位1表示系统错误，2表示业务错误；
 * 错误码前2～5位是模块名，本模块为9103;
 * 后五位为具体错误
 *
 * @Author chenck
 * @Date 2018/3/9 18:03
 */
public class PayApiError {

    public static final String SYSTEM_ERROR = "1910300000";//系统错误
    public static final String RES_SIGN_ERROR = "2910300000";//返回结果签名错误
    public static final String RES_TIME_OUT_ERROR = "2910300001";//交易超时
    public static final String RES_OTHER_ERROR = "2910300002";//其他异常
    public static final String RES_PAY_ERROR = "2910300003";//请求交易异常
    public static final String RES_PAY_PARAM_ERROR = "2910300004";//参数错误

    // 从300开始递增，预留300以前的错误码
    public static final String ERR_APP = "2910300300";// 系统异常
    public static final String ERR_PARAM = "2910300301";// 参数错误
    public static final String ERR_CONFIG = "2910300302";// 配置错误


}
