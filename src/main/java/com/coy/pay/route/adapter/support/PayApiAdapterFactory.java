package com.coy.pay.route.adapter.support;

import com.coy.pay.route.adapter.annotation.ThirdPayApiAnno;
import com.coy.pay.route.adapter.api.PayApiAdapter;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;
import com.coy.pay.route.adapter.type.PayPassIdEnum;
import com.coy.pay.route.exception.PayApiError;
import com.coy.pay.route.exception.PayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 第三方支付api适配器工厂
 *
 * @author chenck
 * @date 2018/4/10 22:15
 */
public class PayApiAdapterFactory {

    private static final Logger LOG = LoggerFactory.getLogger(PayApiAdapterFactory.class);

    /**
     * 第三方支付api适配器配置集合，系统启动时加载
     */
    private static Map<String, Class<? extends PayApiAdapter>> ADAPTER_CONFIG_MAP = new ConcurrentHashMap<String, Class<? extends PayApiAdapter>>();

    /**
     * 第三方支付api适配器对象集合，全局唯一
     * Map<支付通道Id_apiId, 适配器对象>
     */
    private static final Map<String, PayApiAdapter> ADAPTER_INSTANCE_MAP = new ConcurrentHashMap<String, PayApiAdapter>();

    /**
     * 锁, 防止重复创建同一对象
     */
    private static final Object lock = new Object();

    public static final String DELIMITER = "@";

    static {
        loadAdapterClass();
    }

    /**
     * 加载适配器Class
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 19:39
     */
    public static void loadAdapterClass() {
        // 获取适配器接口下所有子类Class
        List<Class> adapterClassList = ClassUtil.getAllSubClass(PayApiAdapter.class);

        // 加载所有第三方支付api的适配器配置
        ThirdPayApiAnno payApiAnno = null;
        String key = null;
        for (Class adapterClass : adapterClassList) {
            payApiAnno = (ThirdPayApiAnno) adapterClass.getAnnotation(ThirdPayApiAnno.class);
            if (null == payApiAnno) {
                continue;
            }
            PayPassIdEnum[] payPassIds = payApiAnno.payPassId();
            // 将一个适配器上配置的多个payPassId处理多个支付通道与适配器的映射
            for (PayPassIdEnum passId : payPassIds) {
                // 按照指定格式拼接key
                key = getKey(passId.getValue(), payApiAnno.apiId().getValue());

                if (ADAPTER_CONFIG_MAP.containsKey(key)) {
                    continue;
                }
                ADAPTER_CONFIG_MAP.put(key, adapterClass);
            }
        }
    }

    public static String getKey(String payPassId, String apiId) {
        return payPassId + DELIMITER + apiId;
    }

    /**
     * 获取第三方支付api适配器实例
     * <p>
     * 注：此处有单利模式（容器式单利），工厂模式的影子
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/8/23 19:51
     */
    public static PayApiAdapter getInstance(String payPassId, String apiId) {
        String key = getKey(payPassId, apiId);
        PayApiAdapter adapter = ADAPTER_INSTANCE_MAP.get(key);
        if (null != adapter) {
            return adapter;
        }
        synchronized (lock) {
            adapter = ADAPTER_INSTANCE_MAP.get(key);
            if (null != adapter) {
                return adapter;
            }

            if (ADAPTER_CONFIG_MAP.size() == 0) {
                loadAdapterClass();
            }

            Class<? extends PayApiAdapter> adapterClass = ADAPTER_CONFIG_MAP.get(key);
            if (null == adapterClass) {
                throw new PayApiException(PayApiError.ERR_CONFIG, "暂不支持该api功能（未找到第三方支付api服务 " + key + "）");
            }

            try {
                adapter = adapterClass.newInstance();
            } catch (Exception e) {
                LOG.error("初始化第三方支付api服务 " + key + " 异常", e);
                throw new PayApiException(PayApiError.ERR_APP, "初始化第三方支付api服务 " + key + " 异常");
            }
            if (null != adapter) {
                ADAPTER_INSTANCE_MAP.put(key, adapter);
            }
        }
        return adapter;
    }


    public static void main(String[] args) {

        final String payPassId = PayPassIdEnum.SWT_SPDB.getValue();
        final String apiId = CustomApiIdEnum.TRADE_QUERY.getValue();
//        apiId = CustomApiIdEnum.PAY_WEIXIN_JSPAY.getValue();

//        IThirdPayApiAdapter payApiAdapter = ThirdPayApiAdapterProvider.getInstance(payPassId, apiId);
//        System.out.println(payApiAdapter.getClass());

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PayApiAdapter payApiAdapter = PayApiAdapterFactory.getInstance(payPassId, apiId);
                    System.out.println(Thread.currentThread().getName() + " " + payApiAdapter);
                }
            }, "thread-" + i).start();
        }
    }

}
