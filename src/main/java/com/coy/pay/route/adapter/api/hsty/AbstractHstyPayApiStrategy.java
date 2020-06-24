package com.coy.pay.route.adapter.api.hsty;

import com.coy.pay.route.adapter.api.PayApiStrategy;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.sdk.hsty.HstyPayApiInput;
import com.coy.pay.route.adapter.dto.base.PayApiResult;
import com.coy.pay.route.sdk.hsty.constant.HstyPayApiConsts;
import com.coy.pay.route.util.ConfigUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 【汇商通盈】抽象策略
 *
 * @author chenck
 * @date 2018/8/23 13:53
 */
public abstract class AbstractHstyPayApiStrategy<T extends PayApiInput<R>, R extends PayApiResult> implements PayApiStrategy<T, R> {

    /**
     * 公众参数设置
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/4/11 20:31
     */
    public void buildCommonParam(PayApiInput input, HstyPayApiInput apiInput) {
        if (StringUtils.isNotBlank(input.getUrl())) {
            apiInput.setUrl(input.getUrl());
        } else {
            apiInput.setUrl(ConfigUtils.get(HstyPayApiConsts.PAY_HSTYPAY_URL, HstyPayApiConsts.PAY_HSTYPAY_URL_DEFAULT));
        }
        apiInput.setSign_agentno(input.getAgentno());// 签名账号
        apiInput.setStore_merchant_id(input.getMchId());// 门店号，作为收款账号
        apiInput.setSecrtkey(input.getSecretKey());
        apiInput.setNonce_str(String.valueOf(new Date().getTime()));
        apiInput.setSign_type(input.getSignType());
        // 此处固定为MD5
        apiInput.setSign_type("MD5");
    }
}
