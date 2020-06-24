package com.coy.pay.route;


import com.alibaba.fastjson.JSON;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundInput;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundQueryInput;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundQueryResult;
import com.coy.pay.route.sdk.hsty.dto.HstyRefundResult;
import com.coy.pay.route.sdk.hsty.dto.HstyTradeQueryInput;
import com.coy.pay.route.sdk.hsty.dto.HstyTradeQueryResult;
import com.coy.pay.route.sdk.hsty.support.HstyPayApiSupport;
import com.coy.pay.route.util.DES3Util;
import com.coy.pay.route.util.security.AES;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * 汇商支付api接口测试
 * 注：需汇商设置ip白名单才能调用
 *
 * @author chenck
 * @date 2018/4/13 14:01
 */
public class HstyPayTest {

    private static String requesturi;
    private static String sign_agentno;
    private static String store_merchant_id;
    private static String secrtkey;

    @Before
    public void testConfig() {
        requesturi = "https://pay.hstytest.com/pay/gateway";// 汇商内部测试环境
        requesturi = "https://upay.hstytest.com/pay/gateway";// 汇商联合开发环境
        sign_agentno = "396100000001";
        store_merchant_id = "343300000001";
        secrtkey = "9Ccam5oF8kMPSIlXxwYS2laj5mHugzJn";
    }

    @Test
    public void tradeQuery() {
        HstyTradeQueryInput input = new HstyTradeQueryInput();
        input.setUrl(requesturi);
        input.setSign_agentno(sign_agentno);
        input.setStore_merchant_id(store_merchant_id);
        input.setSecrtkey(secrtkey);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOut_trade_no("1804291000825095145");

        HstyTradeQueryResult result = HstyPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void refund() {
        HstyRefundInput input = new HstyRefundInput();
        input.setUrl(requesturi);
        input.setSecrtkey(secrtkey);
        input.setSign_agentno(sign_agentno);
        input.setStore_merchant_id(store_merchant_id);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOp_user_id(sign_agentno);// 操作员帐号,默认为商户号
        input.setDevice_info("MEMEPPC_WX");
        input.setOut_trade_no("1804101000172049970");
        input.setOut_refund_no("20180413143000");
        input.setRefund_fee(1L);// 退款总金额,单位为分,可以做部分退款
        HstyRefundResult result = HstyPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void refundQuery() {
        HstyRefundQueryInput input = new HstyRefundQueryInput();
        input.setUrl(requesturi);
        input.setSecrtkey(secrtkey);
        input.setSign_agentno(sign_agentno);
        input.setStore_merchant_id(store_merchant_id);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOp_user_id(sign_agentno);// 操作员帐号,默认为商户号
        input.setDevice_info("MEMEPPC_WX");
        input.setOut_trade_no("1804101000172049970");
        input.setOut_refund_no("20180413143000");

        HstyRefundQueryResult result = HstyPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }


}
