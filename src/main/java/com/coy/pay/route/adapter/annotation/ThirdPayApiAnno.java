package com.coy.pay.route.adapter.annotation;

import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 第三方支付api 注解
 *
 * @author chenck
 * @date 2018/8/23 16:55
 */
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ThirdPayApiAnno {

    /**
     * 支付通道类型(支持配置多个支付通道对应同一个适配器协议)
     *
     * @return
     * @author chenck
     * @date 2018/8/23 16:58
     */
    PayPassIdEnum[] payPassId();

    /**
     * 自定义api标识
     *
     * @return
     * @author chenck
     * @date 2018/8/23 16:58
     */
    CustomApiIdEnum apiId();

}
