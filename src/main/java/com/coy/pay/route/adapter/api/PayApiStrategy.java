package com.coy.pay.route.adapter.api;

import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.dto.base.PayApiResult;

/**
 * 第三方支付api策略接口
 * 注：在策略类中完成业务层统一参数和支付API参数的适配，所以每一个策略类同时也是一个适配器。
 * <p>
 * 注意：通过泛型来限定入参和出参的类型，保证入参和出参对象的一致性
 *
 * @author chenck
 * @date 2018/8/23 13:53
 */
public interface PayApiStrategy<T extends PayApiInput<R>, R extends PayApiResult> {

    /**
     * 调用支付api并适配
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 14:17
     */
    public R call(T input);
}
