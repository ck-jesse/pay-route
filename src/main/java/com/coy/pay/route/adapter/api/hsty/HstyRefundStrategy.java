package com.coy.pay.route.adapter.api.hsty;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundInput;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundResult;
import com.coy.pay.route.sdk.hsty.support.HstyPayApiSupport;
import com.coy.pay.route.adapter.dto.RefundInput;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 【汇商通盈】退款
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.HSTYPAY, apiId = CustomApiIdEnum.TRADE_REFUND)
public class HstyRefundStrategy extends AbstractHstyPayApiStrategy<RefundInput, RefundResult> {

    @Override
    public RefundResult call(RefundInput input) {
        HstyRefundInput apiInput = new HstyRefundInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setOut_refund_no(input.getRefundListid());
        apiInput.setRefund_fee(input.getRefundFee());// 退款总金额,单位为分,可以做部分退款
        if (StringUtils.isNotBlank(input.getOpUserId())) {
            apiInput.setOp_user_id(input.getOpUserId());
        } else {
            apiInput.setOp_user_id(input.getMchId());// 操作员帐号,默认为商户号
        }
        apiInput.setDevice_info(input.getDeviceInfo());
        if (StringUtils.isNotBlank(input.getRefundChannel())) {
            apiInput.setRefund_channel(input.getRefundChannel());
        } else {
            apiInput.setRefund_channel("1");// 1-原路退款，默认 2-余额
        }
        HstyRefundResult apiResult = HstyPayApiSupport.call(apiInput);

        // 将第三方支付api响应结果进行转换
        RefundResult result = new RefundResult();
        result.setTransactionId(apiResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        result.setListid(apiResult.getOut_trade_no());// 商户订单号,商户系统内部的订单号
        result.setRefundListid(apiResult.getOut_refund_no());// 商户退款单号
        result.setRefundId(apiResult.getRefund_id());// 平台退款单号，如威富通平台退款订单号
        result.setRefundChannel(apiResult.getRefund_channel());
        result.setRefundFee(apiResult.getRefund_fee());

        // TODO 商户发起退款后，调用审核接口完成退款的审核，经过审核后退款请求才会发送给第三方支付。
        return result;
    }

}
