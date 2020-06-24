package com.coy.pay.route.adapter.api.hsty;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.WeixinNativeInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.dto.WeixinNativeResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.hsty.dto.HstyWeixinNativeInput;
import com.coy.pay.route.sdk.hsty.dto.HstyWeixinNativeResult;
import com.coy.pay.route.sdk.hsty.support.HstyPayApiSupport;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 【汇商通盈】【微信】扫码支付 预支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.HSTYPAY, apiId = CustomApiIdEnum.PAY_WEIXIN_NATIVE)
public class HstyWeixinNativeStrategy extends AbstractHstyPayApiStrategy<WeixinNativeInput, WeixinNativeResult> {

    @Override
    public WeixinNativeResult call(WeixinNativeInput input) {
        HstyWeixinNativeInput apiInput = new HstyWeixinNativeInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setBody(input.getBody());
        apiInput.setAttach(input.getAttach());
        apiInput.setTotal_fee(input.getTotalFee());
        apiInput.setMch_create_ip(input.getMchCreateIp());
        apiInput.setNotify_url(input.getNotifyUrl());
        apiInput.setOp_user_id(input.getOpUserId());// 操作员帐号,默认为商户号

        if (null != input.getTimeExpire()) {
            apiInput.setExpire_time(DateUtils.dateToStr(input.getTimeExpire(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (StringUtils.isNotBlank(input.getDeviceInfo())) {
            apiInput.setDevice_info(input.getDeviceInfo());
        } else {
            apiInput.setDevice_info(ConfigUtils.get(PayConstants.PAY_WFT_DEVICE_INFO, "MEMEPPC_WX"));
        }
        HstyWeixinNativeResult apiResult = HstyPayApiSupport.call(apiInput);

        WeixinNativeResult result = new WeixinNativeResult();
        result.setCodeUrl(apiResult.getCode_url());
        result.setCodeImgUrl(apiResult.getCode_img_url());
        return result;
    }

}
