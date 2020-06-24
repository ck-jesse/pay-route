package com.coy.pay.route.util.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

/**
 * 该工具类基于HttpClient-4.5.3 Fluent API实现(使用线程池)<br>
 * 注：HttpClient低于4.5.3版本时可能会出现设置代理无效等问题<br>
 * 注：测试发现4.3.4版本就会出现代理设置无效问题<br>
 * <p>
 * 【HTTP 状态代码及其定义】<br>
 * 2XX：表示成功。<br>
 * 3XX：重定向。<br>
 * 4XX：客户机中出现的错误<br>
 * 5XX：服务器中出现的错误<br>
 * <p>
 * 502=>最常见的就是访问量过多，服务器承受不住<br>
 *
 * @author chenck
 * @date 2017年4月26日 下午8:13:51
 */
public class HttpClientUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private HttpClientUtil() {
    }

    /**
     * 执行GET方式请求
     *
     * @param requesturi
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:17:25
     */
    public static HttpResultDto doGet(String requesturi) {
        return HttpClientUtil.invoke(HttpClientParam.of(requesturi),
                RequestConfig.custom().build());
    }

    /**
     * 执行GET方式请求+代理设置
     *
     * @param requesturi
     * @param proxyHost
     * @param proxyPort
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:29:03
     */
    public static HttpResultDto doGet(String requesturi, String proxyHost, int proxyPort) {
        RequestConfig.Builder builder = RequestConfig.custom();
        if (StringUtils.isNotBlank(proxyHost) && proxyPort > 0) {
            builder.setProxy(new HttpHost(proxyHost, Integer.valueOf(proxyPort)));
        }
        return HttpClientUtil.invoke(HttpClientParam.of(requesturi),
                builder.build());
    }

    /**
     * 将参数以流的形式请求
     *
     * @param requesturi
     * @param reqData
     * @return
     * @author chenck
     * @date 2017年5月9日 下午3:37:51
     */
    public static HttpResultDto doPost(String requesturi, String reqData) {
        return HttpClientUtil.doPost(requesturi, reqData, null, 0);
    }

    /**
     * 将参数以表单的形式请求
     *
     * @param requesturi
     * @param reqDataList
     * @return
     * @author chenck
     * @date 2017年5月9日 下午3:50:40
     */
    public static HttpResultDto doPost(String requesturi, List<BasicNameValuePair> reqDataList) {
        return HttpClientUtil.doPost(requesturi, reqDataList, null, 0);
    }

    /**
     * 将参数以流的形式请求+代理设置
     *
     * @param requesturi
     * @param reqData
     * @param proxyHost
     * @param proxyPort
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:07:30
     */
    public static HttpResultDto doPost(String requesturi, String reqData, String proxyHost,
                                       int proxyPort) {
        HttpEntity entity = null;
        if (StringUtils.isNotBlank(reqData)) {
            // Content-type = text/plain
            entity = new StringEntity(reqData, Consts.UTF_8);
        }
        return HttpClientUtil.doPost(requesturi, entity, proxyHost, proxyPort);
    }

    /**
     * 将参数以表单的形式请求+代理设置
     *
     * @param requesturi
     * @param reqDataList
     * @param proxyHost
     * @param proxyPort
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:14:51
     */
    public static HttpResultDto doPost(String requesturi, List<BasicNameValuePair> reqDataList,
                                       String proxyHost, int proxyPort) {
        HttpEntity entity = null;
        if (null != reqDataList && reqDataList.size() > 0) {
            // Content-type = application/x-www-form-urlencoded
            entity = new UrlEncodedFormEntity(reqDataList, Consts.UTF_8);
        }
        return HttpClientUtil.doPost(requesturi, entity, proxyHost, proxyPort);
    }

    /**
     * 【简易】执行POST请求
     *
     * @param requesturi
     * @param entity
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:34:24
     */
    public static HttpResultDto doPost(String requesturi, HttpEntity entity) {
        return HttpClientUtil.doPost(requesturi, entity, null, 0);
    }

    /**
     * 【简易】执行POST请求+代理设置
     *
     * @param requesturi
     * @param entity
     * @param proxyHost
     * @param proxyPort
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:33:20
     */
    public static HttpResultDto doPost(String requesturi, HttpEntity entity, String proxyHost,
                                       int proxyPort) {
        HttpClientParam reqParam = HttpClientParam.of(requesturi)
                .setMethod(HttpMethod.POST.name());
        if (null != entity) {
            reqParam.setEntity(entity);
        }
        RequestConfig.Builder builder = RequestConfig.custom();
        if (StringUtils.isNotBlank(proxyHost) && proxyPort > 0) {
            builder.setProxy(new HttpHost(proxyHost, Integer.valueOf(proxyPort)));
        }
        return HttpClientUtil.invoke(reqParam, builder.build());
    }

    /**
     * 【简易】执行指定请求方式的请求
     *
     * @param reqParam
     * @return
     * @author chenck
     * @date 2017年5月9日 下午4:19:29
     */
    public static HttpResultDto invoke(HttpClientParam reqParam) {
        return HttpClientUtil.invoke(reqParam, RequestConfig.custom().build());
    }

    /**
     * 【基础】执行http和https请求<br>
     * 1.支持普通请求、上传文件、下载文件<br>
     * 2.优先启用设置的代理配置HttpHost，若未设置HttpHost，则取系统环境变量System.getProperty中代理配置<br>
     * 注：HttpEntity为StringEntity，则将参数以流的形式请求<br>
     * 注：HttpEntity为UrlEncodedFormEntity，则将参数以表单的形式请求<br>
     *
     * @param reqParam  请求参数
     * @param reqConfig 请求配置
     * @return
     * @author chenck
     * @date 2017年4月27日 下午4:09:48
     */
    public static HttpResultDto invoke(HttpClientParam reqParam, RequestConfig reqConfig) {
        HttpResultDto httpResultDto = new HttpResultDto();
        Request request = null;
        try {
            Args.notBlank(reqParam.getRequesturi(), "request uri");

            if (null == reqConfig) {
                reqConfig = RequestConfig.custom().build();
            }

            // get request by http method
            String requesturi = reqParam.getRequesturi().trim();
            if (reqParam.getMethod().equals(HttpMethod.POST.name())) {
                request = Request.Post(requesturi);
                // only post set request body
                if (null != reqParam.getEntity()) {
                    request.body(reqParam.getEntity());
                }
            } else if (reqParam.getMethod().equals(HttpMethod.PUT.name())) {
                request = Request.Put(requesturi);
            } else if (reqParam.getMethod().equals(HttpMethod.DELETE.name())) {
                request = Request.Delete(requesturi);
            } else if (reqParam.getMethod().equals(HttpMethod.TRACE.name())) {
                request = Request.Trace(requesturi);
            } else {
                request = Request.Get(requesturi);
            }

            // set request headers
            List<Header> headers = reqParam.getHeaders();
            if (null != headers && headers.size() > 0) {
                for (Header header : headers) {
                    request.addHeader(header);
                }
            }
            // set timeout
            request.connectTimeout(reqConfig.getConnectTimeout());
            request.socketTimeout(reqConfig.getSocketTimeout());

            // set proxy
            if (null != reqConfig.getProxy()) {
                request.viaProxy(reqConfig.getProxy());
            }

            // 获取HttpClient连接池Executor实例
            Executor executor = HttpClientExecutorProvider.getInstance(reqParam, reqConfig);

            // set cookie
            if (null != reqParam.getCookieStore()) {
                executor.use(reqParam.getCookieStore());
            }

            // 所有请求会使用一个公共的连接池，而且内容会立刻全部读出然后关闭inputsream，不需要再用代码去关闭。
            Response response = executor.execute(request);

            HttpResponse httpResponse = response.returnResponse();
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() < 200 || statusLine.getStatusCode() >= 400) {
                httpResultDto.setRetCode(HttpResultDto.FAIL);
                httpResultDto.setRetMsg(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                return httpResultDto;
            }

            HttpEntity entity = httpResponse.getEntity();
            String type = ContentType.getOrDefault(entity).getMimeType();

            // ContentType为如下类型时，将响应内容作为字符串返回
            if (ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(type)
                    || ContentType.APPLICATION_XML.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_HTML.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_PLAIN.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_XML.getMimeType().equalsIgnoreCase(type)) {

                Charset charset = StringUtils.isNotBlank(reqParam.getCharset()) ? Charset.forName(reqParam.getCharset()) : null;
                String responseStr = EntityUtils.toString(entity, charset);

                // 接口方的响应数据编码格式可能为ISO-8859-1和UTF-8（如大数据api），所以此处转为请求的编码格式(如默认编码格式UTF-8)，避免中文被编码的情况
                ContentType contentType = ContentType.get(entity);
                if (contentType != null && contentType.getCharset() != null && "ISO-8859-1".equals(contentType.getCharset().toString())) {
                    responseStr = new String(responseStr.getBytes("ISO-8859-1"), charset);
                }
                // 销毁
                EntityUtils.consumeQuietly(entity);

                LOGGER.info("[HttpClientUtil.invoke][" + type + "][success]" + responseStr);
                httpResultDto.setBizDataObj(responseStr);
                return httpResultDto;
            }

            // get output file path
            String outFilePath = reqParam.getOutFilePath();
            if (StringUtils.isBlank(outFilePath)) {
                String fileName = HttpClientUtil.getFileName(httpResponse);
                if (StringUtils.isBlank(fileName)) {
                    fileName = UUID.randomUUID().toString();
                }
                outFilePath = System.getProperty("java.io.tmpdir") + fileName;
            }
            LOGGER.info("[HttpClientUtil.invoke][" + type + "][output file]" + outFilePath);

            HttpClientUtil.outputFile(outFilePath, entity.getContent());

            httpResultDto.setSessionId(HttpClientUtil.getCookie(httpResponse));// 将响应对象中的Cookie内容写入sessionId
            httpResultDto.setBizRetCode(HttpResultDto.RESPONSE_OUTPUT_TO_FILE);// 标志响应内容输出到文件
            httpResultDto.setBizDataObj(outFilePath);// 文件路径
        } catch (SocketTimeoutException e) {
            // 超时（含连接超时、读取超时两种情况）
            httpResultDto.setRetCode(HttpResultDto.TIMEOUT);
            httpResultDto.setRetMsg(e.getMessage());
        } catch (Exception e) {
            httpResultDto.setRetCode(HttpResultDto.FAIL);
            httpResultDto.setRetMsg(e.getMessage());
            LOGGER.error("[HttpClientUtil.invoke][" + reqParam.getMethod() + "]["
                    + reqParam.getRequesturi() + "] request exception", e);
        }
        return httpResultDto;
    }

    /**
     * 从HttpResponse中获取文件名称
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/18 11:21
     */
    public static String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            for (HeaderElement headerElement : values) {
                NameValuePair param = headerElement.getParameterByName("filename");
                if (param != null) {
                    try {
                        // filename = URLDecoder.decode(param.getValue(), "utf-8");
                        filename = param.getValue();
                        break;
                    } catch (Exception e) {
                        LOGGER.error("[HttpClientUtil.getFileName]", e);
                    }
                }
            }
        }
        return filename;
    }

    public static String getCookie(HttpResponse response) {
        Header setCookie = response.getFirstHeader("Set-Cookie");
        String cookie = null;
        if (setCookie != null) {
            cookie = setCookie.getValue();
        }
        return cookie;
    }

    /**
     * 输出文件
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/18 11:21
     */
    public static void outputFile(String filePath, InputStream is)
            throws IOException, FileNotFoundException {
        File outFile = new File(filePath);
        if (outFile.exists()) {
            outFile.delete();
        }
        File parentFile = outFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();

            //给文件加权限(Nginx分配，多系统账号返回有权限问题)
            parentFile.setExecutable(true, false);
            parentFile.setReadable(true, false);
            parentFile.setWritable(true, false);
        }
        outFile.createNewFile();
        parentFile.setExecutable(false, false);
        parentFile.setReadable(true, false);
        parentFile.setWritable(true, false);

        // 输出文件
        FileOutputStream os = new FileOutputStream(outFile);
        byte[] byteArr = new byte[512];
        int len = 0;
        while ((len = is.read(byteArr)) != -1) {
            os.write(byteArr, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

}
