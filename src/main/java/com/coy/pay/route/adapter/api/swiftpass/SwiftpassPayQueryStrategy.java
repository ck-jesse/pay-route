package com.coy.pay.route.adapter.api.swiftpass;

import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassTradeQueryResult;
import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.dto.PayQueryInput;
import com.coy.pay.route.adapter.dto.PayQueryResult;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassTradeQueryInput;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;

/**
 * 交易查询
 *
 * @author chenck
 * @date 2018/8/23 14:38
 */
@ThirdPayApiAnno(payPassId = {PayPassIdEnum.SWIFTPASS, PayPassIdEnum.SWT_CIB, PayPassIdEnum.SWT_SPDB}, apiId = CustomApiIdEnum.TRADE_QUERY)
public class SwiftpassPayQueryStrategy extends AbstractSwiftpassPayApiStrategy<PayQueryInput, PayQueryResult> {

    @Override
    public PayQueryResult call(PayQueryInput input) {
        SwiftpassTradeQueryInput apiInput = new SwiftpassTradeQueryInput();
        this.buildCommonParam(input, apiInput);

        apiInput.setOut_trade_no(input.getListid());
        apiInput.setTransaction_id(input.getTransactionId());
        SwiftpassTradeQueryResult apiResult = SwiftpassPayApiSupport.call(apiInput);

        PayQueryResult result = new PayQueryResult();
        result.setTradeState(apiResult.getTrade_state());// 交易状态
        result.setTradeType(apiResult.getTrade_type());// 交易类型 pay.weixin.native
        result.setAppid(apiResult.getAppid());// 公众号appid 服务商公众号appid
        result.setOpenid(apiResult.getOpenid());// 用户标识 用户在服务商 appid 下的唯一标识
        result.setIsSubscribe(apiResult.getIs_subscribe());// 是否关注公众账号 用户是否关注服务商公众账号，Y-关注，N-未关注
        result.setSubAppid(apiResult.getSub_appid());// 子商户 appid
        result.setSubOpenid(apiResult.getSub_openid());// 用户在子商户 sub_appid 下的唯一标识
        result.setSubIsSubscribe(apiResult.getSub_is_subscribe());//用户是否关注子公众账号，1-关注，0-未关 注，仅在公众账号类型支付有效
        result.setListid(apiResult.getOut_trade_no());// 商户订单号
        result.setTransactionId(apiResult.getTransaction_id());// 平台订单号，如威富通平台订单号
        result.setOutTransactionId(apiResult.getOut_transaction_id());// 第三方订单号，如微信订单号（支付成功后会返回，没支付则不会）
        result.setTotalFee(apiResult.getTotal_fee());// 总金额，以分为单位，不允许包含任何字、符号
        result.setCouponFee(apiResult.getCoupon_fee());// 现金券金额 现金券支付金额<=订单总金额， 订单总金额-现金券金额为现金支付金额
        result.setFeeType(apiResult.getFee_type());// 货币种类 货币类型，符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
        result.setAttach(apiResult.getAttach());// 附加信息 商家数据包，原样返回
        result.setBankType(apiResult.getBank_type());// 付款银行	银行类型
        result.setBankBillno(apiResult.getBank_billno());// 银行订单号 银行订单号，若为微信支付则为空
        result.setTimeEnd(apiResult.getTime_end());// 支付完成时间 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒
        return result;
    }

}
