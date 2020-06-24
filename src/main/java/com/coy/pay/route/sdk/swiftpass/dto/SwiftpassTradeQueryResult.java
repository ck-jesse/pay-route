package com.coy.pay.route.sdk.swiftpass.dto;


import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;

/**
 * 威富通交易查询出参
 *
 * @Author chenck
 * @Date 2018/3/9 11:10
 */
public class SwiftpassTradeQueryResult extends SwiftpassPayApiResult {

    private String trade_state;// 交易状态 SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 PAYERROR—支付失败(其他原因，如银行返回失败)
    private String trade_state_desc;// 交易状态描述

    // 以下字段在 trade_state为 SUCCESS的时候有返回

    private String trade_type;// 交易类型 pay.weixin.native
    private String appid;// 公众号appid 服务商公众号appid
    private String openid;// 用户标识 用户在服务商 appid 下的唯一标识
    private String is_subscribe;// 是否关注公众账号 用户是否关注服务商公众账号，Y-关注，N-未关注
    private String sub_appid;// 子商户 appid
    private String sub_openid;// 用户在子商户 sub_appid 下的唯一标识
    private String sub_is_subscribe;//用户是否关注子公众账号，1-关注，0-未关 注，仅在公众账号类型支付有效

    private String transaction_id;// 平台订单号 平台交易单号
    private String out_transaction_id;// 第三方订单号，如微信订单号（支付成功后会返回，没支付则不会）
    private String out_trade_no;// 商户订单号 商户系统内部的定单号，32个字符内、可包含字母
    private Long total_fee;// 总金额，以分为单位，不允许包含任何字、符号
    private Long coupon_fee;// 现金券金额 现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
    private String fee_type;// 货币种类 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
    private String attach;// 附加信息 商家数据包，原样返回
    private String bank_type;// 付款银行	银行类型
    private String bank_billno;// 银行订单号 银行订单号，若为微信支付则为空
    private String time_end;// 支付完成时间 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自平台服务器

    private String promotion_detail;// 促销细节

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_openid() {
        return sub_openid;
    }

    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }

    public String getSub_is_subscribe() {
        return sub_is_subscribe;
    }

    public void setSub_is_subscribe(String sub_is_subscribe) {
        this.sub_is_subscribe = sub_is_subscribe;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_transaction_id() {
        return out_transaction_id;
    }

    public void setOut_transaction_id(String out_transaction_id) {
        this.out_transaction_id = out_transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public Long getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Long coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_billno() {
        return bank_billno;
    }

    public void setBank_billno(String bank_billno) {
        this.bank_billno = bank_billno;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getPromotion_detail() {
        return promotion_detail;
    }

    public void setPromotion_detail(String promotion_detail) {
        this.promotion_detail = promotion_detail;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
}
