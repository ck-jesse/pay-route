package com.coy.pay.route.adapter.dto.base;

import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 支付查询入参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class PayApiInput<T extends PayApiResult> implements Serializable {

    /**
     * 创建参数化泛型class的对象
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/28 18:17
     */
    public T createResultObj() {
        Class<T> resultClazz = null;
        // 获取带有泛型的父类
        Type type = getClass().getGenericSuperclass();
        // 参数化泛型
        if (type instanceof ParameterizedType) {
            // 获取参数化类型的数组，可能有多个泛型
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            // 此处只有一个泛型，故取第一个
            resultClazz = (Class<T>) p[0];
        }
        if (null == resultClazz) {
            throw new PayApiException(PayApiError.ERR_CONFIG, getClass().getSimpleName() + "未设置对应的响应类型（即未设置Result）");
        }
        try {
            return resultClazz.newInstance();
        } catch (Exception e) {
            throw new PayApiException(PayApiError.ERR_APP, "第三方支付:创建响应结果对象异常");
        }
    }

    private String payPassId;// 支付通道标识
    private String apiId;// 自定义支付api标识
    private String url;// 支付api地址

    private String version;// 版本号
    private String charset;// 字符集，UTF-8
    private String signType;// 签名方式，如MD5/SHA256/RSA
    private String secretKey;// 签名秘钥
    private String sign;// 签名
    private String opUserId;// 操作员
    private String deviceInfo;// 设备号,汇商支付分配的终端设备信息

    // 支付通道签名验签模式：
    // 1、商户号+秘钥模式（如威富通）
    // 2、渠道号+秘钥模式（如汇商通盈、中旅）
    private String mchId;// 商户号，由第三方支付分配（如威富通或汇商通盈）
    private String agentno;// 渠道号，由第三方支付分配（如威富通或汇商通盈）

    // RSA签名验签方式不是每个第三方支付都会使用，此处冗余，需要使用时才使用
    private String rsaPrivateKey;// RSA私钥（我方私钥，用于对请求数据进行签名，请求第三方支付）
    private String rsaPublicKey;// RSA公钥（对方公钥，用于对第三方支付响应数据进行验签）

    public String getPayPassId() {
        return payPassId;
    }

    public void setPayPassId(String payPassId) {
        this.payPassId = payPassId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAgentno() {
        return agentno;
    }

    public void setAgentno(String agentno) {
        this.agentno = agentno;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }
}
