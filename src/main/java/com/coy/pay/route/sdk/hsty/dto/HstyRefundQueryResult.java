package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiResult;

/**
 * 查询退款API
 * 注：该接口我方仅针对单个退款订单号进行查询，即支持一次查询一个退款订单，不支持一次查询多个退款订单的情况
 *
 * @Author chenck
 * @Date 2018/3/21 12:14
 */
public class HstyRefundQueryResult extends HstyPayApiResult {

    private String out_trade_no;// 商户订单号,商户系统内部的订单号
    private String transaction_id;// 汇商订单号
    private String out_refund_no;// 商户退款单号，商户退款单号
    private String refund_id;// 汇商退款单号
    private String refund_channel;// 1—原路退款，默认 2—退回到余额
    private Long refund_fee;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private String audit_status;// 审核结果 2-审核通过。 3-表示不通过
    private String audit_reason;// 审核说明 审核备注，说明审核通过或不通过的原因
    private String refund_time;// 退款时间 格式为 yyyyMMddhhmmss，如 2009 年 12 月 27日 9 点 10 分 10 秒表示为 20091227091010。时区为 GMT+8 beijing。该时间取自汇商服务器
    private String refund_status;// 退款状态 1:初始化;2:退款成功;3:退款失败;4:未确定;5:转入代发

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

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Long getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }

    public String getAudit_reason() {
        return audit_reason;
    }

    public void setAudit_reason(String audit_reason) {
        this.audit_reason = audit_reason;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }
}
