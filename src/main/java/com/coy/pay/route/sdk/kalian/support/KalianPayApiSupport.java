package com.coy.pay.route.sdk.kalian.support;

import com.alibaba.fastjson.JSON;
import com.coy.pay.route.exception.PayApiException;
import com.coy.pay.route.sdk.kalian.KalianPayApiInput;
import com.coy.pay.route.util.security.MD5;
import com.coy.pay.route.sdk.kalian.KalianPayApiResult;
import com.coy.pay.route.sdk.kalian.constant.KalianPayApiConsts;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.util.ConfigUtils;
import com.coy.pay.route.util.httpclient.HttpClientParam;
import com.coy.pay.route.util.httpclient.HttpClientUtil;
import com.coy.pay.route.util.httpclient.HttpMethod;
import com.coy.pay.route.util.httpclient.HttpResultDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 中旅支付接口辅助类
 *
 * @author yehl
 * @date 2018/8/21 9:40
 */
public class KalianPayApiSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(KalianPayApiSupport.class);

    /**
     * convert json to javabean or javabean to json
     */
    private static final ObjectMapper objectMapper = new ObjectMapper() {
        @Override
        public ObjectMapper setSerializationInclusion(JsonInclude.Include incl) {
            return super.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
    };

    static {
        // 配置全局属性，反序列化时忽略目标对象没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T extends KalianPayApiResult> T call(KalianPayApiInput<T> input) {
        T result = null;
        StringBuilder msgPrefix = new StringBuilder("[CtsBankApi]");
        try {
            if (null == input) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "input param must not null");
            }
            msgPrefix.append("[").append(input.getClass().getSimpleName()).append("]");
            if (StringUtils.isBlank(input.getUrl())) {
                throw new PayApiException(PayApiError.RES_PAY_PARAM_ERROR, "api url must not null");
            }

            // 计算待签名串
            String signData = KalianPayApiSupport.calcSignData(input);

            // 生成签名
            input.setSign(KalianPayApiSupport.genSign(input, signData));

            // 业务参数处理为JSON字符串
            String requestData = obj2Json(input);
            LOGGER.info("{}[requestData]{}", msgPrefix, requestData);

            // 从配置文件中获取HttpClient连接池配置
            int maxConnTotal = ConfigUtils.getByInt(KalianPayApiConsts.HTTPCLIENT_CTSBANK_MAX_CONN_TOTAL_KEY, KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_MAX_CONN_TOTAL);
            int maxConnPerRoute = ConfigUtils.getByInt(KalianPayApiConsts.HTTPCLIENT_CTSBANK_MAX_CONN_PER_ROUTE_KEY, KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_MAX_CONN_PER_ROUTE);
            int connectTimeOut = ConfigUtils.getByInt(KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_CONNECT_TIME_OUT_KEY, KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_CONNECT_TIME_OUT);
            int readTimeOut = ConfigUtils.getByInt(KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_READ_TIME_OUT_KEY, KalianPayApiConsts.HTTPCLIENT_CTSBANK_DEFAULT_READ_TIME_OUT);

            HttpClientParam reqParam = HttpClientParam.of().setRequesturi(input.getUrl())
                    .setEntity(new StringEntity(requestData, "UTF-8"))
                    .setMethod(HttpMethod.POST.name())
                    .setMaxConnTotal(maxConnTotal)
                    .setMaxConnPerRoute(maxConnPerRoute);

            RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(readTimeOut);
            if (StringUtils.isNotBlank(input.getProxyHost()) && input.getProxyPort() > 0) {
                builder.setProxy(new HttpHost(input.getProxyHost(), input.getProxyPort()));
            }
            HttpResultDto resultDto = HttpClientUtil.invoke(reqParam, builder.build());

            LOGGER.info("{}[responseData]{}", msgPrefix, obj2Json(resultDto));

            // 判断本次请求执行的结果（非业务结果）
            if (resultDto.isFail()) {
                throw new PayApiException(PayApiError.RES_OTHER_ERROR, "[接口调用异常]" + resultDto.getRetMsg());
            }
            if (resultDto.isTimeout()) {
                throw new PayApiException(PayApiError.RES_TIME_OUT_ERROR, "[接口调用超时]" + resultDto.getRetMsg());
            }

            String responseData = resultDto.getBizDataObj().toString();
            if (StringUtils.isBlank(responseData)) {
                throw new PayApiException(PayApiError.RES_PAY_ERROR, "api return empty");
            }
            result = (T) json2Obj(input.getResultClazz(), responseData);

            // 判断业务方的执行结果，0表示成功，非0表示失败此字段是通信标识
            if (!result.getReturn_code().startsWith("0")) {
                throw new PayApiException(result.getReturn_code(), result.getReturn_msg());
            }
            // 计算待签名串
            signData = KalianPayApiSupport.calcSignData(result);

            // 验证签名
            KalianPayApiSupport.checkSign(input, signData, result.getSign());
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
     * 对象转json
     *
     * @param obj
     * @return
     * @throws IOException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @author chenck
     * @date 2016年12月16日 下午4:53:30
     */
    public static String obj2Json(Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        StringWriter str = new StringWriter();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置null的属性不参与序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.writeValue(str, obj);
        return str.toString();
    }

    /**
     * json转对象
     *
     * @param resultClazz
     * @param jsonData
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static <T extends KalianPayApiResult> T json2Obj(Class<T> resultClazz, String jsonData) throws IOException, JsonParseException, JsonMappingException {
        LOGGER.info(String.format("[json2Obj(Class<T> resultClazz, String jsonData)]:resultClazz:%s,jsonData:%s", resultClazz.toString(), jsonData));
        if (StringUtils.isBlank(jsonData)) {
            return null;
        }
        return objectMapper.readValue(jsonData, resultClazz);
//        return JSON.parseObject(jsonData, resultClazz);
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
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 将bean转换为json【目的：过滤无需参与签名的字段】
        String json = objectMapper.writeValueAsString(input);
        // 将json转换为map
        TreeMap<String, Object> objFields = objectMapper.readValue(json, TreeMap.class);
        objFields.remove("sign");

        // 计算待签名串
        return map2Params(objFields, true);
    }

    public static String map2Params(Map<String, Object> params, boolean removeBlankField) {
        StringBuilder sb = new StringBuilder(1024);
        boolean first = true;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            // 移除值为空或为null的字段，不拼接到k=v串中
            if (removeBlankField
                    && (entry.getValue() == null || "".equals(entry.getValue().toString()))) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            String value = entry.getValue().toString();
            if (entry.getValue() instanceof List || entry.getValue() instanceof Map) {
                value = JSON.toJSONString(entry.getValue());
            }
            sb.append(entry.getKey()).append("=").append(value);
        }
        return sb.toString();
    }

    /**
     * 生成签名【MD5加密】
     *
     * @Author chenck
     * @Date 2018/3/9 15:34
     */
    public static String genSign(KalianPayApiInput input, String signData) throws IOException {
        return MD5.encode(signData + "&" + input.getSecrtkey(), input.getCharset()).toUpperCase();
    }

    /**
     * 验证签名
     *
     * @Param
     * @Author chenck
     * @Date 2018/3/9 15:36
     */
    public static void checkSign(KalianPayApiInput input, String signData, String oldSign) throws IOException {
        String sign = KalianPayApiSupport.genSign(input, signData);
        // 忽略大小写进行比较
        if (StringUtils.isBlank(sign) || !sign.toUpperCase().equalsIgnoreCase(oldSign)) {
            throw new PayApiException(PayApiError.RES_SIGN_ERROR, "[卡联支付api]应答签名校验失败,old sign:" + oldSign + ", new sign:" + sign + "");
        }
    }
}
