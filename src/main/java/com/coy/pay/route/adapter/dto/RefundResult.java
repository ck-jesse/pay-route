package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 退款出参
 * 将不同第三方支付api的退款结果对象，包装为该对象，用于统一出口
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class RefundResult extends PayApiResult {

    private String transactionId;// 平台订单号，如威富通平台订单号
    private String outTransactionId;// 第三方订单号，如微信订单号
    private String listid;// 商户订单号,商户系统内部的订单号
    private String refundListid;// 商户退款单号，商户退款单号
    private String refundId;// 平台退款单号，如威富通平台退款订单号
    private String refundChannel;// ORIGINAL-原路退款，默认
    private Long refundFee;// 退款金额, 退款总金额,单位为分,可以做部分退款

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

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getRefundListid() {
        return refundListid;
    }

    public void setRefundListid(String refundListid) {
        this.refundListid = refundListid;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

}
