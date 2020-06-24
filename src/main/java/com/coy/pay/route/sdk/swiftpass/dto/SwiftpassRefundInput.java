package com.coy.pay.route.sdk.swiftpass.dto;


import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;

/**
 * 退款输入参数
 *
 * @Author chenck
 * @Date 2018/3/9 10:41
 */
public class SwiftpassRefundInput extends SwiftpassPayApiInput<SwiftpassRefundResult> {

    public SwiftpassRefundInput() {
        super();
        // 初始化服务名称
        this.setService(SwiftpassPayApiConsts.UNIFIED_TRADE_REFUND);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 平台订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String out_refund_no;// 商户退款单号，32个字符内、可包含字母,确保在商户系统唯一。
    private Long total_fee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private Long refund_fee;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private String op_user_id;// 操作员, 操作员帐号,默认为商户号
    private String refund_channel;// ORIGINAL-原路退款，默认

    // 特殊字段，采用固定字符串进行MD5加密
    private String refund_source;// 退款来源，对于电子卡业务产生的支付订单，威富通基于该字段限制只有验证通过的退款请求，才能进行退款（防止其他地方退款后，我方系统不知道的情况）

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

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public Long getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public String getRefund_source() {
        return refund_source;
    }

    public void setRefund_source(String refund_source) {
        this.refund_source = refund_source;
    }
}
