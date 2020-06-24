package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;
import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;

/**
 * 【支付宝】【服务窗支付：统一下单API】入参
 *
 * @author chenck
 * @date 2018/8/23 22:00
 */
public class SwiftpassAlipayJSPayInput extends SwiftpassPayApiInput<SwiftpassAlipayJSPayResult> {

    public SwiftpassAlipayJSPayInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.PAY_ALIPAY_JSPAY);
    }

    private String out_trade_no;// 商户系统内部的订单号，32个字符内、可包含字母，确保在商户系统唯一
    private String device_info;// 设备号,威富通支付分配的终端设备号
    private String op_user_id;// 操作员帐号,默认为商户号
    private String op_shop_id;// 门店编号
    private String body;// 商品描述
    private String attach;// 附加信息 商户附加信息，可做扩展参数
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private String mch_create_ip;// 终端ip 订单生成的机器 IP
    private String notify_url;// 通知地址 接收平台通知的URL，需给绝对路径，255字符内格式如:http://wap.tenpay.com/tenpay.asp，确保平台能通过互联网访问该地址
    private String limit_credit_pay;// 是否限制信用卡 限定用户使用时能否使用信用卡，值为1，禁用信用卡；值为0或者不传此参数则不禁用
    private String callback_url;// 前端页面跳转的URL（包括支付成功和关闭时都会跳到这个地址,商户需自行处理逻辑），需给绝对路径，255字符内格式如:http://wap.tenpay.com/callback.asp注:该地址只作为前端页面的一个跳转，须使用notify_url通知结果作为支付最终结果。
    private String time_start;// 订单生成时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String time_expire;// 订单过期时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String qr_code_timeout_express;// 订单最晚付款时间 该笔订单允许的最晚付款时间，逾期将关闭交易，从下单开始计时。取值范围：1m~15d。m-分钟，h-小时，d-天，1c-当天(1c-当天的情况下，无论交易何时创建，都在0点关闭)。该参数数值不接受小数点，如1.5h，可转换为90m。
    private String goods_tag;// 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
    private String product_id;// 商品 ID 预留字段此 id 为静态可打印的二维码中包含的商品 ID，商户自行维护。

    private String buyer_logon_id;// 买家支付宝账号 买家支付宝账号，和buger_id不能同时为空
    private String buyer_id;// 买家支付宝用户ID 和buyer_logon_id不能同时为空 buyer_id获取方法：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.8ujLD6&treeId=115&articleId=104114&docType=1通过网页授权获取用户信息，同步响应结果中的user_id对应文档中的buyer_id。


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

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
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

    public String getQr_code_timeout_express() {
        return qr_code_timeout_express;
    }

    public void setQr_code_timeout_express(String qr_code_timeout_express) {
        this.qr_code_timeout_express = qr_code_timeout_express;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }
}
