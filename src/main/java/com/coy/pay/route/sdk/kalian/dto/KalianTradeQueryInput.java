package com.coy.pay.route.sdk.kalian.dto;


import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianTxnNumConsts;

/**
 * 订单查询入参
 * @author yehl
 * @date 2018/8/15 14:21
 */
public class KalianTradeQueryInput extends KalianPayApiInput<KalianTradeQueryResult> {

    public KalianTradeQueryInput() {
        super();
        this.setTxn_num(KalianTxnNumConsts.WeiUnionPay.ORDER_QUERY_TXNNUM);
    }

    private String transaction_id;// 平台订单号

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
