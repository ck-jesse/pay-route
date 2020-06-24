package com.coy.pay.route.util.httpclient.annotation;


import com.coy.pay.route.util.httpclient.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP api 注解
 *
 * @author chenck
 * @date 2019/6/14 10:57
 */
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HttpApiAnno {

    /**
     * URL参数以格式化模式 构建
     */
    String URL_PARAM_FORMAT_MODE = "FORMAT";
    /**
     * URL参数以拼接模式构建
     */
    String URL_PARAM_JOIN_MODE = "JOIN";

    /**
     * 数据来源：对象本身
     */
    String DATA_FROM_OBJECT_SELF = "SELF";
    /**
     * 数据来源：对象的paramMap字段
     */
    String DATA_FROM_FIELD_MAP = "MAP";
    /**
     * 数据来源：对象的paramStr字段（该来源用于外部自行组装请求参数传入，便于扩展）
     */
    String DATA_FROM_FIELD_PARAM_STR = "STR";

    /**
     * 数据格式：对象的paramMap字段转换为json格式
     */
    String DATA_FORMAT_JSON = "JSON";
    /**
     * 数据格式：对象的paramMap字段转换为xml格式
     */
    String DATA_FORMAT_XML = "XML";
    /**
     * 数据格式：对象的paramMap字段转换为k=v&k=v格式
     */
    String DATA_FORMAT_KV = "KV";

    /**
     * POST请求的数据提交方式，表单形式提交
     */
    String POST_DATA_MODE_FORM = "FORM";
    /**
     * POST请求的数据提交方式，流的形式提交
     */
    String POST_DATA_MODE_STREAM = "STREAM";

    /**
     * 接口名称
     */
    String name() default "";

    /**
     *
     */
    Class clazz() default void.class;

    /**
     * 接口访问地址或者配置url对应的key
     */
    String url() default "";

    /**
     * 接口访问的相对路径，适合于input入参中的url字段+path组成完成的接口地址
     */
    String path() default "";

    /**
     * 请求方式
     */
    HttpMethod method() default HttpMethod.POST;

    /**
     * 编码格式
     */
    String charset() default "UTF-8";

    /**
     * 连接主机超时（单位：毫秒）默认10秒
     */
    int connectTimeOut() default 5 * 1000;

    /**
     * 设置从主机读取数据超时（单位：毫秒）默认10秒
     */
    int readTimeOut() default 5 * 1000;

    /**
     * URL上参数的构建模式，包含格式化模式 (FORMAT)、拼接模式(JOIN)<br>
     * 注：FORMAT模式下，会将url参数根据TreeMap本身特性按照key升序排列,然后格式化到url上<br>
     * 注：GET请求参数是附加在url上的
     */
    String urlParamBuildMode() default URL_PARAM_JOIN_MODE;

    /**
     * 数据来源，表示用于构建指定格式字符串的参数数据来源,默认对象本身<br>
     * 包含两种来源：一种是对象本身转为指定格式，一种是通过WechatApiInput.paramMap字段内容转为指定格式<br>
     */
    String dataFrom() default DATA_FROM_OBJECT_SELF;

    /**
     * 数据提交方式（适用于POST请求）
     * <p>
     * FORM 表单形式提交（默认），STREAM 流的形式提交
     */
    String postDataMode() default POST_DATA_MODE_FORM;

    /**
     * 数据格式（适用于POST请求）
     * <p>
     * 支持 JSON（默认）、XML、KV格式
     * 注：以流的形式提交才会使用到该字段
     */
    String postDataFormat() default DATA_FORMAT_JSON;
}
