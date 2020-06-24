package com.coy.pay.route.sdk.swiftpass.dto;

import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;

/**
 * 【微信】【公众号&小程序支付：初始化请求API】出参
 *
 * @author chenck
 * @date 2018/8/21 19:26
 */
public class SwiftpassWeixinJSPayResult extends SwiftpassPayApiResult {

    private String appid;// 公众账号ID 服务商公众号APPID

    private String token_id;// 动态口令 授权口令
    private String pay_info;// 原生态js支付信息或小程序支付信息 原生态js支付：is_raw为1时返回，json格式的字符串，作用于原生态js支付时的参数 小程序支付：is_minipg为1时返回，json格式的字符串，作用于小程序支付时的参数
    private String pay_url;// 支付链接 直接访问链接

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

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
