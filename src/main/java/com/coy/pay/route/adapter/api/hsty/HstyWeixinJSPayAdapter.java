package com.coy.pay.route.adapter.api.hsty;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.WeixinJSPayInput;
import com.coy.pay.route.adapter.dto.WeixinJSPayinfo;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.hsty.dto.HstyWeixinJSPayInput;
import com.coy.pay.route.sdk.hsty.dto.HstyWeixinJSPayResult;
import com.coy.pay.route.sdk.hsty.support.HstyPayApiSupport;
import com.coy.pay.route.adapter.dto.WeixinJSPayResult;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 【适配器】【汇商通盈】【微信】公众号&小程序支付 预支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.HSTYPAY, apiId = CustomApiIdEnum.PAY_WEIXIN_JSPAY)
public class HstyWeixinJSPayAdapter extends AbstractHstyPayApiAdapter<WeixinJSPayInput, WeixinJSPayResult> {

    @Override
    public WeixinJSPayResult call(WeixinJSPayInput input) {
        // 暂时过滤小程序
        if (StringUtils.isNotBlank(input.getIs_minipg()) && "1".equals(input.getIs_minipg())) {
            // throw new PayApiException(PayApiError.ERR_PARAM, "暂不支持小程序支付");// 小程序支付
        }

        HstyWeixinJSPayInput apiInput = new HstyWeixinJSPayInput();
        this.buildCommonParam(input, apiInput);
        apiInput.setOut_trade_no(input.getListid());
        apiInput.setBody(input.getBody());
        apiInput.setAttach(input.getAttach());
        apiInput.setTotal_fee(input.getTotalFee());
        apiInput.setMch_create_ip(input.getMchCreateIp());
        apiInput.setSub_appid(input.getSubAppid());
        apiInput.setSub_openid(input.getSubOpenid());
        apiInput.setIs_raw("1");
        apiInput.setNotify_url(input.getNotifyUrl());
        apiInput.setCallback_url(input.getCallbackUrl());
        apiInput.setOp_user_id(input.getOpUserId());// 操作员帐号,默认为商户号
        apiInput.setLimit_credit_pay(input.getLimit_credit_pay());

        if (StringUtils.isNotBlank(input.getIs_minipg()) && "1".equals(input.getIs_minipg())) {
            apiInput.setIs_minipg("1");// 小程序支付
        }
        if (null != input.getTimeExpire()) {
            apiInput.setExpire_time(DateUtils.dateToStr(input.getTimeExpire(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (StringUtils.isNotBlank(input.getDeviceInfo())) {
            apiInput.setDevice_info(input.getDeviceInfo());
        } else {
            apiInput.setDevice_info(ConfigUtils.get(PayConstants.PAY_WFT_DEVICE_INFO, "MEMEPPC_WX"));
        }
        HstyWeixinJSPayResult apiResult = HstyPayApiSupport.call(apiInput);

        WeixinJSPayResult result = new WeixinJSPayResult();
        result.setPayUrl(apiResult.getPay_url());
        result.setPayInfo(apiResult.getPay_info());
        try {
            if (StringUtils.isNotBlank(apiResult.getPay_info())) {
                ObjectMapper objectMapper = new ObjectMapper();
                WeixinJSPayinfo jsPayinfo = objectMapper.readValue(apiResult.getPay_info(), WeixinJSPayinfo.class);
                // 汇商支付内部采用的支付通道不同，callback_url可能有值，也可能没值，所以此处当callback_url为空时，对其进行赋值
                if (StringUtils.isBlank(jsPayinfo.getCallback_url())) {
                    jsPayinfo.setCallback_url(input.getCallbackUrl());
                    result.setPayInfo(objectMapper.writeValueAsString(jsPayinfo));
                }
            }
        } catch (IOException e) {
            throw new PayApiException(PayApiError.ERR_APP, e.getMessage());
        }
        return result;
    }

}
