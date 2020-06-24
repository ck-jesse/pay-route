package com.coy.pay.route.sdk.kalian.dto;

import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianTxnNumConsts;

/**
 * 【微信扫码支付接口】入参
 * @author yehl
 * @date 2018/8/24 18:56
 */
public class KalianWeixinNativeInput extends KalianPayApiInput<KalianWeixinNativeResult> {

    public KalianWeixinNativeInput() {
        super();
        this.setTxn_num(KalianTxnNumConsts.WeiUnionPay.CODE_PAY_TXNNUM);
    }

    private String total_fee      ; // 总金额
    private String fee_type     ; // 货币类型
    private String limit_pay    ; // 指定支付方式
    private String body         ; // 商品描述
    private String notify_url   ; // 通知地址
    private String attach       ; // 附加数据
    private String mch_create_ip; // 终端IP
    private String time_start   ; // 交易起始日期
    private String time_expire  ; // 交易结束时间

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMch_create_ip() {
        return mch_create_ip;
    }

    public void setMch_create_ip(String mch_create_ip) {
        this.mch_create_ip = mch_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }
}
