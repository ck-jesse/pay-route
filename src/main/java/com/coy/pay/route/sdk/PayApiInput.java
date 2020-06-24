package com.coy.pay.route.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 第三方支付api基础入参
 * 注：TODO 暂时先简单实现，后续重构时，封装一套针对【对接第三方接口】的基础组件
 * 对接不同的第三方，只需做如下操作：
 * 1、实现第三方接口的签名验签
 * 2、实现第三方接口的入参组装
 * 3、实现第三方接口的出参解析
 *
 * @author chenck
 * @date 2018/4/10 19:48
 */
public class PayApiInput<T extends PayApiResult> {

    /**
     * 获取参数化泛型class
     */
    @SuppressWarnings("unchecked")
    public PayApiInput() {
        // 获取带有泛型的父类
        Type type = getClass().getGenericSuperclass();
        // 参数化泛型
        if (type instanceof ParameterizedType) {
            // 获取参数化类型的数组，可能有多个泛型
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            // 此处只有一个泛型，故取第一个
            resultClazz = (Class<T>) p[0];
        }
    }

    /**
     * 请求目标url
     */
    @JsonIgnore
    private String url;

    /**
     * 代理ip
     */
    @JsonIgnore
    private String proxyHost;

    /**
     * 代理端口
     */
    @JsonIgnore
    private int proxyPort;

    /**
     * 响应参数对应的class，用于反序列化
     */
    @JsonIgnore
    private Class<T> resultClazz;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Class<T> getResultClazz() {
        return resultClazz;
    }

    public void setResultClazz(Class<T> resultClazz) {
        this.resultClazz = resultClazz;
    }
}
