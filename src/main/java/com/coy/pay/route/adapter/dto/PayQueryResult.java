package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 支付查询出参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class PayQueryResult extends PayApiResult {

    private String tradeState;// 交易状态
    private String trade_state_desc;// 交易状态描述
    private String tradeType;// 交易类型 pay.weixin.native
    private String appid;// 公众号appid 服务商公众号appid
    private String openid;// 用户标识 用户在服务商 appid 下的唯一标识
    private String isSubscribe;// 是否关注公众账号 用户是否关注服务商公众账号，Y-关注，N-未关注
    private String subAppid;// 子商户 appid
    private String subOpenid;// 用户在子商户 sub_appid 下的唯一标识
    private String subIsSubscribe;//用户是否关注子公众账号，1-关注，0-未关 注，仅在公众账号类型支付有效

    private String listid;// 订单号
    private String transactionId;// 平台订单号，如威富通平台订单号
    private String outTransactionId;// 第三方订单号 第三方订单号（支付成功后会返回，没支付则不会）

    private Long totalFee;// 总金额，以分为单位，不允许包含任何字、符号
    private Long couponFee;// 现金券金额 现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
    private String feeType;// 货币种类 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
    private String attach;// 附加信息 商家数据包，原样返回
    private String bankType;// 付款银行	银行类型
    private String bankBillno;// 银行订单号 银行订单号，若为微信支付则为空
    private String timeEnd;// 支付完成时间 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自平台服务器

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public String getSubIsSubscribe() {
        return subIsSubscribe;
    }

    public void setSubIsSubscribe(String subIsSubscribe) {
        this.subIsSubscribe = subIsSubscribe;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTransactionId() {
        return outTransactionId;
    }

    public void setOutTransactionId(String outTransactionId) {
        this.outTransactionId = outTransactionId;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Long couponFee) {
        this.couponFee = couponFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankBillno() {
        return bankBillno;
    }

    public void setBankBillno(String bankBillno) {
        this.bankBillno = bankBillno;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
