package com.coy.pay.route.util.httpclient.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * api基础入参
 *
 * @Author chenck
 * @Date 2019/1/18 11:33
 */
@Data
@Accessors(chain = true)
public class HttpApiInput<T extends HttpApiResult> {

    public HttpApiInput() {
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
    @JSONField(serialize = false)// fastjson 忽略该字段
    protected String url;

    /**
     * 代理ip
     */
    @JSONField(serialize = false)
    protected String proxyHost;

    /**
     * 代理端口
     */
    @JSONField(serialize = false)
    protected int proxyPort;

    /**
     * 响应参数对应的class，用于反序列化
     */
    @JSONField(serialize = false)
    protected Class<T> resultClazz;

}
