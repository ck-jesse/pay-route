package com.coy.pay.route.util.httpclient;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * HttpClient连接池中的连接监控，用于清除过期和闲置的的连接
 *
 * @author chenck
 * @date 2018/4/14 19:40
 */
public class HttpClientConnectionMonitor implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientConnectionMonitor.class);

    PoolingHttpClientConnectionManager poolConnMgr;
    int connKeepAliveTime;

    public HttpClientConnectionMonitor(PoolingHttpClientConnectionManager poolConnMgr, int connKeepAliveTime) {
        this.poolConnMgr = poolConnMgr;
        this.connKeepAliveTime = connKeepAliveTime;
    }

    @Override
    public void run() {
        try {
            LOGGER.debug("httpclient pool release start connect count={}", poolConnMgr.getTotalStats().getAvailable());

            // Close expired connections
            poolConnMgr.closeExpiredConnections();

            // Optionally, close connections that have been idle longer than connKeepAliveTime MILLISECONDS
            // 空闲时间从交还给连接管理器时开始，不管是否已过期超过空闲时间则关闭。所以Idle时间应该设置的尽量长一点。
            poolConnMgr.closeIdleConnections(connKeepAliveTime, TimeUnit.MILLISECONDS);

            LOGGER.debug("httpclient pool release end connect count={}", poolConnMgr.getTotalStats().getAvailable());

        } catch (Exception e) {
            LOGGER.error("httpclient pool release error", e);
        }
    }
}
