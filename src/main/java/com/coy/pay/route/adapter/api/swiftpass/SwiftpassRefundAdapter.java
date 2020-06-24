package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundInput;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;
import com.coy.pay.route.util.security.MD5;
import com.coy.pay.route.adapter.dto.RefundInput;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundResult;
import org.apache.commons.lang3.StringUtils;

/**
 * 【适配器】退款
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.TRADE_REFUND)
public class SwiftpassRefundAdapter extends AbstractSwiftpassPayApiAdapter<RefundInput, RefundResult> {

    @Override
    public RefundResult call(RefundInput input) {
        SwiftpassRefundInput apiInput = new SwiftpassRefundInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setOut_refund_no(input.getRefundListid());
        apiInput.setTotal_fee(input.getTotalFee());// 订单总金额，单位为分
        apiInput.setRefund_fee(input.getRefundFee());// 退款总金额,单位为分,可以做部分退款
        if (StringUtils.isNotBlank(input.getOpUserId())) {
            apiInput.setOp_user_id(input.getOpUserId());
        } else {
            apiInput.setOp_user_id(input.getMchId());// 操作员帐号,默认为商户号
        }
        // 对退款来源进行MD5加密
        apiInput.setRefund_source(MD5.encode(SwiftpassPayApiConsts.REFUND_SOURCE_KEY, "UTF-8").toUpperCase());

        SwiftpassRefundResult apiResult = SwiftpassPayApiSupport.call(apiInput);

        // 将第三方支付api响应结果进行转换
        RefundResult result = new RefundResult();
        result.setTransactionId(apiResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        result.setOutTransactionId(apiResult.getOut_transaction_id());// 第三方订单号，如微信订单号
        result.setListid(apiResult.getOut_trade_no());// 商户订单号,商户系统内部的订单号
        result.setRefundListid(apiResult.getOut_refund_no());// 商户退款单号
        result.setRefundId(apiResult.getRefund_id());// 平台退款单号，如威富通平台退款订单号
        result.setRefundChannel(apiResult.getRefund_channel());
        result.setRefundFee(apiResult.getRefund_fee());
        return result;
    }

}
