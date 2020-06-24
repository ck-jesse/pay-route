package com.coy.pay.route.sdk.swiftpass.dto;


import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;

/**
 * 威富通交易查询入参
 *
 * @Author chenck
 * @Date 2018/3/9 11:10
 */
public class SwiftpassTradeQueryInput extends SwiftpassPayApiInput<SwiftpassTradeQueryResult> {

    public SwiftpassTradeQueryInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.UNIFIED_TRADE_QUERY);
    }

    private String out_trade_no;// 商户订单号 商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 平台订单号 平台交易号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先。

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
