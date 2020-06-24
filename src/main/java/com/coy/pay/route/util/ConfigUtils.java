package com.coy.pay.route.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 配置读取工具类
 */
public class ConfigUtils {

    public static String get(String key) {
        // 从系统属性中获取
        String value = System.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        // 从环境变量中获取
        value = System.getenv(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return defaultValue;
    }

    public static Integer getByInt(String key, int defaultValue) {
        String value = get(key);
        if (StringUtils.isNotBlank(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public static void main(String[] args) {
        System.setProperty("coy.pay.test", "10");
        System.out.println(ConfigUtils.getByInt("coy.pay.test", 1));
    }
}
