package com.coy.pay.route.adapter.api.kalian;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.kalian.support.KalianPayApiSupport;
import com.coy.pay.route.adapter.dto.RefundInput;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.kalian.dto.KalianRefundInput;
import com.coy.pay.route.sdk.kalian.dto.KalianRefundResult;
import com.coy.pay.route.exception.PayApiError;
import org.apache.commons.lang3.StringUtils;

/**
 * 【适配器】退款
 *
 * @author yehl
 * @date 2018/8/24 19:06
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.KALIAN, apiId = CustomApiIdEnum.TRADE_REFUND)
public class KalianRefundAdapter extends AbstractKalianPayApiAdapter<RefundInput, RefundResult> {

    @Override
    public RefundResult call(RefundInput input) {
        KalianRefundInput refundInput = new KalianRefundInput();
        this.buildCommonParam(input, refundInput);
        refundInput.setOut_order_no(input.getListid());
        refundInput.setTransaction_id(null);
        refundInput.setOut_refund_no(input.getRefundListid());
        refundInput.setOrg_fee(input.getTotalFee().toString());
        refundInput.setRefund_fee(input.getRefundFee().toString());
        // refundInput.setRefund_fee_type("CNY");
        refundInput.setOp_user_id(input.getOpUserId());
        // refundInput.setRefund_account(null);
        this.checkRefund(refundInput);
        KalianRefundResult refundResult = KalianPayApiSupport.call(refundInput);

        // 将第三方支付api响应结果进行转换
        RefundResult result = new RefundResult();
        // result.setTransactionId(refundResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        // result.setOutTransactionId(refundResult.getOut_transaction_id());// 第三方订单号，如微信订单号
        result.setListid(refundResult.getOut_order_no());// 商户订单号,商户系统内部的订单号
        result.setRefundListid(refundResult.getOut_refund_no());// 商户退款单号
        result.setRefundId(refundResult.getRefund_no());// 平台退款单号，如威富通平台退款订单号
        result.setRefundChannel(refundResult.getRefund_channel());
        result.setRefundFee(StringUtils.isBlank(refundResult.getRefund_fee()) ? 0 : Long.parseLong(refundResult.getRefund_fee()));
        return result;
    }

    private void checkRefund(KalianRefundInput input) {
        this.check(input);
        if (StringUtils.isBlank(input.getOut_refund_no())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入退款单号且退款单号不能为空");
        }
        if (input.getOrg_fee() == null) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入总金额且总金额不能为空");
        }
        if (StringUtils.isBlank(input.getRefund_fee()) || Long.parseLong(input.getRefund_fee()) == 0) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入退款金额且退款金额必须大于0");
        }
        if (StringUtils.isBlank(input.getOp_user_id())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入操作员且操作员不能为空");
        }
    }
}
