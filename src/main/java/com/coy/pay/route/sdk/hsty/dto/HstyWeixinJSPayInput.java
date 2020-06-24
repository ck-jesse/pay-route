package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;

/**
 * 【微信公众号支付接口】入参
 *
 * @author chenck
 * @date 2018/8/23 22:03
 */
public class HstyWeixinJSPayInput extends HstyPayApiInput<HstyWeixinJSPayResult> {

    public HstyWeixinJSPayInput() {
        super();
        // 初始化服务名称
        this.setService(HstyPayApiConsts.PAY_WECHAT_JSPAY_CZ);
    }

    private String out_trade_no;//商户系统内部的订单号 32个字符内、可包含字母【必填】
    private String device_info;// 设备号,威富通支付分配的终端设备号【必填】
    private String body;// 商品描述【必填】
    private String attach;//附加信息 可做扩展参数 255字符内
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号【必填】
    private String mch_create_ip;// 终端ip【必填】
    private String sub_openid;// 用户的微信公众号openid【必填】 微信用户关注商家公众号的 openid（注：使用测试号时此参数置空，即不要传这个参数， 使用正式商户号时才传入，参数名是 sub_openid，具体请看文档最后注意事项第 7 点）
    private String sub_appid;// 当发起公众号支付时，值是微信公众平台基 本配置中的 AppID(应用 ID)；当发起小程序 支付时，值是对应小程序的  AppID
    private String is_raw;// 值为 1：是（对应文档 6.2 一节）；值为 0：否 （对应文档 6.3 一节）；不传默认是 0
    private String is_minipg;// 小程序支付 值为1：是；
    private String notify_url;// 通知地址【必填】
    private String callback_url;// 前台跳转地址
    private String expire_time;// 订单失效时间 yyyymmddhhmmss
    private String op_user_id;// 操作员账号【必填】
    private String goods_tag;// 商品标记
    private String limit_credit_pay;// 限定用户使用微信支付时能否使用信用卡， 值为 1，禁用信用卡；值为 0 或者不传此参数

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

    public String getSub_openid() {
        return sub_openid;
    }

    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getIs_raw() {
        return is_raw;
    }

    public void setIs_raw(String is_raw) {
        this.is_raw = is_raw;
    }

    public String getIs_minipg() {
        return is_minipg;
    }

    public void setIs_minipg(String is_minipg) {
        this.is_minipg = is_minipg;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
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

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
    }
}
