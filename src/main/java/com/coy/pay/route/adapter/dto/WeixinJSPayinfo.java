package com.coy.pay.route.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yehl
 * @date 2018/8/27 18:50
 */
public class WeixinJSPayinfo {

    @JsonProperty("appId")
    private String appId;
    @JsonProperty("callback_url")
    private String callback_url;
    @JsonProperty("nonceStr")
    private String nonceStr;
    @JsonProperty("package")
    private String packageStr;
    @JsonProperty("paySign")
    private String paySign;
    @JsonProperty("signType")
    private String signType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("timeStamp")
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
