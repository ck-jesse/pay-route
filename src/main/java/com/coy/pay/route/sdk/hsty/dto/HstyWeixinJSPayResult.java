package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiResult;

/**
 * 【微信公众号支付接口】出参
 *
 * @author chenck
 * @date 2018/8/23 22:25
 */
public class HstyWeixinJSPayResult extends HstyPayApiResult {

    private String pay_info;// 原生态js支付信息或小程序支付信息 原生态js支付：is_raw为1时返回，json格式的字符串，作用于原生态js支付时的参数 小程序支付：is_minipg为1时返回，json格式的字符串，作用于小程序支付时的参数
    private String pay_url;// 支付链接 直接访问链接

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }
}
