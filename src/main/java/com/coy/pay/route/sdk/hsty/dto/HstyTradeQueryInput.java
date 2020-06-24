package com.coy.pay.route.sdk.hsty.dto;


import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;

/**
 * 交易查询入参
 *
 * @author chenck
 * @date 2018/4/12 17:58
 */
public class HstyTradeQueryInput extends HstyPayApiInput<HstyTradeQueryResult> {

    public HstyTradeQueryInput() {
        super();
        // 初始化服务名称
        this.setService(HstyPayApiConsts.UNIFIED_TRADE_QUERY);
    }

    private String op_user_id;// 操作员, 操作员帐号
    private String device_info;// 设备信息 终端设备信息
    private String out_trade_no;// 商户订单号 商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
    private String transaction_id;// 汇商订单号,汇 商 交 易 号 , out_trade_no 和transaction_id 至少一个必填，同时存在时transaction_id 优先。

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
