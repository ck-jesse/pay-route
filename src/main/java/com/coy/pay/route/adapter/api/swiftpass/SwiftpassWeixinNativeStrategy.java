package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.adapter.dto.WeixinNativeInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinNativeInput;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;
import com.coy.pay.route.adapter.dto.WeixinNativeResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinNativeResult;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 【微信】扫码支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.PAY_WEIXIN_NATIVE)
public class SwiftpassWeixinNativeStrategy extends AbstractSwiftpassPayApiStrategy<WeixinNativeInput, WeixinNativeResult> {

    @Override
    public WeixinNativeResult call(WeixinNativeInput input) {
        SwiftpassWeixinNativeInput apiInput = new SwiftpassWeixinNativeInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setBody(input.getBody());
        apiInput.setTotal_fee(input.getTotalFee());
        apiInput.setMch_create_ip(input.getMchCreateIp());
        apiInput.setNotify_url(input.getNotifyUrl());
        apiInput.setOp_user_id(input.getOpUserId());// 操作员帐号,默认为商户号
        apiInput.setOp_shop_id(input.getOpShopId());
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

        SwiftpassWeixinNativeResult apiResult = SwiftpassPayApiSupport.call(apiInput);

        WeixinNativeResult result = new WeixinNativeResult();
        result.setCodeImgUrl(apiResult.getCode_img_url());
        result.setCodeUrl(apiResult.getCode_url());
        return result;
    }

}
