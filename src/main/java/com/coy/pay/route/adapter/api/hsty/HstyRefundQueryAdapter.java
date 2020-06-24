package com.coy.pay.route.adapter.api.hsty;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.RefundQueryInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.RefundStateEnum;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundQueryInput;
import com.coy.pay.route.adapter.dto.RefundQueryResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundQueryResult;
import com.coy.pay.route.sdk.hsty.support.HstyPayApiSupport;

/**
 * 【适配器】【汇商通盈】退款查询
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = PayPassIdEnum.HSTYPAY, apiId = CustomApiIdEnum.TRADE_REFUND_QUERY)
public class HstyRefundQueryAdapter extends AbstractHstyPayApiAdapter<RefundQueryInput, RefundQueryResult> {

    @Override
    public RefundQueryResult call(RefundQueryInput input) {
        HstyRefundQueryInput apiInput = new HstyRefundQueryInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setOut_refund_no(input.getRefundListid());
        apiInput.setTransaction_id(input.getTransactionId());
        apiInput.setOp_user_id(input.getOpUserId());// 操作员帐号,默认为商户号
        apiInput.setDevice_info(input.getDeviceInfo());

        HstyRefundQueryResult apiResult = HstyPayApiSupport.call(apiInput);

        RefundQueryResult result = new RefundQueryResult();
        result.setTransactionId(apiResult.getTransaction_id());// 汇商订单号
        result.setListid(apiResult.getOut_trade_no());// 商户订单号,商户系统内部的订单号
        result.setRefundListid(apiResult.getOut_refund_no());// 商户退款单号
        result.setRefundId(apiResult.getRefund_id());// 平台退款单号，如威富通平台退款订单号
        result.setRefundChannel(apiResult.getRefund_channel());// ORIGINAL-原路退款，默认
        result.setRefundFee(apiResult.getRefund_fee());// 退款金额, 单位为分
        result.setRefundTime(apiResult.getRefund_time());// 退款时间 yyyyMMddHHmmss
        // 退款状态 1:初始化;2:退款成功;3:退款失败;4:未确定;5:转入代发
        if ("1".equals(apiResult.getRefund_status())) {
            result.setRefundState(RefundStateEnum.PROCESSING.getState());
        } else if ("2".equals(apiResult.getRefund_status())) {
            result.setRefundState(RefundStateEnum.SUCCESS.getState());
        } else if ("3".equals(apiResult.getRefund_status())) {
            result.setRefundState(RefundStateEnum.FAIL.getState());
        } else if ("4".equals(apiResult.getRefund_status())) {
            result.setRefundState(RefundStateEnum.NOTSURE.getState());
        } else if ("5".equals(apiResult.getRefund_status())) {
            result.setRefundState(RefundStateEnum.CHANGE.getState());
        }
        return result;
    }

}
