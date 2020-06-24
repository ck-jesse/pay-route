package com.coy.pay.route.util.httpclient.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * api基础入参
 *
 * @Author chenck
 * @Date 2019/1/18 11:33
 */
@Data
@Accessors(chain = true)
public class HttpParamApiInput<T extends HttpApiResult> extends HttpApiInput<T> {

    /**
     * url参数Map，用于url参数按照k=v拼接模式(JOIN)构建
     */
    @JSONField(serialize = false)
    protected Map<String, Object> urlParamMap;

    /**
     * url参数List，用于url参数按照格式化模式(FORMAT)构建<br/>
     * 按照格式化方式构建，如https://test.com/page?appid={0}&code={1}<br>
     */
    @JSONField(serialize = false)
    protected List<Object> urlParamList;

    /**
     * 请求参数Map，用于将参数构建为指定格式的字符串，适用于POST请求方式
     */
    @JSONField(serialize = false)
    protected Map<String, Object> paramMap;

    /**
     * 外部传入的请求参数字符串，无需再做数据格式转换，适用于POST请求方式
     */
    @JSONField(serialize = false)
    protected String paramStr;

    /**
     * 当api为下载文件时，该参数表示下载文件的存放路径（文件保存的全路径）
     */
    @JSONField(serialize = false)
    protected String outFilePath;


    @JSONField(serialize = false)
    public Map<String, Object> getUrlParamMap() {
        if (null == urlParamMap) {
            urlParamMap = new TreeMap<String, Object>();
        }
        return urlParamMap;
    }

    @JSONField(serialize = false)
    public List<Object> getUrlParamList() {
        if (null == urlParamList) {
            urlParamList = new ArrayList<Object>();
        }
        return urlParamList;
    }


    @JSONField(serialize = false)
    public Map<String, Object> getParamMap() {
        if (null == paramMap) {
            paramMap = new TreeMap<String, Object>();
        }
        return paramMap;
    }

    /**
     * 添加URL参数到Map
     */
    @JSONField(serialize = false)
    public HttpParamApiInput<T> addUrlParam(String key, Object value) {
        this.getUrlParamMap().put(key, value);
        return this;
    }

    /**
     * 添加URL参数到List
     */
    @JSONField(serialize = false)
    public HttpParamApiInput<T> addUrlParam(Object value) {
        this.getUrlParamList().add(value);
        return this;
    }

    /**
     * 添加参数到paramMap以便格式化为指定格式(如JSON)的参数
     */
    @JSONField(serialize = false)
    public HttpParamApiInput<T> addParam(String key, Object value) {
        this.getParamMap().put(key, value);
        return this;
    }

}
