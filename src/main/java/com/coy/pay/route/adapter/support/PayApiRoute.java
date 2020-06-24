package com.coy.pay.route.adapter.support;

import com.coy.pay.route.adapter.api.PayApiStrategy;
import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.dto.base.PayApiResult;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支付路由 - 第三方支付api统一调用入口
 *
 * @author chenck
 * @date 2018/8/23 20:33
 */
public class PayApiRoute {

    private static final Logger LOG = LoggerFactory.getLogger(PayApiRoute.class);

    public static <T extends PayApiResult> T call(PayApiInput<T> input) {
        T result = null;
        try {
            if (StringUtils.isBlank(input.getPayPassId())) {
                throw new PayApiException(PayApiError.ERR_PARAM, "支付通道标识不能为空");
            }
            if (StringUtils.isBlank(input.getApiId())) {
                throw new PayApiException(PayApiError.ERR_PARAM, "支付api标识不能为空");
            }
            if (StringUtils.isBlank(PayPassIdEnum.getName(input.getPayPassId()))) {
                throw new PayApiException(PayApiError.ERR_PARAM, "暂不支持该支付通道（" + input.getPayPassId() + "）");
            }
            if (StringUtils.isBlank(CustomApiIdEnum.getName(input.getApiId()))) {
                throw new PayApiException(PayApiError.ERR_PARAM, "支付通道（" + input.getPayPassId() + "）暂不支持该api功能（" + input.getApiId() + "）");
            }

            // 根据支付通道标识和apiId 获取支付api策略
            PayApiStrategy payApiStrategy = PayApiStrategyFactory.getInstance(input.getPayPassId(), input.getApiId());

            result = (T) payApiStrategy.call(input);
        } catch (PayApiException e) {
            LOG.error("调用第三方支付api失败:", e.getCode() + e.getMsg());
            result = input.createResultObj();
            result.setCode(e.getCode());
            result.setMsg(e.getMsg());
        } catch (Exception e) {
            LOG.error(result.getMsg(), e);
            result = input.createResultObj();
            result.setCode(PayApiError.ERR_APP);
            result.setMsg("调用第三方支付异常");
        }
        return result;
    }


}
