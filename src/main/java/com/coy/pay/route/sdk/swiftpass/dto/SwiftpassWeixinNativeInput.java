package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;

/**
 * 【微信】【扫码支付：统一下单API】入参
 *
 * @author chenck
 * @date 2018/8/21 19:26
 */
public class SwiftpassWeixinNativeInput extends SwiftpassPayApiInput<SwiftpassWeixinNativeResult> {

    public SwiftpassWeixinNativeInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.PAY_WEIXIN_NATIVE);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的定单号，32个字符内、可包含字母
    private String device_info;// 设备号,威富通支付分配的终端设备号
    private String op_user_id;// 操作员帐号,默认为商户号
    private String op_shop_id;// 门店编号
    private String body;// 商品描述
    private String attach;// 附加信息 商户附加信息，可做扩展参数
    private String sub_appid;// 公众账号或小程序ID
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private boolean need_receipt;// 电子发票 需要和微信公众平台的发票功能联合，传入true时，微信支付成功消息和支付详情页将出现开票入口[新增need_receipt【适用于微信】]
    private String mch_create_ip;// 终端ip 订单生成的机器 IP
    private String notify_url;// 通知地址 接收平台通知的URL，需给绝对路径，255字符内格式如:http://wap.tenpay.com/tenpay.asp，确保平台能通过互联网访问该地址
    private String time_start;// 订单生成时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String time_expire;// 订单过期时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注：订单生成时间与超时时间需要同时传入才会生效。
    private String goods_tag;// 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
    private String product_id;// 商品 ID 预留字段此 id 为静态可打印的二维码中包含的商品 ID，商户自行维护。
    private String limit_credit_pay;// 是否限制信用卡 限定用户使用时能否使用信用卡，值为1，禁用信用卡；值为0或者不传此参数则不禁用

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

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
    }
}
