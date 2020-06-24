package com.coy.pay.route.adapter.api.kalian;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.WeixinNativeInput;
import com.coy.pay.route.adapter.dto.WeixinNativeResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.kalian.support.KalianPayApiSupport;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.kalian.dto.KalianWeixinNativeInput;
import com.coy.pay.route.sdk.kalian.dto.KalianWeixinNativeResult;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 【适配器】【微信】扫码支付
 *
 * @author yehl
 * @date 2018/8/24 18:54
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.KALIAN, apiId = CustomApiIdEnum.PAY_WEIXIN_NATIVE)
public class KalianWeixinNativeAdapter extends AbstractKalianPayApiAdapter<WeixinNativeInput, WeixinNativeResult> {

    @Override
    public WeixinNativeResult call(WeixinNativeInput input) {
        WeixinNativeResult nativeResult = new WeixinNativeResult();
        KalianWeixinNativeInput nativeInput = new KalianWeixinNativeInput();
        /**  公共参数 */
        this.buildCommonParam(input, nativeInput);

        nativeInput.setOut_order_no(input.getListid());
        nativeInput.setTotal_fee(input.getTotalFee().toString());
        nativeInput.setFee_type(input.getFeeType());
        nativeInput.setBody(input.getBody());
        nativeInput.setAttach(input.getAttach());
        nativeInput.setMch_create_ip(input.getMchCreateIp());
        nativeInput.setNotify_url(input.getNotifyUrl());
        if (input.getTimeStart() != null) {
            nativeInput.setTime_start(DateUtils.dateToStr(input.getTimeStart(), DateUtils.YYYYMMDDHHMMSS));
        }
        if (input.getTimeExpire() != null) {
            nativeInput.setTime_expire(DateUtils.dateToStr(input.getTimeExpire(), DateUtils.YYYYMMDDHHMMSS));
        }

        this.checkInput(nativeInput);
        KalianWeixinNativeResult result = KalianPayApiSupport.call(nativeInput);
        nativeResult.setCodeUrl(result.getCode_url());
        return nativeResult;
    }

    /**
     * 支付前检查
     *
     * @param
     * @return
     * @author yehl
     * @date 2018/8/15 16:52
     */
    private void checkInput(KalianWeixinNativeInput input) {
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
    }
}
