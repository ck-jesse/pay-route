package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.dto.base.PayApiInput;

import java.util.Date;

/**
 * 【微信】公众号&小程序支付 预支付入参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class WeixinJSPayInput extends PayApiInput<WeixinJSPayResult> {

    public WeixinJSPayInput() {
        super();
        // 设置api标识
        this.setApiId(CustomApiIdEnum.PAY_WEIXIN_JSPAY.getValue());
    }

    private String listid; // 订单号
    private String opShopId;// 门店编号
    private String body; // 商品描述
    private String openid; // 用户标识
    private String subOpenid;// 商户微信公众号分配给用户的openid
    private String subAppid;// 商户微信公众号的openid
    private String attach; // 自定义数据，在查询API中，会原样返回
    private Long totalFee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private String feeType; // 货币类型（CNY）

    private String mchCreateIp;// 终端ip
    private String notifyUrl;//支付成功回调接口URL
    private String callbackUrl;// 前台跳转地址
    private Date timeStart;// 订单生成时间
    private Date timeExpire;// 订单过期时间
    private String limit_credit_pay;// 是否限制信用卡 限定用户使用时能否使用信用卡，值为1，禁用信用卡；值为0或者不传此参数则不禁用

    private String is_minipg;// 是否小程序支付	值为1，表示小程序支付；不传或值不为1，表示公众账号内支付

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getOpShopId() {
        return opShopId;
    }

    public void setOpShopId(String opShopId) {
        this.opShopId = opShopId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getMchCreateIp() {
        return mchCreateIp;
    }

    public void setMchCreateIp(String mchCreateIp) {
        this.mchCreateIp = mchCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(Date timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
    }

    public String getIs_minipg() {
        return is_minipg;
    }

    public void setIs_minipg(String is_minipg) {
        this.is_minipg = is_minipg;
    }
}
