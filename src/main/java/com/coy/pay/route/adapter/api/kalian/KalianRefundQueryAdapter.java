package com.coy.pay.route.adapter.api.kalian;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundQueryInput;
import com.coy.pay.route.adapter.dto.RefundQueryResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.kalian.dto.KalianRefundInf;
import com.coy.pay.route.sdk.kalian.dto.KalianRefundQueryInput;
import com.coy.pay.route.sdk.kalian.dto.KalianRefundQueryResult;
import com.coy.pay.route.sdk.kalian.support.KalianPayApiSupport;

/**
 * 【适配器】退款查询
 *
 * @author yehl
 * @date 2018/8/24 19:06
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.KALIAN, apiId = CustomApiIdEnum.TRADE_REFUND_QUERY)
public class KalianRefundQueryAdapter extends AbstractKalianPayApiAdapter<RefundQueryInput, RefundQueryResult> {

    @Override
    public RefundQueryResult call(RefundQueryInput input) {
        KalianRefundQueryInput queryInput = new KalianRefundQueryInput();
        this.buildCommonParam(input, queryInput);
        queryInput.setTransaction_id(input.getTransactionId());
        queryInput.setOut_refund_no(input.getRefundListid());
        queryInput.setOut_order_no(input.getListid());
        this.check(queryInput);
        KalianRefundQueryResult queryResult = KalianPayApiSupport.call(queryInput);

        RefundQueryResult result = new RefundQueryResult();
        result.setTransactionId(queryResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        // result.setOutTransactionId(queryResult.getOut_transaction_id());// 第三方订单号，如微信订单号
        result.setListid(queryResult.getOut_order_no());// 商户订单号,商户系统内部的订单号
        if (queryResult.getRefund_inf() != null && queryResult.getRefund_inf().size() > 0) {
            KalianRefundInf refundInf = queryResult.getRefund_inf().get(0);
            result.setRefundListid(refundInf.getOut_refund_no());// 商户退款单号
            // result.setOutRefundId(queryResult.getRefund_no());// 第三方退款订单号，如微信退款订单号
            result.setRefundId(refundInf.getRefund_no());// 平台退款单号，如威富通平台退款订单号
            result.setRefundChannel(refundInf.getRefund_channel());// ORIGINAL-原路退款，默认
            result.setRefundFee(Long.parseLong(refundInf.getRefund_fee()));// 退款金额, 单位为分
            result.setRefundState(refundInf.getRefund_status());// 退款状态：
            result.setRefundTime(queryResult.getDate_time());// 退款时间 yyyyMMddHHmmss
        }
        return result;
    }

}
