package com.coy.pay.route.sdk.kalian.dto;

import com.coy.pay.route.sdk.kalian.KalianPayApiResult;

import java.util.List;

/**
 * 退款单查询结果
 *
 * @author yehl
 * @date 2018/8/15 14:10
 */
public class KalianRefundQueryResult extends KalianPayApiResult {

    private String transaction_id;   // 平台订单号
    private String org_total_fee;   // 原交易金额
    private String settlement_total_fee;   // 应结订单金额
    private String fee_type;   // 货币种类
    private String cash_fee;   // 现金支付金额
    private String refund_count;   // 退款笔数
    private List<KalianRefundInf> refund_inf;   // 退款信息

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOrg_total_fee() {
        return org_total_fee;
    }

    public void setOrg_total_fee(String org_total_fee) {
        this.org_total_fee = org_total_fee;
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

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public List<KalianRefundInf> getRefund_inf() {
        return refund_inf;
    }

    public void setRefund_inf(List<KalianRefundInf> refund_inf) {
        this.refund_inf = refund_inf;
    }
}