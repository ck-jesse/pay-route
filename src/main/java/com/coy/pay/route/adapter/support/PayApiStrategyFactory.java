package com.coy.pay.route.adapter.support;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.api.PayApiStrategy;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 第三方支付api策略工厂
 *
 * @author chenck
 * @date 2018/4/10 22:15
 */
public class PayApiStrategyFactory {

    private static final Logger LOG = LoggerFactory.getLogger(PayApiStrategyFactory.class);

    /**
     * 第三方支付api策略配置集合，系统启动时加载
     */
    private static Map<String, Class<? extends PayApiStrategy>> STRATEGY_CONFIG_MAP = new ConcurrentHashMap<String, Class<? extends PayApiStrategy>>();

    /**
     * 第三方支付api策略对象集合，全局唯一
     * Map<支付通道Id_apiId, 策略对象>
     */
    private static final Map<String, PayApiStrategy> STRATEGY_INSTANCE_MAP = new ConcurrentHashMap<String, PayApiStrategy>();

    /**
     * 锁, 防止重复创建同一对象
     */
    private static final Object lock = new Object();

    public static final String DELIMITER = "@";

    static {
        loadStrategyClass();
    }

    /**
     * 加载策略Class
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 19:39
     */
    public static void loadStrategyClass() {
        // 获取策略接口下所有子类Class
        List<Class> strategyClassList = ClassUtil.getAllSubClass(PayApiStrategy.class);

        // 加载所有第三方支付api的策略配置
        ThirdPayApiAnno payApiAnno = null;
        String key = null;
        for (Class strategyClass : strategyClassList) {
            payApiAnno = (ThirdPayApiAnno) strategyClass.getAnnotation(ThirdPayApiAnno.class);
            if (null == payApiAnno) {
                continue;
            }
            PayPassIdEnum[] payPassIds = payApiAnno.payPassId();
            // 将一个策略上配置的多个payPassId处理多个支付通道与策略的映射
            for (PayPassIdEnum passId : payPassIds) {
                // 按照指定格式拼接key
                key = getKey(passId.getValue(), payApiAnno.apiId().getValue());

                if (STRATEGY_CONFIG_MAP.containsKey(key)) {
                    continue;
                }
                STRATEGY_CONFIG_MAP.put(key, strategyClass);
            }
        }
    }

    public static String getKey(String payPassId, String apiId) {
        return payPassId + DELIMITER + apiId;
    }

    /**
     * 获取第三方支付api策略实例
     * <p>
     * 注：此处有单例模式（容器式单利），工厂模式的影子
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 19:51
     */
    public static PayApiStrategy getInstance(String payPassId, String apiId) {
        String key = getKey(payPassId, apiId);
        PayApiStrategy strategy = STRATEGY_INSTANCE_MAP.get(key);
        if (null != strategy) {
            return strategy;
        }
        synchronized (lock) {
            strategy = STRATEGY_INSTANCE_MAP.get(key);
            if (null != strategy) {
                return strategy;
            }

            if (STRATEGY_CONFIG_MAP.size() == 0) {
                loadStrategyClass();
            }

            Class<? extends PayApiStrategy> adapterClass = STRATEGY_CONFIG_MAP.get(key);
            if (null == adapterClass) {
                throw new PayApiException(PayApiError.ERR_CONFIG, "暂不支持该api功能（未找到第三方支付api服务 " + key + "）");
            }

            try {
                strategy = adapterClass.newInstance();
            } catch (Exception e) {
                LOG.error("初始化第三方支付api服务 " + key + " 异常", e);
                throw new PayApiException(PayApiError.ERR_APP, "初始化第三方支付api服务 " + key + " 异常");
            }
            if (null != strategy) {
                STRATEGY_INSTANCE_MAP.put(key, strategy);
            }
        }
        return strategy;
    }

}
