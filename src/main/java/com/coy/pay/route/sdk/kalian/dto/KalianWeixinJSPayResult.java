package com.coy.pay.route.sdk.kalian.dto;

import com.coy.pay.route.adapter.dto.WeixinJSPayinfo;
import com.coy.pay.route.sdk.kalian.KalianPayApiResult;

/**
 * 预下单支付结果出参
 *
 * @author yehl
 * @date 2018/8/15 13:53
 */
public class KalianWeixinJSPayResult extends KalianPayApiResult {

    private String prepay_id; // 预支付交易会话标识
    private WeixinJSPayinfo pay_info; // 原生态 js 支付信息

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public WeixinJSPayinfo getPay_info() {
        return pay_info;
    }

    public void setPay_info(WeixinJSPayinfo pay_info) {
        this.pay_info = pay_info;
    }
}
