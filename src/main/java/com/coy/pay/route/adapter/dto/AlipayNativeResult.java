package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 【支付宝】扫码支付 出参
 *
 * @author chenck
 * @date 2018/8/24 9:46
 */
public class AlipayNativeResult extends PayApiResult {

    private String codeUrl;// 二维码链接 商户可用此参数自定义去生成二维码后展示出来进行扫码支付
    private String codeImgUrl;// 二维码图片 	此参数的值即是根据code_url生成的可以扫码支付的二维码图片地址

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getCodeImgUrl() {
        return codeImgUrl;
    }

    public void setCodeImgUrl(String codeImgUrl) {
        this.codeImgUrl = codeImgUrl;
    }
}
