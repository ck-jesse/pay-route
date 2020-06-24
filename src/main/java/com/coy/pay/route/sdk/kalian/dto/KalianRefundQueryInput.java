package com.coy.pay.route.sdk.kalian.dto;


import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianTxnNumConsts;

/**
 * 查询退款API
 *
 * @author chenck
 * @date 2018/4/12 17:12
 */
public class KalianRefundQueryInput extends KalianPayApiInput<KalianRefundQueryResult> {

    public KalianRefundQueryInput() {
        super();
        this.setTxn_num(KalianTxnNumConsts.WeiUnionPay.REFUND_QUERY_TXNNUM);
    }

    /**
     * 平台退款单号refund_no、out_refund_no、out_order_no 、transaction_id 四个参数必填一个，
     * 如果同时存在优先级为：refund_no>out_refund_no>transaction_id>out_oder_no
     * */

    private String transaction_id; // 商户系统内部的订单号
    private String      refund_no; // 退款订单号
    private String  out_refund_no; // 商户退款订单号

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

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
}
