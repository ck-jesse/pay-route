package com.coy.pay.route.util.httpclient;


import com.coy.pay.route.util.httpclient.annotation.HttpApiAnno;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HttpApiAnno标注类的容器工具类
 *
 * @author chenck
 * @date 2019/7/8 20:49
 */
public class HttpApiAnnoContainerUtil {

    /**
     * input对应的注解缓存
     */
    private static final Map<String, HttpApiAnno> INPUT_ANNOTATIONS = new ConcurrentHashMap<String, HttpApiAnno>();

    /**
     * 锁, 防止重复连接同一配置
     */
    private static final Object lock = new Object();

    /**
     * 获取输入参数对象上的HttpApiAnno注解配置
     *
     * @param clazz
     */
    public static HttpApiAnno getHttpApiAnno(Class<?> clazz) {
        HttpApiAnno anno = INPUT_ANNOTATIONS.get(clazz.getName());
        if (null != anno) {
            return anno;
        }
        synchronized (lock) {
            anno = INPUT_ANNOTATIONS.get(clazz.getName());
            if (null != anno) {
                return anno;
            }
            anno = clazz.getAnnotation(HttpApiAnno.class);
            if (null != anno) {
                INPUT_ANNOTATIONS.put(clazz.getName(), anno);
            }
            return anno;
        }
    }
}
