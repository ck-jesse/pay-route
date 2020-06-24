package com.coy.pay.route.sdk.kalian.dto;

import com.coy.pay.route.sdk.kalian.KalianPayApiResult;

/**
 * 【微信扫码支付接口】出参
 * @author yehl
 * @date 2018/8/24 18:56
 */
public class KalianWeixinNativeResult extends KalianPayApiResult {

    private String code_url;// 二维码链接 商户可用此参数自定义去生成二维码后展示出来进行扫码支付
    private String prepay_id;// 预支付交易会话标识

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}
