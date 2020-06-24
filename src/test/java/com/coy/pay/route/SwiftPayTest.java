package com.coy.pay.route;


import com.alibaba.fastjson.JSON;
import com.coy.pay.route.adapter.consts.PayConstants;
import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinJSPayInput;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassWeixinJSPayResult;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundInput;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundQueryInput;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundQueryResult;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassRefundResult;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassTradeQueryInput;
import com.coy.pay.route.sdk.swiftpass.dto.SwiftpassTradeQueryResult;
import com.coy.pay.route.sdk.swiftpass.support.SwiftpassPayApiSupport;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.DateUtils;
import com.coy.pay.route.util.security.MD5;
import org.junit.Test;

import java.util.Date;

/**
 * @Author chenck
 * @Date 2018/3/9 12:06
 */
public class SwiftPayTest {

    private static final String requesturi = "https://pay.swiftpass.cn/pay/gateway";
    private static final String swiftid = "7551000001";
    private static final String swiftkey = "9d101c97133837e13dde2d32a5054abb";
    private static final String rsaPrivateKey = "";// RSA私钥（我方私钥，用于对请求数据进行签名，请求第三方支付）
    private static final String rsaPublicKey = "";// RSA公钥（对方公钥，用于对第三方支付响应数据进行验签）

    @Test
    public void prepay() {
        SwiftpassWeixinJSPayInput input = new SwiftpassWeixinJSPayInput();
        input.setService(SwiftpassPayApiConsts.PAY_WEIXIN_JSPAY);
        input.setUrl(requesturi);
        input.setSecrtkey(swiftkey);
        input.setMch_id(swiftid);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOut_trade_no("20180821000001");
        input.setBody("测试商品");
        input.setTotal_fee(1L);
        input.setMch_create_ip("127.0.0.1");
        input.setNotify_url("http://eppc.xxx.com");
        input.setDevice_info(ConfigUtils.get(PayConstants.PAY_WFT_DEVICE_INFO, "MEMEPPC_WX"));
        input.setOp_user_id(swiftid);// 操作员帐号,默认为商户号

        if ("MD5".equals(input.getSign_type())) {
            input.setSecrtkey(swiftkey);
        } else if ("RSA_1_256".equals(input.getSign_type())) {
            input.setRsaPrivateKey(rsaPrivateKey);// 私钥
            input.setRsaPublicKey(rsaPublicKey);// 公钥
        }
        // H5端需要走jspay接口
        if (SwiftpassPayApiConsts.PAY_WEIXIN_JSPAY.equals(input.getService())) {
            input.setIs_raw("1");
            input.setIs_minipg("1");// 小程序支付
            input.setCallback_url("");// 公众号支付
            if (!"7551000001".equals(swiftid)) {
                input.setSub_openid("");
                input.setSub_appid("");
            }
            input.setTime_start(DateUtils.dateToStr(new Date(), DateUtils.YYYYMMDDHHMMSS));
            input.setTime_expire("");
        }
        // PC端
        else if (SwiftpassPayApiConsts.PAY_WEIXIN_NATIVE.equals(input.getService())) {
            input.setTime_start("");
            input.setTime_expire("");
        }
        // APP端
        else {
            input.setService(SwiftpassPayApiConsts.UNIFIED_TRADE_PAY);
        }

        SwiftpassWeixinJSPayResult result = SwiftpassPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void tradeQuery() {
        SwiftpassTradeQueryInput input = new SwiftpassTradeQueryInput();
        input.setUrl(requesturi);
        input.setSecrtkey(swiftkey);
        input.setMch_id(swiftid);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOut_trade_no("1803201000145003121");
        input.setTransaction_id("105520019809201803201123396423");

        SwiftpassTradeQueryResult result = SwiftpassPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void refund() {
        SwiftpassRefundInput input = new SwiftpassRefundInput();
        input.setUrl(requesturi);
        input.setSecrtkey(swiftkey);
        input.setMch_id(swiftid);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        input.setOut_trade_no("1804171000225144273");
        input.setOut_refund_no("201804181800001");
        input.setTotal_fee(1L);// 订单总金额，单位为分
        input.setRefund_fee(1L);// 退款总金额,单位为分,可以做部分退款
        input.setOp_user_id(swiftid);// 操作员帐号,默认为商户号
        // 对退款来源进行MD5加密
        input.setRefund_source(MD5.encode(SwiftpassPayApiConsts.REFUND_SOURCE_KEY, "UTF-8").toUpperCase());
        SwiftpassRefundResult result = SwiftpassPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void refundQuery() {
        SwiftpassRefundQueryInput input = new SwiftpassRefundQueryInput();
        input.setUrl(requesturi);
        input.setSecrtkey(swiftkey);
        input.setMch_id(swiftid);
        input.setSign_type("MD5");
        input.setNonce_str(String.valueOf(new Date().getTime()));
        //input.setOut_trade_no("1803201000145003121");
        input.setOut_refund_no("1804212000980428570");
        //input.setTransaction_id("105520019809201803201123396423");

        SwiftpassRefundQueryResult result = SwiftpassPayApiSupport.call(input);
        System.out.println(JSON.toJSONString(result));
    }


}
