package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.WeixinJSPayInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinJSPayInput;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.WeixinJSPayResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinJSPayResult;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 【微信】公众号&小程序支付 预支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.PAY_WEIXIN_JSPAY)
public class SwiftpassWeixinJSPayStrategy extends AbstractSwiftpassPayApiStrategy<WeixinJSPayInput, WeixinJSPayResult> {

    @Override
    public WeixinJSPayResult call(WeixinJSPayInput input) {
        SwiftpassWeixinJSPayInput apiInput = new SwiftpassWeixinJSPayInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setIs_raw("1");
        if (StringUtils.isNotBlank(input.getIs_minipg()) && "1".equals(input.getIs_minipg())) {
            apiInput.setIs_minipg("1");// 小程序支付
        }
        if (!"7551000001".equals(input.getMchId())) {
            apiInput.setSub_openid(input.getSubOpenid());
            apiInput.setSub_appid(input.getSubAppid());
        }

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setBody(input.getBody());
        apiInput.setAttach(input.getAttach());
        apiInput.setTotal_fee(input.getTotalFee());
        apiInput.setMch_create_ip(input.getMchCreateIp());
        apiInput.setNotify_url(input.getNotifyUrl());
        apiInput.setCallback_url(input.getCallbackUrl());
        apiInput.setOp_user_id(input.getOpUserId());// 操作员帐号,默认为商户号
        apiInput.setOp_shop_id(input.getOpShopId());
        apiInput.setLimit_credit_pay(input.getLimit_credit_pay());
        if (null != input.getTimeStart()) {
            apiInput.setTime_start(DateUtils.dateToStr(input.getTimeStart(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (null != input.getTimeExpire()) {
            apiInput.setTime_expire(DateUtils.dateToStr(input.getTimeExpire(), DateUtils.YYYYMMDDHHMMSS));
        }

        if (StringUtils.isNotBlank(input.getDeviceInfo())) {
            apiInput.setDevice_info(input.getDeviceInfo());
        } else {
            apiInput.setDevice_info(ConfigUtils.get(PayConstants.PAY_WFT_DEVICE_INFO, "MEMEPPC_WX"));
        }

        SwiftpassWeixinJSPayResult apiResult = SwiftpassPayApiSupport.call(apiInput);

        WeixinJSPayResult result = new WeixinJSPayResult();
        result.setPayInfo(apiResult.getPay_info());
        result.setPayUrl(apiResult.getPay_url());
        result.setTokenId(apiResult.getToken_id());
        return result;
    }

}
