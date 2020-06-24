package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundQueryInput;
import com.coy.pay.route.adapter.dto.RefundQueryResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundQueryInput;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundQueryResult;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;
import com.coy.pay.route.exception.PayApiError;
import org.apache.commons.lang3.StringUtils;

/**
 * 【适配器】退款查询
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.TRADE_REFUND_QUERY)
public class SwiftpassRefundQueryAdapter extends AbstractSwiftpassPayApiAdapter<RefundQueryInput, RefundQueryResult> {

    @Override
    public RefundQueryResult call(RefundQueryInput input) {
        SwiftpassRefundQueryInput apiInput = new SwiftpassRefundQueryInput();
        this.buildCommonParam(input, apiInput);

        if (StringUtils.isBlank(input.getRefundListid())) {
            throw new PayApiException(PayApiError.ERR_PARAM, "请传入退款订单号（暂时仅支持查询单个退款订单）");
        }
        apiInput.setOut_trade_no(input.getListid());
        apiInput.setOut_refund_no(input.getRefundListid());
        apiInput.setTransaction_id(input.getTransactionId());

        SwiftpassRefundQueryResult apiResult = SwiftpassPayApiSupport.call(apiInput);

        RefundQueryResult result = new RefundQueryResult();
        result.setTransactionId(apiResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        result.setOutTransactionId(apiResult.getOut_transaction_id());// 第三方订单号，如微信订单号
        result.setListid(apiResult.getOut_trade_no());// 商户订单号,商户系统内部的订单号
        result.setRefundListid(apiResult.getOut_refund_no_0());// 商户退款单号
        result.setOutRefundId(apiResult.getOut_refund_id_0());// 第三方退款订单号，如微信退款订单号
        result.setRefundId(apiResult.getRefund_id_0());// 平台退款单号，如威富通平台退款订单号
        result.setRefundChannel(apiResult.getRefund_channel_0());// ORIGINAL-原路退款，默认
        result.setRefundFee(apiResult.getRefund_fee_0());// 退款金额, 单位为分
        result.setRefundTime(apiResult.getRefund_time_0());// 退款时间 yyyyMMddHHmmss
        result.setRefundState(apiResult.getRefund_status_0());// 退款状态：
        return result;
    }

}
