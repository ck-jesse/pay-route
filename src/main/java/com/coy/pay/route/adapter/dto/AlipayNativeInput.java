package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;

/**
 * 【支付宝】扫码支付 入参
 *
 * @author chenck
 * @date 2018/8/24 9:49
 */
public class AlipayNativeInput extends PayApiInput<AlipayNativeResult> {

    public AlipayNativeInput() {
        super();
        // 设置api标识
        this.setApiId(CustomApiIdEnum.PAY_ALIPAY_NATIVE.getValue());
    }

}
