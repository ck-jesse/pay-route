package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.dto.AlipayNativeInput;
import com.coy.pay.route.adapter.dto.AlipayNativeResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;

/**
 * 【支付宝】扫码支付
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.PAY_ALIPAY_NATIVE)
public class SwiftpassAlipayNativeStrategy extends AbstractSwiftpassPayApiStrategy<AlipayNativeInput, AlipayNativeResult> {

    @Override
    public AlipayNativeResult call(AlipayNativeInput input) {
        throw new PayApiException(PayApiError.ERR_CONFIG, "暂不支持该api功能");
    }

}
