package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiResult;

/**
 * 退款出参
 *
 * @author chenck
 * @date 2018/4/12 17:00
 */
public class HstyRefundResult extends HstyPayApiResult {

    private String transaction_id;// 汇商订单号
    private String out_trade_no;// 商户订单号,商户系统内部的订单号
    private String out_refund_no;// 商户退款单号
    private String refund_id;// 汇商退款单号
    private String refund_channel;// 1—原路退款，默认 2—退回到余额
    private Long refund_fee;// 退款金额, 退款总金额,单位为分,可以做部分退款

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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
}
