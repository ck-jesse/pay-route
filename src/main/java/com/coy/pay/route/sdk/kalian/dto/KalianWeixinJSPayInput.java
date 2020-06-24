package com.coy.pay.route.sdk.kalian.dto;


import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianTxnNumConsts;

/**
 * 预下单支付输入参数
 * @author yehl
 * @date 2018/8/15 13:44
 */
public class KalianWeixinJSPayInput extends KalianPayApiInput<KalianWeixinJSPayResult> {

    public KalianWeixinJSPayInput() {
        super();
        this.setTxn_num(KalianTxnNumConsts.WeiUnionPay.PUBLIC_PAY_TXNNUM);
    }

    /**  必填  */
    private String total_fee; // 总金额（以分为单位）
    private String fee_type; // 货币类型（目前仅支持CNY）
    /**  必填  */
    private String body; // 商品描述
    private String attach; // 自定义数据，在查询API中，会原样返回
    /**  必填  */
    private String mch_create_ip; // 订单生成的机器IP
    private String auth_code; // 授权码
    private String limit_pay; // 指定支付方式（no_credit—不能使用信用卡支付）
    private String notify_url; // 接收平台通知的URL，需给绝对路径，255字符内格式
    private String callback_url; // 支付回调地址
    private String time_start; // 交易起始日期【格式：yyyyMMddHHmmss】
    private String time_expire; // 交易结束时间【格式：yyyyMMddHHmmss】
    private String open_id; // 用户标识
    private String sub_appid; // 子商户公众账号ID（微信分配的子商户公众账号ID，如需在支付完成后获取sub_openid则此参数必传）
    private String sub_openid; // 子商户公众号用户的用户标识（用户在子商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid）

    private String buyer_logon_id; // 支付宝支付时，买家支付宝账号，和buger_id不能同时为空
    private String buyer_id; // 支付宝支付时，买家支付宝用户ID，和buyer_logon_id不能同时为空

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
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

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_openid() {
        return sub_openid;
    }

    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }
}
