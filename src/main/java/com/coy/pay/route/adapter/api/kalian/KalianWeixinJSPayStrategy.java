package com.coy.pay.route.adapter.api.kalian;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.WeixinJSPayInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.kalian.dto.KalianWeixinJSPayResult;
import com.coy.pay.route.adapter.dto.WeixinJSPayResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.kalian.dto.KalianWeixinJSPayInput;
import com.coy.pay.route.sdk.kalian.support.KalianPayApiSupport;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * 【微信】公众号&小程序支付 预支付
 *
 * @author yehl
 * @date 2018/8/24 18:54
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.KALIAN, apiId = CustomApiIdEnum.PAY_WEIXIN_JSPAY)
public class KalianWeixinJSPayStrategy extends AbstractKalianPayApiStrategy<WeixinJSPayInput, WeixinJSPayResult> {

    @Override
    public WeixinJSPayResult call(WeixinJSPayInput input) {
        // 暂时过滤小程序
        if (StringUtils.isNotBlank(input.getIs_minipg()) && "1".equals(input.getIs_minipg())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "暂不支持小程序支付");// 小程序支付
        }
        WeixinJSPayResult payResult = new WeixinJSPayResult();
        KalianWeixinJSPayInput payInput = new KalianWeixinJSPayInput();
        /**  公共参数 */
        this.buildCommonParam(input, payInput);

        payInput.setOut_order_no(input.getListid());
        payInput.setTotal_fee(input.getTotalFee().toString());
        payInput.setFee_type(input.getFeeType());
        payInput.setBody(input.getBody());
        payInput.setAttach(input.getAttach());
        payInput.setMch_create_ip(input.getMchCreateIp());
        payInput.setNotify_url(input.getNotifyUrl());
        payInput.setCallback_url(input.getCallbackUrl());
        if (input.getTimeStart() != null) {
            payInput.setTime_start(DateUtils.dateToStr(input.getTimeStart(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (input.getTimeExpire() != null) {
            payInput.setTime_expire(DateUtils.dateToStr(input.getTimeExpire(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (StringUtils.isNotBlank(payInput.getAgent_no())) {
            payInput.setOpen_id(input.getSubOpenid());
        } else {
            payInput.setSub_appid(input.getSubAppid());
            payInput.setSub_openid(input.getSubOpenid());
        }

        this.checkInput(payInput);
        KalianWeixinJSPayResult result = KalianPayApiSupport.call(payInput);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result.getPay_info().setCallback_url(input.getCallbackUrl());
            payResult.setPayInfo(objectMapper.writeValueAsString(result.getPay_info()));
        } catch (JsonProcessingException e) {
            throw new PayApiException(PayApiError.ERR_APP, e.getMessage());
        }
        return payResult;
    }

    /**
     * 支付前检查
     *
     * @param
     * @return
     * @author yehl
     * @date 2018/8/15 16:52
     */
    private void checkInput(KalianWeixinJSPayInput input) {
        this.check(input);
        if (input.getTotal_fee() == null) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入总金额且总金额不能为空");
        }
        if (StringUtils.isBlank(input.getBody())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入商品描述且商品描述不能为空");
        }
        if (StringUtils.isBlank(input.getMch_create_ip())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入终端IP且终端IP不能为空");
        }
        if (StringUtils.isBlank(input.getOpen_id()) && StringUtils.isBlank(input.getSub_openid())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "用户标识【openid】和子商户公众号用户的用户标识【sub_openid】必传一个");
        }
        if (StringUtils.isBlank(input.getOpen_id())) {
            if (StringUtils.isBlank(input.getSub_appid())) {
                throw new PayApiException(PayApiError.ERR_PARAM, "子商户公众账号ID【sub_appid】和子商户公众号用户的用户标识【sub_openid】必须同时存在");
            }
        }
    }
}
