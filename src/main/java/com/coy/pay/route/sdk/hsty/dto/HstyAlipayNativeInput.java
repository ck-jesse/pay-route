package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;

/**
 * 【支付宝扫码支付接口】入参
 *
 * @author chenck
 * @date 2018/8/23 22:24
 */
public class HstyAlipayNativeInput extends HstyPayApiInput<HstyAlipayNativeResult> {

    public HstyAlipayNativeInput() {
        super();
        // 初始化服务名称
        this.setService(HstyPayApiConsts.PAY_ALIPAY_NATIVE);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的定单号，32个字符内、可包含字母
    private String device_info;// 设备号,威富通支付分配的终端设备号
    private String body;// 商品描述
    private String attach;// 附加信息 商户附加信息，可做扩展参数
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private String mch_create_ip;// 终端ip 订单生成的机器 IP
    private String notify_url;// 通知地址 接收平台通知的URL，需给绝对路径，255字符内格式如:http://wap.tenpay.com/tenpay.asp，确保平台能通过互联网访问该地址
    //    private String callback_url;// 前端页面跳转的URL（包括支付成功和关闭时都会跳到这个地址,商户需自行处理逻辑），需给绝对路径，255字符内格式如:http://wap.tenpay.com/callback.asp注:该地址只作为前端页面的一个跳转，须使用notify_url通知结果作为支付最终结果。
    //    private String time_start;// 订单生成时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。注：订单生成时间与超时时间需要同时传入才会生效。
    //    private String time_expire;// 订单过期时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String expire_time;// 订单失效时间，格式为 yyyymmddhhmmss，如2009 年 12 月 25 日 9 点 10 分 10 秒表示为20091225091010。时区为 GMT+8 beijing。该时间取自商户服务器
    private String op_user_id;// 操作员帐号,默认为商户号
    private String goods_tag;// 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public String getMch_create_ip() {
        return mch_create_ip;
    }

    public void setMch_create_ip(String mch_create_ip) {
        this.mch_create_ip = mch_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }
}
