package com.coy.pay.route.util.httpclient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.Args;

import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient参数对象<br>
 * 注:可按照HttpClient的RequestConfig.Builder设置参数来配置请求相关属性<br>
 * 支持链式调用<br>
 * <p>
 * connectTimeout：表示建立连接的timeout时间<br>
 * socketTimeout：表示数据传输处理时间<br>
 * connectionRequestTimeout：表示从连接池中取连接的timeout时间<br>
 *
 * @author chenck
 * @date 2017年4月27日 上午11:42:16
 */
@Data
@Accessors(chain = true)// 生成支持链式调用setter方法
@NoArgsConstructor(staticName = "of")// 生成一个无参数的私有构造方法，并生成一个对应的静态构造方法
@RequiredArgsConstructor(staticName = "of")// 生成一个有参数的私有构造方法，并生成一个对应的静态构造方法（参数结合lombok.NonNull一起使用）
public class HttpClientParam {

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_POOL_NAME = "defaultHttpClientExecutor";
    /**
     * 默认连接池名称
     */
    private String poolName = DEFAULT_POOL_NAME;
    /**
     * 字符集,默认UTF-8
     */
    private String charset = DEFAULT_CHARSET;
    /**
     * 请求uri(可带参数)
     */
    @NonNull
    private String requesturi;
    /**
     * HTTP请求方式,默认get
     */
    private String method = HttpMethod.GET.name();
    /**
     * 请求参数内容
     */
    private HttpEntity entity;
    /**
     * 表示连接池最大并发连接数,默认300
     */
    private int maxConnTotal = 300;
    /**
     * 表示单路由的最大并发连接数,默认150<br>
     * 假设你的业务系统需要调用A和B这两个外部系统的http接口，那么如果DefaultMaxPerRout=100，那么调用A系统的http接口时，
     * 最大并发数就是100。
     */
    private int maxConnPerRoute = 150;
    /**
     * 重试执行次数,默认0
     */
    private int retryExecutionCount = 1;
    /**
     * 连接空闲超时时间(即连接保持时间),单位毫秒
     * 注：
     */
    private int connKeepAliveTime = 1 * 60 * 1000;
    /**
     * 空闲的永久连接检查间隔
     * 注：官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查
     * 对非活动的永久连接，每隔validateAfterInactivity毫秒（默认2s）做一次链路检查，尽量确保在使用的时候是可用的
     */
    private int validateAfterInactivity = 1000;
    /**
     * 输出文件路径
     */
    private String outFilePath;

    /**
     * Cookie设置<br>
     * 注：domain的设置需要与requesturi中的域名一致，否则存在跨域cookie写不进去的问题<br>
     */
    private CookieStore cookieStore;
    /**
     * 设置请求header,可设置Accept,User-Agent,Referer,Cookie等
     */
    private List<Header> headers;
    /**
     * 响应处理器
     */
    private ResponseHandler<Object> responseHandler;

    /* 通过lombok来实现，更加简洁
    public static HttpClientParam of() {
        return new HttpClientParam();
    }

    protected HttpClientParam() {
        super();
        this.headers = new ArrayList<Header>(16);
    }*/

    // ------------ custom shortcut method

    /**
     * 注：domain的设置需要与requesturi中的域名一致，否则存在跨域cookie写不进去的问题<br>
     */
    public HttpClientParam setClientCookie(ClientCookie clientCookie) {
        if (null == this.cookieStore) {
            this.cookieStore = new BasicCookieStore();
        }
        cookieStore.addCookie(clientCookie);
        return this;
    }

    /**
     * 注：domain的设置需要与requesturi中的域名一致，否则存在跨域cookie写不进去的问题<br>
     */
    public HttpClientParam setClientCookie(String name, String value, String domain, String path) {
        Args.notNull(name, "Cookie name");
        Args.notNull(domain, "Cookie domain");
        if (null == this.cookieStore) {
            this.cookieStore = new BasicCookieStore();
        }
        if (StringUtils.isBlank(path)) {
            path = "/";
        }
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookieStore.addCookie(cookie);
        return this;
    }

    /**
     * 注：domain的设置需要与requesturi中的域名一致，否则存在跨域cookie写不进去的问题<br>
     */
    public HttpClientParam setClientCookie(String name, String value, String domain) {
        setClientCookie(name, value, domain, "/");
        return this;
    }

    /**
     * 设置请求header,可设置Accept,User-Agent,Referer,Cookie等
     */
    public HttpClientParam setHeader(Header header) {
        Args.notNull(header, "Header");
        if (this.headers == null) {
            this.headers = new ArrayList<Header>(16);
        }
        this.headers.add(header);
        return this;
    }

    /**
     * 设置请求header,可设置Accept,User-Agent,Referer,Cookie等
     */
    public HttpClientParam setHeader(String name, String value) {
        Args.notNull(name, "Header name");
        setHeader(new BasicHeader(name, value));
        return this;
    }
}
