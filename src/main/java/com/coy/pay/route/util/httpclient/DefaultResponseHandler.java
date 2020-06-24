package com.coy.pay.route.util.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 默认响应处理器
 * 
 * @author chenck
 * @date 2017年4月27日 下午3:22:23
 */
public class DefaultResponseHandler implements ResponseHandler<String> {

    @Override
    public String handleResponse(HttpResponse response)
            throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }

        if (null == entity) {
            throw new ClientProtocolException("Response contains no content");
        }
        return EntityUtils.toString(entity, Charset.forName("UTF-8"));
    }

}
