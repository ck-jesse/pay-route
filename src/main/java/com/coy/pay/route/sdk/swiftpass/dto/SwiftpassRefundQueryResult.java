package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;

/**
 * 查询退款API
 * 注：该接口我方仅针对单个退款订单号进行查询，即支持一次查询一个退款订单，不支持一次查询多个退款订单的情况
 *
 * @Author chenck
 * @Date 2018/3/21 12:14
 */
public class SwiftpassRefundQueryResult extends SwiftpassPayApiResult {

    private String appid;//
    private String transaction_id;// 平台订单号
    private String out_transaction_id;// 第三方订单号，如微信订单号
    private String out_trade_no;// 商户订单号,商户系统内部的订单号
    private int refund_count;// 退款记录数
    private String trade_type;//
    private Long total_fee;//

    //  TODO 此处返回的数据结构真尼玛让人蛋疼，想问问那个开发的猿类，侬脑子歪他了吧
    // $n 表示记录的序号，取值为 0~($ refund_count -1)，例如 refund_count 指示返回的退款记录有 2 条。第一条序号为“0”，第二条序号为“1”。
    private String out_refund_no_0;// 商户退款单号，商户退款单号
    private String out_refund_id_0;// 第三方退款订单号，如微信退款订单号
    private String refund_id_0;// 平台退款单号，平台退款单号
    private String refund_channel_0;// 退款渠道 ORIGINAL-原路退款，默认
    private Long refund_fee_0;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private Long coupon_refund_fee_0;// 现金券退款金额, 现金券退款金额 <= 退款金额， 退款金额-现金券退款金额为现金
    private String refund_time_0;// 退款时间 yyyyMMddHHmmss
    private String refund_status_0;// 退款状态： SUCCESS—退款成功 FAIL—退款失败 PROCESSING—退款处理中 NOTSURE—未确定， 需要商户原退款单号重新发起 CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者平台转账的方式进行退款。

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_transaction_id() {
        return out_transaction_id;
    }

    public void setOut_transaction_id(String out_transaction_id) {
        this.out_transaction_id = out_transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(int refund_count) {
        this.refund_count = refund_count;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public String getOut_refund_no_0() {
        return out_refund_no_0;
    }

    public void setOut_refund_no_0(String out_refund_no_0) {
        this.out_refund_no_0 = out_refund_no_0;
    }

    public String getOut_refund_id_0() {
        return out_refund_id_0;
    }

    public void setOut_refund_id_0(String out_refund_id_0) {
        this.out_refund_id_0 = out_refund_id_0;
    }

    public String getRefund_id_0() {
        return refund_id_0;
    }

    public void setRefund_id_0(String refund_id_0) {
        this.refund_id_0 = refund_id_0;
    }

    public String getRefund_channel_0() {
        return refund_channel_0;
    }

    public void setRefund_channel_0(String refund_channel_0) {
        this.refund_channel_0 = refund_channel_0;
    }

    public Long getRefund_fee_0() {
        return refund_fee_0;
    }

    public void setRefund_fee_0(Long refund_fee_0) {
        this.refund_fee_0 = refund_fee_0;
    }

    public Long getCoupon_refund_fee_0() {
        return coupon_refund_fee_0;
    }

    public void setCoupon_refund_fee_0(Long coupon_refund_fee_0) {
        this.coupon_refund_fee_0 = coupon_refund_fee_0;
    }

    public String getRefund_time_0() {
        return refund_time_0;
    }

    public void setRefund_time_0(String refund_time_0) {
        this.refund_time_0 = refund_time_0;
    }

    public String getRefund_status_0() {
        return refund_status_0;
    }

    public void setRefund_status_0(String refund_status_0) {
        this.refund_status_0 = refund_status_0;
    }
}
