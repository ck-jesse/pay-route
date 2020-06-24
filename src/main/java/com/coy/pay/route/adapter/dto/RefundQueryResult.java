package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 退款查询出参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class RefundQueryResult extends PayApiResult {

    private String transactionId;// 平台订单号，如威富通平台订单号
    private String outTransactionId;// 第三方订单号，如微信订单号
    private String listid;// 商户订单号,商户系统内部的订单号

    private String refundListid;// 商户退款单号，商户退款单号
    private String outRefundId;// 第三方退款订单号，如微信退款订单号
    private String refundId;// 平台退款单号，如威富通平台退款订单号
    private String refundChannel;// ORIGINAL-原路退款，默认
    private Long refundFee;// 退款金额, 单位为分
    private String refundTime;// 退款时间 yyyyMMddHHmmss
    private String refundState;// 退款状态： SUCCESS—退款成功 FAIL—退款失败 PROCESSING—退款处理中 NOTSURE—未确定， 需要商户原退款单号重新发起 CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者平台转账的方式进行退款。


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

    public String getOutRefundId() {
        return outRefundId;
    }

    public void setOutRefundId(String outRefundId) {
        this.outRefundId = outRefundId;
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

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }
}
