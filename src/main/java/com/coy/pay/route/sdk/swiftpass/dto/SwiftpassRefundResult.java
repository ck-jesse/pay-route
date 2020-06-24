package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;

/**
 * 威富通退款出参
 *
 * @Author chenck
 * @Date 2018/3/9 10:41
 */
public class SwiftpassRefundResult extends SwiftpassPayApiResult {

    private String trade_type;// 交易类型
    private String transaction_id;// 平台订单号，如威富通平台订单号
    private String out_transaction_id;// 第三方订单号，如微信订单号
    private String out_trade_no;// 商户订单号,商户系统内部的订单号
    private String out_refund_no;// 商户退款单号
    private String refund_id;// 平台退款单号，如威富通平台退款订单号
    private String refund_channel;// ORIGINAL-原路退款，默认
    private Long refund_fee;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private Long coupon_refund_fee;// 现金券退款金额, 现金券退款金额 <= 退款金额， 退款金额-现金券退款金额为现金

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
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

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Long getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Long getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Long coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }
}
