package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 【支付宝】服务窗支付 预支付出参
 *
 * @author chenck
 * @date 2018/8/23 20:50
 */
public class AlipayJSPayResult extends PayApiResult {

    private String payInfo;// 原生态js支付信息或小程序支付信息 原生态js支付：is_raw为1时返回，json格式的字符串，作用于原生态js支付时的参数 小程序支付：is_minipg为1时返回，json格式的字符串，作用于小程序支付时的参数
    private String payUrl;// 支付链接 直接访问链接

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
