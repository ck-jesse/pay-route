package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.api.PayApiAdapter;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;
import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.adapter.dto.base.PayApiResult;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 【威富通】抽象适配器
 *
 * @author chenck
 * @date 2018/8/23 13:53
 */
public abstract class AbstractSwiftpassPayApiAdapter<T extends PayApiInput<R>, R extends PayApiResult> implements PayApiAdapter<T, R> {

    /**
     * 公众参数设置
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/4/11 20:31
     */
    public void buildCommonParam(PayApiInput input, SwiftpassPayApiInput apiInput) {
        if (StringUtils.isNotBlank(input.getUrl())) {
            apiInput.setUrl(input.getUrl());
        } else {
            apiInput.setUrl(ConfigUtils.get(PayConstants.PAY_WFT_URL, PayConstants.PAY_WFT_URL_DEFAULT));
        }
        apiInput.setMch_id(input.getMchId());
        apiInput.setNonce_str(String.valueOf(new Date().getTime()));
        apiInput.setSecrtkey(input.getSecretKey());
        apiInput.setRsaPrivateKey(input.getRsaPrivateKey());
        apiInput.setRsaPublicKey(input.getRsaPublicKey());
        // 由于业务系统中定义的签名类型为数字，此处转换一下（蛋疼的勒）
        // 注：signType定义为String，方便后续改造为传入具体的签名类型
        if ("1".equals(input.getSignType())) {
            apiInput.setSign_type(SwiftpassPayApiConsts.MD5);
        } else if ("2".equals(input.getSignType())) {
            apiInput.setSign_type(SwiftpassPayApiConsts.RSA_1_256);
        }
    }
}
