package com.coy.pay.route.sdk.swiftpass.dto;


import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;

/**
 * 查询退款API
 *
 * @Author chenck
 * @Date 2018/3/21 12:13
 */
public class SwiftpassRefundQueryInput extends SwiftpassPayApiInput<SwiftpassRefundQueryResult> {

    public SwiftpassRefundQueryInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.UNIFIED_TRADE_REFUNDQUERY);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 平台订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String out_refund_no;// 商户退款单号，32个字符内、可包含字母,确保在商户系统唯一。
    private String refund_id;// 平台退款单号refund_id、out_refund_no、out_trade_no 、transaction_id 四个参数必填一个， 如果同时存在优先级为：refund_id>out_refund_no>transaction_id>out_trade_no

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
}
