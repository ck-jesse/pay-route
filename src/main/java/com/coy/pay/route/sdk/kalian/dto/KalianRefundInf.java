package com.coy.pay.route.sdk.kalian.dto;

public class KalianRefundInf {
    private String out_refund_no;   // 商户退款订单号
    private String refund_account;   // 退款资金来源
    private String refund_channel;   // 退款渠道
    private String refund_fee;   // 退款金额
    private String refund_no;   // 平台退款订单号
    private String refund_status;   // 退款状态
    private String refund_recv_account;   // 退款入账账户
    private String settlement_refund_fee;   // 结算退款金额

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(String settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getRefund_recv_account() {
        return refund_recv_account;
    }

    public void setRefund_recv_account(String refund_recv_account) {
        this.refund_recv_account = refund_recv_account;
    }
}