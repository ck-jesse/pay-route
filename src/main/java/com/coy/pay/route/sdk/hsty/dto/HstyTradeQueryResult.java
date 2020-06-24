package com.coy.pay.route.sdk.hsty.dto;


import com.coy.pay.route.sdk.hsty.HstyPayApiResult;

/**
 * 交易查询出参
 *
 * @author chenck
 * @date 2018/4/12 18:01
 */
public class HstyTradeQueryResult extends HstyPayApiResult {

    private String trade_status;// 交易状态 1 未支付、2 支付成功、3 已关闭、4 转入退款、8 已冲正、9 已撤销
    private String trade_state_desc;// 交易状态描述

    // 以下字段在 trade_state 为 2 或 4 的时候有返回

    private String api_code;// 交易类型 pay.wechat.native、pay.wechat.micropay
    private String appid;// 公众号appid 服务商公众号appid
    private String openid;// 用户标识 用户在服务商 appid 下的唯一标识
    private String is_subscribe;// 是否关注公众账号 用户是否关注公众账号，1-关注，0-未关注，仅在公众账号类型支付有效
    private String sub_appid;// 子商户 appid
    private String sub_openid;// 用户在子商户 sub_appid 下的唯一标识
    private String sub_is_subscribe;//用户是否关注子公众账号，1-关注，0-未关 注，仅在公众账号类型支付有效

    private String transaction_id;// 汇商订单号
    private String out_transaction_id;// 第三方订单号，如微信订单号（支付成功后会返回，没支付则不会）
    private String out_trade_no;// 商户订单号 商户系统内部的定单号，32个字符内、可包含字母
    private Long trade_money;// 总金额，以分为单位，不允许包含任何字、符号
    private Long coupon_fee;// 现金券金额 现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
    private String fee_type;// 货币种类 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
    private String attach;// 附加信息 商家数据包，原样返回
    private String bank_type;// 付款银行	银行类型
    private String bank_billno;// 银行订单号 银行订单号，若为微信支付则为空
    private String time_end;// 支付完成时间 支付完成时间，格式为 yyyyMMddhhmmss，如2009 年 12 月 27 日 9 点 10 分 10 秒表示为20091227091010。时区为 GMT+8 beijing。该时间取自汇商服务器

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getApi_code() {
        return api_code;
    }

    public void setApi_code(String api_code) {
        this.api_code = api_code;
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

    public Long getTrade_money() {
        return trade_money;
    }

    public void setTrade_money(Long trade_money) {
        this.trade_money = trade_money;
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
}
