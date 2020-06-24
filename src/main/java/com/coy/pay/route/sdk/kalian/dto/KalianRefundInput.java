package com.coy.pay.route.sdk.kalian.dto;


import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianTxnNumConsts;

/**
 * 退款输入参数
 * @author yehl
 * @date 2018/8/15 13:44
 */
public class KalianRefundInput extends KalianPayApiInput<KalianRefundResult> {

    public KalianRefundInput() {
        super();
        this.setTxn_num(KalianTxnNumConsts.WeiUnionPay.REFUND_TXNNUM);
    }

    private String transaction_id; // 平台订单号【商户系统内部的订单号, out_order_no和transaction_id至少一个必填，同时存在时transaction_id优先】
    /**  必填  */
    private String out_refund_no; // 商户退款订单号【商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔】
    /**  必填  */
    private String org_fee; // 原交易金额
    /**  必填  */
    private String refund_fee; // 退款金额
    private String refund_fee_type; // 货币类型
    /**  必填  */
    private String op_user_id; // 操作员【操作员帐号, 默认为商户号】
    private String refund_account; // 退款资金来源【REFUND_SOURCE_UNSETTLED_FUNDS:未结算资金退款（默认）REFUND_SOURCE_RECHARGE_FUNDS:可用余额退款】

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getOrg_fee() {
        return org_fee;
    }

    public void setOrg_fee(String org_fee) {
        this.org_fee = org_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }
}
