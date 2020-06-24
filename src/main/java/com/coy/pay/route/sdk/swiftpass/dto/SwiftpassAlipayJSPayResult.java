package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;

/**
 * 【支付宝】【服务窗支付：统一下单API】出参
 *
 * @author chenck
 * @date 2018/8/23 22:01
 */
public class SwiftpassAlipayJSPayResult extends SwiftpassPayApiResult {

    private String pay_info;// 原生支付信息 JSON字符串，自行唤起支付宝钱包支付
    private String pay_url;// 支付链接 仅作为参考使用，商户需自己实现该支付页面

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }
}
