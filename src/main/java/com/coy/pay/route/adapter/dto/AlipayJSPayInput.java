package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;

import java.util.Date;

/**
 * 【支付宝】服务窗支付 预支付入参
 *
 * @author chenck
 * @date 2018/8/23 20:49
 */
public class AlipayJSPayInput extends PayApiInput<AlipayJSPayResult> {

    public AlipayJSPayInput() {
        super();
        // 设置api标识
        this.setApiId(CustomApiIdEnum.PAY_ALIPAY_JSPAY.getValue());
    }

    private int clientType = PayConstants.APP;//客户端类型
    private String listid; // 订单号
    private Long totalFee;// 总金额, 总金额，以分为单位，不允许包含任何字、符号
    private String feeType; // 货币类型（CNY）
    private String body; // 商品描述

    private String attach; // 自定义数据，在查询API中，会原样返回
    private String authCode; // 授权码

    private String notifyUrl;//支付成功回调接口URL
    private String callbackUrl;// 前台跳转地址

    private String openid; // 用户微信的openid
    private String subAppid;// 商户微信公众号的openid
    private String subOpenid;// 商户微信公众号分配给用户的openid

    private String buyerLogonId; // 买家支付宝账号
    private String buyerId; // 买家支付宝用户ID

    private String limitPay; // 指定支付方式（no_credit - -  不能使用信用卡支付）

    private String mchCreateIp;// 终端ip
    private Date timeStart;// 订单生成时间
    private Date timeExpire;// 订单过期时间

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getMchCreateIp() {
        return mchCreateIp;
    }

    public void setMchCreateIp(String mchCreateIp) {
        this.mchCreateIp = mchCreateIp;
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
}
