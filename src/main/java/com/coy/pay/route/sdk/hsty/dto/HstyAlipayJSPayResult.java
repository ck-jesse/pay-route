package com.coy.pay.route.sdk.hsty.dto;

import com.coy.pay.route.sdk.hsty.HstyPayApiResult;

/**
 * 【支付宝 Wap(H5)支付接口】出参
 *
 * @author chenck
 * @date 2018/8/23 22:25
 */
public class HstyAlipayJSPayResult extends HstyPayApiResult {

    private String pay_info;// JSON 字符串，自行唤起支付宝钱包支付
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
