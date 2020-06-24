package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;

/**
 * 【微信】【公众号&小程序支付：初始化请求API】入参
 *
 * @author chenck
 * @date 2018/8/21 19:26
 */
public class SwiftpassWeixinJSPayInput extends SwiftpassPayApiInput<SwiftpassWeixinJSPayResult> {

    public SwiftpassWeixinJSPayInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.PAY_WEIXIN_JSPAY);
    }

    // 公众号或小程序支付 特有字段
    private String is_raw;// 原生JS	值为1：是；值为0：否
    private String is_minipg;// 是否小程序支付	值为1，表示小程序支付；不传或值不为1，表示公众账号内支付

    private String out_trade_no;// 商户订单号,商户系统内部的定单号，32个字符内、可包含字母
    private String device_info;// 设备号,威富通支付分配的终端设备号
    private String op_user_id;// 操作员帐号,默认为商户号
    private String op_shop_id;// 门店编号
    private String body;// 商品描述
    private String sub_openid;// 用户openid 微信用户关注商家公众号的openid
    private String sub_appid;// 公众账号或小程序ID
    private String attach;// 附加信息 商户附加信息，可做扩展参数
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private boolean need_receipt;// 电子发票 需要和微信公众平台的发票功能联合，传入true时，微信支付成功消息和支付详情页将出现开票入口[新增need_receipt【适用于微信】]
    private String mch_create_ip;// 终端ip 订单生成的机器 IP
    private String notify_url;// 通知地址 接收平台通知的URL，需给绝对路径，255字符内格式如:http://wap.tenpay.com/tenpay.asp，确保平台能通过互联网访问该地址
    private String callback_url;// 前端页面跳转的URL（包括支付成功和关闭时都会跳到这个地址,商户需自行处理逻辑），需给绝对路径，255字符内格式如:http://wap.tenpay.com/callback.asp注:该地址只作为前端页面的一个跳转，须使用notify_url通知结果作为支付最终结果。
    private String time_start;// 订单生成时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String time_expire;// 订单过期时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String goods_tag;// 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
    //    private String product_id;// 商品 ID 预留字段此 id 为静态可打印的二维码中包含的商品 ID，商户自行维护。
    private String limit_credit_pay;// 是否限制信用卡 限定用户使用时能否使用信用卡，值为1，禁用信用卡；值为0或者不传此参数则不禁用

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

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getOp_shop_id() {
        return op_shop_id;
    }

    public void setOp_shop_id(String op_shop_id) {
        this.op_shop_id = op_shop_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public boolean isNeed_receipt() {
        return need_receipt;
    }

    public void setNeed_receipt(boolean need_receipt) {
        this.need_receipt = need_receipt;
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

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

//    public String getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(String product_id) {
//        this.product_id = product_id;
//    }

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
    }
}
