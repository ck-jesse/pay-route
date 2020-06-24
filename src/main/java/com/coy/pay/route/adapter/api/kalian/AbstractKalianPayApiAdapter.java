package com.coy.pay.route.adapter.api.kalian;

import com.coy.pay.route.adapter.api.PayApiAdapter;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.dto.base.PayApiResult;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.sdk.kalian.constant.KalianPayApiConsts;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 【卡联】抽象适配器
 *
 * @author yehl
 * @date 2018/8/24 19:06
 */
public abstract class AbstractKalianPayApiAdapter<T extends PayApiInput<R>, R extends PayApiResult> implements PayApiAdapter<T, R> {

    /**
     * 公众参数设置
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/4/11 20:31
     */
    public void buildCommonParam(PayApiInput input, KalianPayApiInput apiInput) {
        if (StringUtils.isNotBlank(input.getUrl())) {
            apiInput.setUrl(input.getUrl());
        } else {
            apiInput.setUrl(ConfigUtils.get(KalianPayApiConsts.KALIAN_UNION_WEIXIN_PAY_API, KalianPayApiConsts.KALIAN_UNION_DEFAULT_WEIXIN_PAY_API)); // 暂时只支持微信相关支付
        }
        apiInput.setCharset("UTF-8");
        apiInput.setVersion("1.0");
        apiInput.setDate_time(DateUtils.getCurrentDateYYYYMMDDHHMMSS());
        apiInput.setDev_id(input.getDeviceInfo());
        apiInput.setMcht_id(input.getMchId());
        apiInput.setSecrtkey(input.getSecretKey());
        apiInput.setAgent_no(input.getAgentno());
        apiInput.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * 基础参数校验
     *
     * @param
     * @return
     * @author yehl
     * @date 2018/8/24 12:04
     */
    public void check(KalianPayApiInput input) {
        if (StringUtils.isBlank(input.getMcht_id())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入支付商户号且支付商户号不能为空");
        }
        if (StringUtils.isBlank(input.getSecrtkey())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入签名KEY且签名KEY不能为空");
        }
        if (StringUtils.isBlank(input.getOut_order_no())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入商户订单号且商户订单号不能为空");
        }
    }
}
