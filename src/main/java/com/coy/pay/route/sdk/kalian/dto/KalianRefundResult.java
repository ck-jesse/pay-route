package com.coy.pay.route.sdk.kalian.dto;

import com.coy.pay.route.sdk.kalian.KalianPayApiResult;

/**
 * 退款出参
 * @author yehl
 * @date 2018/8/15 13:53
 */
public class KalianRefundResult extends KalianPayApiResult {

    private String refund_no            ; // 平台退款订单号
    private String out_refund_no        ; // 商户退款订单号
    private String refund_channel       ; // 退款渠道
    private String refund_fee           ; // 退款金额
    private String settlement_refund_fee; // 结算退款金额
    private String org_fee              ; // 原交易金额
    private String settlement_total_fee ; // 应结订单金额
    private String fee_type             ; // 货币种类
    private String cash_fee             ; // 现金支付金额
    private String cash_refund_fee      ; // 现金退款金额

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
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

    public String getOrg_fee() {
        return org_fee;
    }

    public void setOrg_fee(String org_fee) {
        this.org_fee = org_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }
}
