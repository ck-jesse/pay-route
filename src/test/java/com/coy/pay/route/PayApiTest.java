package com.coy.pay.route;

import com.alibaba.fastjson.JSON;
import com.coy.pay.route.adapter.dto.PayQueryInput;
import com.coy.pay.route.adapter.dto.PayQueryResult;
import com.coy.pay.route.adapter.dto.RefundInput;
import com.coy.pay.route.adapter.dto.RefundQueryInput;
import com.coy.pay.route.adapter.dto.RefundQueryResult;
import com.coy.pay.route.adapter.dto.RefundResult;
import com.coy.pay.route.adapter.dto.WeixinJSPayInput;
import com.coy.pay.route.adapter.dto.WeixinJSPayResult;
import com.coy.pay.route.adapter.dto.WeixinJSPayinfo;
import com.coy.pay.route.adapter.dto.WeixinNativeInput;
import com.coy.pay.route.adapter.dto.WeixinNativeResult;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.support.PayApiRoute;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

/**
 * 支付网关路由测试
 *
 * @Author chenck
 * @Date 2017/11/28 19:27
 */
public class PayApiTest {

    private static String payPassId;
    private static String requesturi;
    private static String signId;
    private static String secretKey;
    private static String signType;
    private static String mchId;// 商户号，由第三方支付分配（如威富通或汇商通盈）
    private static String listid;
    private static String refundListid;

    // 威富通支付通道-测试配置
    public static void swiftpassTestConfig() {
        payPassId = PayPassIdEnum.SWIFTPASS.getValue();
        requesturi = "https://pay.swiftpass.cn/pay/gateway";
        mchId = "7551000001";
        signId = "7551000001";// 威富通
        secretKey = "9d101c97133837e13dde2d32a5054abb";
        signType = "MD5";
    }

    // 汇商通盈支付通道-测试配置
    public static void hstyTestConfig() {
        payPassId = PayPassIdEnum.HSTYPAY.getValue();
        requesturi = "https://pay.hstytest.com/pay/gateway";// 汇商内部测试环境
        signId = "396100000001";// 对应sign_agentno
        secretKey = "9Ccam5oF8kMPSIlXxwYS2laj5mHugzJn";
        signType = "MD5";
        mchId = "343300000001";// 对应store_merchant_id
    }

    // 卡联支付通道-测试配置
    public static void kalianTestConfig() {
        payPassId = PayPassIdEnum.KALIAN.getValue();
        requesturi = "https://jhzf.jzctb.com/AMPS/uwxPay/gateway";//
        signId = "";// 对应sign_agentno
        secretKey = "4vj6xfxxjwmi3wuhpi0m5eeg5mfjnx9p";
        signType = "MD5";
        mchId = "472100000473713";// 对应store_merchant_id
    }

    public static void setCommonParam(PayApiInput input) {
        input.setUrl(requesturi);
        input.setAgentno(signId);
        input.setSecretKey(secretKey);
        input.setMchId(mchId);
        input.setSignType(signType);
        input.setPayPassId(payPassId);
        input.setOpUserId(mchId);// 操作员帐号,默认为商户号
    }

    @Before
    public void before() {
        // 初始化配置
        // hstyTestConfig();
        // kalianTestConfig();
        swiftpassTestConfig();

        listid = "20200624000001";
        refundListid = "202006240000010001";
    }

    // 交易查詢
    @Test
    public void payQuery() {
        PayQueryInput input = new PayQueryInput();
        setCommonParam(input);
        input.setListid(listid);
        PayQueryResult result = PayApiRoute.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    // 微信公众号支付
    @Test
    public void weixinJSPay() {
        WeixinJSPayInput input = new WeixinJSPayInput();
        setCommonParam(input);
        input.setListid(listid);
        input.setBody("测试商品");
        input.setTotalFee(1L);
        input.setMchCreateIp("127.0.0.1");
        input.setNotifyUrl("http://paydev1.test.xxx.com/eppc-web/tradeaction/buy_pcard_notify/kalian_pass");
        input.setCallbackUrl("http://eppc.xxx.com");// 公众号支付
        input.setDeviceInfo("MEMEPPC_WX");
        input.setIs_minipg("0");// 小程序支付

        Calendar calendar = Calendar.getInstance();
        input.setTimeStart(calendar.getTime());
        calendar.add(Calendar.MINUTE, 30);
        input.setTimeExpire(calendar.getTime());
        input.setOpenid("ofDK05O8FrzOEw91-_MBMuRRUKK0");
        input.setSubOpenid("ofDK05O8FrzOEw91-_MBMuRRUKK0");
        input.setSubAppid("wx6edbb4aa778ac786");// 汇商通盈

        WeixinJSPayResult result = PayApiRoute.call(input);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeixinJSPayinfo weixinJSPayinfo = objectMapper.readValue(result.getPayInfo(), WeixinJSPayinfo.class);
            System.out.println(weixinJSPayinfo.getCallback_url());
            if (StringUtils.isBlank(weixinJSPayinfo.getCallback_url()) || weixinJSPayinfo.getCallback_url().equalsIgnoreCase("\"null\"")) {
                System.out.println("================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));
    }

    // 微信扫码支付
    @Test
    public void weixinNative() {
        WeixinNativeInput input = new WeixinNativeInput();
        setCommonParam(input);
        input.setListid(listid);
        input.setBody("测试商品");
        input.setTotalFee(1L);
        input.setMchCreateIp("127.0.0.1");
        input.setNotifyUrl("http://paydev1.test.xxx.com/eppc-web/tradeaction/buy_pcard_notify/swt_pass");
        input.setDeviceInfo("MEMEPPC_WX");
        Calendar calendar = Calendar.getInstance();
        input.setTimeStart(calendar.getTime());
        calendar.add(Calendar.MINUTE, 30);
        input.setTimeExpire(calendar.getTime());
        WeixinNativeResult result = PayApiRoute.call(input);
        System.out.println(JSON.toJSONString(result));
    }

    // 退款
    @Test
    public void refund() {
        RefundInput refundInput = new RefundInput();
        setCommonParam(refundInput);
        refundInput.setListid(listid);
        refundInput.setRefundListid(refundListid);
        refundInput.setTotalFee(1L);
        refundInput.setRefundFee(1L);
        refundInput.setDeviceInfo("sss");
        RefundResult refundResult = PayApiRoute.call(refundInput);
        System.out.println(JSON.toJSONString(refundResult));
    }

    // 退款查询
    @Test
    public void refundQuery() {
        RefundQueryInput input = new RefundQueryInput();
        setCommonParam(input);
        input.setListid(listid);
        input.setRefundListid(refundListid);
        RefundQueryResult result = PayApiRoute.call(input);
        System.out.println(JSON.toJSONString(result));
    }
}
