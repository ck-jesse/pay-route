package com.coy.pay.route.sdk.swiftpass.support;

import com.alibaba.fastjson.JSON;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.swiftpass.constant.SwiftpassPayApiConsts;
import com.coy.pay.route.util.security.MD5;
import com.coy.pay.route.util.security.RSAUtils;
import com.coy.pay.route.util.security.SignUtil;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiInput;
import com.coy.pay.route.sdk.swiftpass.SwiftpassPayApiResult;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.httpclient.HttpClientParam;
import com.coy.pay.route.util.httpclient.HttpClientUtil;
import com.coy.pay.route.util.httpclient.HttpMethod;
import com.coy.pay.route.util.httpclient.HttpResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.TreeMap;

/**
 * 威富通支付api
 *
 * @Author chenck
 * @Date 2018/3/9 14:43
 */
public class SwiftpassPayApiSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(SwiftpassPayApiSupport.class);

    /**
     * convert json to javabean or javabean to json
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * convert xml to javabean or javabean to xml
     */
    private static final XmlMapper xmlMapper = new XmlMapper();

    static {
        // 配置全局属性，反序列化时忽略目标对象没有的属性
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T extends SwiftpassPayApiResult> T call(SwiftpassPayApiInput<T> input) {
        T result = null;
        StringBuilder msgPrefix = new StringBuilder("[SwiftpassApi]");
        try {
            if (null == input) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "input param must not null");
            }
            msgPrefix.append("[").append(input.getClass().getSimpleName()).append("]");
            if (StringUtils.isBlank(input.getService())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "接口类型service不能为空");
            }
            if (StringUtils.isBlank(input.getUrl())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "api url must not null");
            }

            // 计算待签名串
            String signData = SwiftpassPayApiSupport.calcSignData(input);

            // 生成签名
            input.setSign(SwiftpassPayApiSupport.genSign(input, signData, true));

            // 业务参数处理为XML
            String requestXml = xmlMapper.writeValueAsString(input);
            LOGGER.info("{}[requestData]{}", msgPrefix, requestXml);

            result = (T) input.getResultClazz().newInstance();

            // 从配置文件中获取HttpClient连接池配置
            int maxConnTotal = ConfigUtils.getByInt(SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_MAX_CONN_TOTAL_KEY, SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_MAX_CONN_TOTAL);
            int maxConnPerRoute = ConfigUtils.getByInt(SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_MAX_CONN_PER_ROUTE_KEY, SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_MAX_CONN_PER_ROUTE);
            int connectTimeOut = ConfigUtils.getByInt(SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_CONNECT_TIME_OUT_KEY, SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_CONNECT_TIME_OUT);
            int readTimeOut = ConfigUtils.getByInt(SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_READ_TIME_OUT_KEY, SwiftpassPayApiConsts.HTTPCLIENT_SWIFTPASS_DEFAULT_READ_TIME_OUT);

            HttpClientParam reqParam = HttpClientParam.of().setRequesturi(input.getUrl())
                    .setEntity(new StringEntity(requestXml, "UTF-8"))
                    .setMethod(HttpMethod.POST.name())
                    .setMaxConnTotal(maxConnTotal)
                    .setMaxConnPerRoute(maxConnPerRoute);

            RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(readTimeOut);
            if (StringUtils.isNotBlank(input.getProxyHost()) && input.getProxyPort() > 0) {
                builder.setProxy(new HttpHost(input.getProxyHost(), input.getProxyPort()));
            }
            HttpResultDto resultDto = HttpClientUtil.invoke(reqParam, builder.build());

            //ResultDto resultDto = HttpClientUtil.doPost(input.getUrl(), requestXml, input.getProxyHost(), input.getProxyPort());
            LOGGER.info("{}[responseData]{}", msgPrefix, JSON.toJSONString(resultDto));

            // 判断本次请求执行的结果（非业务结果）
            if (resultDto.isFail()) {
                throw new PayApiException(PayApiError.RES_OTHER_ERROR, "[接口调用异常]" + resultDto.getRetMsg());
            }
            if (resultDto.isTimeout()) {
                throw new PayApiException(PayApiError.RES_TIME_OUT_ERROR, "[接口调用超时]" + resultDto.getRetMsg());
            }

            String responseXml = resultDto.getBizDataObj().toString();
            if (StringUtils.isBlank(responseXml)) {
                throw new PayApiException(PayApiError.RES_PAY_ERROR, "api return empty");
            }

            // 响应报文XML转换为Bean
            // 注：在转换响应报文XML时，若响应报文中存在bean中不存在的字段，则会直接抛出异常（抛出明确的异常，方便排查问题）
            // 说明：对于这种情况此处不做忽略处理，因为哪怕忽略未知属性，后面签名验证的时候也会失败
            result = (T) xmlMapper.readValue(responseXml, result.getClass());

            // 判断业务方的执行结果，0表示成功，非0表示失败此字段是通信标识
            if (!SwiftpassPayApiConsts.SUCCESS_CODE.equals(result.getStatus())) {
                throw new PayApiException(result.getStatus(), result.getMessage());
            }
            // 判断业务结果
            if (!SwiftpassPayApiConsts.SUCCESS_CODE.equals(result.getResult_code())) {
                throw new PayApiException(result.getResult_code(), result.getErr_msg());
            }

            // 计算待签名串
            signData = SwiftpassPayApiSupport.calcSignData(result);

            // 验证签名（rsa使用公钥进行签名验签）
            SwiftpassPayApiSupport.checkSign(input, signData, false, result.getSign());

        } catch (PayApiException e) {
            LOGGER.error(msgPrefix.toString(), e);
            throw new PayApiException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.error(msgPrefix.toString(), e);
            throw new PayApiException(PayApiError.SYSTEM_ERROR, e.getMessage());
        }
        if (null == result) {
            throw new PayApiException(PayApiError.SYSTEM_ERROR, "系统繁忙");
        }
        return result;
    }

    /**
     * 计算待签名串
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/4/12 14:27
     */
    public static String calcSignData(Object input) throws IOException {
        // 将bean转换为json【目的：过滤无需参与签名的字段】
        String json = objectMapper.writeValueAsString(input);

        // 将json转换为map
        TreeMap<String, Object> objFields = objectMapper.readValue(json, TreeMap.class);
        objFields.remove("sign");

        // 计算待签名串
        return SignUtil.map2Params(objFields, true);
    }

    /**
     * 生成签名【MD5加密】
     *
     * @Param rsaKeyFlag true表示使用ras私钥计算签名，false表示使用ras公钥计算签名
     * @Author chenck
     * @Date 2018/3/9 15:34
     */
    public static String genSign(SwiftpassPayApiInput input, String signData, boolean rsaKeyFlag) throws IOException {
        if (SwiftpassPayApiConsts.MD5.equalsIgnoreCase(input.getSign_type())) {
            if (StringUtils.isBlank(input.getSecrtkey())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "md5 secrtkey must not null");
            }
            return MD5.encode(signData + "&key=" + input.getSecrtkey(), input.getCharset());
        } else if (SwiftpassPayApiConsts.RSA_1_256.equalsIgnoreCase(input.getSign_type())) {
            if (StringUtils.isBlank(input.getRsaPrivateKey())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "rsa private key must not null");
            }
            if (StringUtils.isBlank(input.getRsaPublicKey())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "rsa public key must not null");
            }
            // 签名类型为RSA_1_256时，对于请求参数的签名计算采用我方私钥，对于响应参数的签名计算采用第三方公钥
            String key = input.getRsaPrivateKey();
            if (!rsaKeyFlag) {
                key = input.getRsaPublicKey();
            }
            byte[] signBuf = RSAUtils.sign(RSAUtils.SignatureSuite.SHA256, signData.getBytes(), key);
            return new String(Base64.encodeBase64(signBuf), input.getCharset());
        } else {
            throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "签名类型不合法");
        }
    }

    /**
     * 验证签名
     *
     * @Param
     * @Author chenck
     * @Date 2018/3/9 15:36
     */
    public static void checkSign(SwiftpassPayApiInput input, String signData, boolean rsaKeyFlag, String oldSign) throws IOException {
        if (SwiftpassPayApiConsts.RSA_1_256.equalsIgnoreCase(input.getSign_type())) {
            boolean flag = RSAUtils.verifySign(RSAUtils.SignatureSuite.SHA256, signData.getBytes("UTF8"), Base64.decodeBase64(oldSign.getBytes("UTF8")), input.getRsaPublicKey());
            if (!flag) {
                LOGGER.error("[威富通支付api]应答签名校验RSA签名失败");
                throw new PayApiException(PayApiError.RES_SIGN_ERROR, "支付异常(应答签名校验RSA签名失败)");
            }
        } else {
            String sign = SwiftpassPayApiSupport.genSign(input, signData, rsaKeyFlag);
            // 忽略大小写进行比较
            if (StringUtils.isBlank(sign) || !sign.toUpperCase().equalsIgnoreCase(oldSign)) {
                throw new PayApiException(PayApiError.RES_SIGN_ERROR, "[威富通支付api]应答签名校验失败,old sign:" + oldSign + ", new sign:" + sign + "");
            }
        }
    }
}
