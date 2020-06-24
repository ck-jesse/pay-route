package com.coy.pay.route.adapter.api;

import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 第三方支付api策略接口（同时也是适配器）
 * 注：在实现类中对第三方支付各自的api入参和出参进行适配并调动
 * <p>
 * 注意：通过泛型来限定入参和出参的类型，保证入参和出参对象的一致性
 *
 * @author chenck
 * @date 2018/8/23 13:53
 */
public interface PayApiAdapter<T extends PayApiInput<R>, R extends PayApiResult> {

    /**
     * 第三方支付api适配并调用
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 14:17
     */
    public R call(T input);
}
