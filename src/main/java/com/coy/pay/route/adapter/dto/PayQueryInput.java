package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;

/**
 * 支付查询入参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class PayQueryInput extends PayApiInput<PayQueryResult> {

    public PayQueryInput() {
        super();
        // 设置api标识
        this.setApiId(CustomApiIdEnum.TRADE_QUERY.getValue());
    }

    private String listid;// 订单号
    private String transactionId;// 平台订单号，如威富通平台订单号

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
