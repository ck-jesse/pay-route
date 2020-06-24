package com.coy.pay.route.sdk.hsty.dto;


import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;

/**
 * 查询退款API
 *
 * @author chenck
 * @date 2018/4/12 17:12
 */
public class HstyRefundQueryInput extends HstyPayApiInput<HstyRefundQueryResult> {

    public HstyRefundQueryInput() {
        super();
        // 初始化服务名称
        this.setService(HstyPayApiConsts.UNIFIED_TRADE_REFUNDQUERY);
    }

    private String out_trade_no;// 商户订单号,商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 汇商订单号,汇商单号, out_trade_no 和 transaction_id 至少一个必填，同时存在时 transaction_id 优先
    private String out_refund_no;// 商户退款单号，32个字符内、可包含字母,确保在商户系统唯一。商户退款单号，32 个字符内、可包含字母,确保在商户系统唯一。同个退款单号多次请求，汇商当一个单处理，只会退一次款。如果出现退款不成功，请采用原退款单号重新发起，避免出现重复退款。
    private String op_user_id;// 操作员, 操作员帐号
    private String device_info;// 设备信息

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
}
