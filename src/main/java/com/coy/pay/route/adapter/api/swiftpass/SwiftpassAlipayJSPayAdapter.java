package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.AlipayJSPayResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.dto.AlipayJSPayInput;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;

/**
 * 【适配器】【微信】服务窗支付 预支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.PAY_ALIPAY_JSPAY)
public class SwiftpassAlipayJSPayAdapter extends AbstractSwiftpassPayApiAdapter<AlipayJSPayInput, AlipayJSPayResult> {

    @Override
    public AlipayJSPayResult call(AlipayJSPayInput input) {
        throw new PayApiException(PayApiError.ERR_CONFIG, "暂不支持该api功能");
    }

}
