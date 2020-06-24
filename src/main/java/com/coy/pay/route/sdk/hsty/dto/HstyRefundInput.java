package com.coy.pay.route.sdk.hsty.dto;


import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;

/**
 * 退款输入参数
 *
 * @author chenck
 * @date 2018/4/12 16:58
 */
public class HstyRefundInput extends HstyPayApiInput<HstyRefundResult> {

    public HstyRefundInput() {
        super();
        // 初始化服务名称
        this.setService(HstyPayApiConsts.UNIFIED_TRADE_REFUND);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 汇商订单号, 汇商单号, out_trade_no 和 transaction_id 至少一个必填，同时存在时 transaction_id 优先
    private String out_refund_no;// 商户退款单号，32个字符内、可包含字母,确保在商户系统唯一。商户退款单号，32 个字符内、可包含字母,确保在商户系统唯一。同个退款单号多次请求，汇商当一个单处理，只会退一次款。如果出现退款不成功，请采用原退款单号重新发起，避免出现重复退款。
    private Long refund_fee;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private String op_user_id;// 操作员, 操作员帐号
    private String device_info;// 设备信息
    private String refund_channel;// 1-原路退款，默认 2-余额


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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }
}
