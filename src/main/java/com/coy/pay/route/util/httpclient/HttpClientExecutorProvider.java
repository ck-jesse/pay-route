package com.coy.pay.route.util.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * HttpClient 连接池Executor提供者
 *
 * @author chenck
 * @date 2017年5月25日 下午5:49:31
 */
public class HttpClientExecutorProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientExecutorProvider.class);

    // 同一配置, 始终保持同一个连接,注：生产者和消费者均始终保持各自的同一个连接
    private static final ConcurrentHashMap<String, Executor> EXECUTOR_MAP = new ConcurrentHashMap<String, Executor>();
    // 锁, 防止重复连接同一配置
    private static final Object LOCK = new Object();

    /**
     * 获取HttpClient连接池Executor实例
     *
     * @param reqParam
     * @param reqConfig
     * @return
     * @author chenck
     * @date 2017年5月25日 下午5:54:07
     */
    public static Executor getInstance(HttpClientParam reqParam, RequestConfig reqConfig) {
        if (null == reqParam) {
            throw new IllegalArgumentException("HttpClientParam should not be null");
        }
        if (null == reqConfig) {
            throw new IllegalArgumentException("RequestConfig should not be null");
        }
        // 针对不同的PoolName实例化不同的Executor
        String cachedKey = reqParam.getPoolName();

        Executor executor = EXECUTOR_MAP.get(cachedKey);
        if (null == executor) {
            synchronized (LOCK) {
                executor = EXECUTOR_MAP.get(cachedKey);
                if (null != executor) {
                    return executor;
                }
                // 通过自定义HttpClient设置自定义连接池
                HttpClient httpClient = createHttpClient(reqParam, reqConfig);
                executor = Executor.newInstance(httpClient);
                EXECUTOR_MAP.putIfAbsent(cachedKey, executor);
            }
        }
        return executor;
    }

    /**
     * 创建HttpClient连接池
     *
     * @param
     * @return
     * @author chenck
     * @date 2018/4/14 20:18
     */
    private static PoolingHttpClientConnectionManager createPool(HttpClientParam reqParam, RequestConfig reqConfig) {
        LayeredConnectionSocketFactory ssl = null;
        try {
            ssl = SSLConnectionSocketFactory.getSystemSocketFactory();
        } catch (final SSLInitializationException ex) {
            final SSLContext sslcontext;
            try {
                sslcontext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
                sslcontext.init(null, null, null);
                ssl = new SSLConnectionSocketFactory(sslcontext);
            } catch (final SecurityException ignore) {
            } catch (final KeyManagementException ignore) {
            } catch (final NoSuchAlgorithmException ignore) {
            }
        }

        final Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", ssl != null ? ssl : SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(sfr);
        connMgr.setDefaultMaxPerRoute(reqParam.getMaxConnPerRoute());// 单路由的最大并发连接数
        connMgr.setMaxTotal(reqParam.getMaxConnTotal());// 最大连接数
        connMgr.setValidateAfterInactivity(reqParam.getValidateAfterInactivity());// 空闲的永久连接检查间隔
        return connMgr;
    }

    /**
     * 创建HttpClient
     *
     * @param reqParam
     * @param reqConfig
     * @return
     * @author chenck
     * @date 2017年5月25日 下午5:53:58
     */
    private static HttpClient createHttpClient(HttpClientParam reqParam, RequestConfig reqConfig) {
        // custom HttpClient
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setDefaultRequestConfig(reqConfig)
                .setMaxConnTotal(reqParam.getMaxConnTotal())
                .setMaxConnPerRoute(reqParam.getMaxConnPerRoute());

        // RequestConfig.Builder.setProxy也可显示设置代理【此方式设置代理优先使用】
        // 注：外层设置代理到环境变量中时，针对不同请求协议（http/https）需分别配置
        // http.proxyHost和http.proxyPort
        // https.proxyHost和https.proxyPort
        httpClientBuilder.useSystemProperties();

        // 连接池
        PoolingHttpClientConnectionManager connMgr = createPool(reqParam, reqConfig);
        httpClientBuilder.setConnectionManager(connMgr);

        // 问题：从连接池中获取的连接已失效，导致请求服务端失败。
        // 分析：如果服务端针对长连接有设置过期策略，那么当服务端连接已关闭时，客户端是无法检测到这个状态变化而及时关闭连接的。这就造成了线程从连接池中获取的连接不一定是有效的。
        // 方案：客户端设置连接的有效时间，然后定期清除过期和闲置的的连接
        // 注：清除连接池中过期和失效的连接，HttpClientBuilder.build()方法中的IdleConnectionEvictor线程有支持清除，可不用自己实现（设置对应的参数即可）
        final int connKeepAliveTime = reqParam.getConnKeepAliveTime();
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                // keep-alive默认策略为连接永久有效，如果keep-alive值没有由服务器明确设置，那么将连接的存活时间设置为指定时长
                if (keepAlive == -1) {
                    keepAlive = connKeepAliveTime;
                }
                return keepAlive;
            }
        });

        // 定期清除过期和闲置的的连接（第一次延迟3秒执行，每10秒执行一次）
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory(reqParam.getPoolName() + "_conn-monitor"));
        scheduler.scheduleAtFixedRate(new HttpClientConnectionMonitor(connMgr, connKeepAliveTime), 3000, 10 * 1000, TimeUnit.MILLISECONDS);

        // retry execution count
        final int retryExecutionCount = reqParam.getRetryExecutionCount();
        if (retryExecutionCount > 0) {
            httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException exception, int executionCount,
                                            HttpContext context) {
                    // 限制重试次数
                    if (executionCount > retryExecutionCount) {
                        return false;
                    }
                    if (exception instanceof NoHttpResponseException) {
                        LOGGER.info("[NoHttpResponseException has retry request:"
                                + context.toString() + "][executionCount:" + executionCount + "]");
                        return true;
                    } else if (exception instanceof SocketException) {
                        LOGGER.info("[SocketException has retry request:" + context.toString()
                                + "][executionCount:" + executionCount + "]");
                        return true;
                    }
                    return false;
                }
            });
        }
        return httpClientBuilder.build();
    }
}
