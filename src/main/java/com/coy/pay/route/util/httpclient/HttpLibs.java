/**
 * HttpLibs.java
 * Copyright (c) 2015-8-24 by penggq
 *
 * @author 彭国卿
 * @version 1.0
 * @Description
 */
package com.coy.pay.route.util.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpLibs {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpLibs.class);

    /**
     * /
     **/
    public static final String MARK_SLASH = "/";

    /**
     * =
     **/
    public static final String MARK_EQUAL = "=";

    /**
     * -
     **/
    public static final String MARK_HYPHEN = "-";

    /**
     * _
     **/
    public static final String MARK_DASH = "_";

    /**
     * &
     **/
    public static final String MARK_AND = "&";

    /**
     * ,
     **/
    public static final String MARK_COMMA = ",";

    /**
     * .
     **/
    public static final String MARK_POINT = ".";

    /**
     * ?
     **/
    public static final String MARK_QUESTION = "?";

    /**
     * :
     **/
    public static final String MARK_COLON = ":";

    /**
     * |
     **/
    public static final String MARK_VERTICAL = "|";

    /**
     * EMPTY
     **/
    public static final String EMPTY = "";

    /**
     * UTF-8
     **/
    public static final String UTF_8 = "UTF-8";

    /**
     * 组装请求URL<br>
     * 按照key=value拼接方式构建<br>
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/29 14:45
     */
    public static String buildRequestURL(Map<String, Object> params, String actionURL) {
        return buildRequestURL(params, null, actionURL);
    }

    /**
     * 组装请求URL<br>
     * 按照格式化方式构建，如https://test.com/page?appid={0}&code={1}<br>
     *
     * @author chenck
     * @date 2016年12月23日 下午8:12:36
     */
    public static String buildRequestURL(List<Object> params, String actionURL) {
        if (null != params && params.size() > 0) {
            return MessageFormat.format(actionURL, params.toArray());
        } else {
            return actionURL;
        }
    }

    /**
     * 组装url
     *
     * @param params    参数Map
     * @param keyList   拼装顺序,拼装的顺序<br>
     *                  如果为空，则按默认顺序组装Map中的所有参数<br>
     *                  如果不为空则按List中的顺序拼装
     * @param actionURL
     * @Author chenck
     * @Date 2019/1/29 14:45
     */
    public static String buildRequestURL(Map<String, Object> params, List<String> keyList,
                                         String actionURL) {
        String paramsLink = buildParamsLink(params, keyList);
        return buildRequestURL(paramsLink, actionURL);
    }

    /**
     * 组装url
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/29 14:45
     */
    public static String buildRequestURL(String params, String actionURL) {
        if (StringUtils.isBlank(actionURL)) {
            return "";
        }
        if (StringUtils.isBlank(params)) {
            return actionURL;
        }

        StringBuffer url = new StringBuffer(actionURL);

        if (actionURL.indexOf(MARK_QUESTION) < 0) { // 不存在?号
            url.append(MARK_QUESTION);
        } else if (!MARK_QUESTION.equals(actionURL.charAt(actionURL.length() - 1))) {// 判断最后一个字符是不是 ? 号
            url.append(MARK_AND);
        } else {
            url.append(MARK_QUESTION);
        }

        String res = url.append(params).toString();
        return res;
    }

    /**
     * 将URL返回的字符串解析成Map, 解析的字符串格式：a=a&b=b&c=c
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/29 14:45
     */
    public static Map<String, String> getUrlParamToMap(String urlParam) {
        if (StringUtils.isBlank(urlParam)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>(16);
        try {
            String[] params = urlParam.split(MARK_AND);
            for (String param : params) {
                String[] values = param.split(MARK_EQUAL, 2);
                String value = values.length == 2 ? values[1] : "";
                map.put(values[0], URLDecoder.decode(value, UTF_8));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("参数拼接异常：", e);
        }
        return map;
    }

    public static List<NameValuePair> getParamsList(String urlParam) {
        if (StringUtils.isBlank(urlParam)) {
            return new ArrayList<NameValuePair>();
        }

        List<NameValuePair> res = new ArrayList<NameValuePair>();
        String[] params = urlParam.split(MARK_AND);
        for (String param : params) {
            String[] values = param.split(MARK_EQUAL, 2);
            String value = values.length == 2 ? values[1] : "";
            res.add(new BasicNameValuePair(values[0], value));
        }
        return res;
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     *
     * @param paramsMap 参数集, 键/值对
     * @return NameValuePair参数集
     */
    public static List<NameValuePair> getParamsList(Map<String, Object> paramsMap) {
        return getParamsList(paramsMap, null);
    }

    public static List<NameValuePair> getParamsList(Map<String, Object> paramsMap,
                                                    List<String> keyList) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return null;
        }

        List<String> keys = null;
        if (keyList == null || keyList.size() == 0) {
            keys = new ArrayList<String>(paramsMap.keySet());
        } else {
            keys = keyList;
        }

        // 创建参数队列
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : keys) {
            String value = paramsMap.get(key).toString();
            params.add(new BasicNameValuePair(key, value));
        }

        return params;
    }

    /**
     * Map转换为String请求串
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/29 14:44
     */
    public static String buildParamsLink(Map<String, Object> paramMap) {
        return buildParamsLink(paramMap, null);
    }

    /**
     * 组装URL
     *
     * @Param params    参数Map
     * @Param keyList   拼装顺序,拼装的顺序, 如果为空，则按默认顺序组装Map中的所有参数, 如果不为空则按List中的顺序拼装
     * @Author chenck
     * @Date 2019/1/29 14:44
     */
    public static String buildParamsLink(Map<String, Object> params, List<String> keyList) {
        if (params == null || params.size() == 0) {
            return null;
        }

        List<String> keys = null;
        if (keyList == null || keyList.size() == 0) {
            keys = new ArrayList<String>(params.keySet());
        } else {
            keys = keyList;
        }

        List<String> res = new ArrayList<String>(keys.size());
        try {
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                Object value = params.get(key);
                // 过滤为空和为null的字段
                if (null == value || "".equals(value.toString().trim())) {
                    continue;
                }
                res.add(key + MARK_EQUAL + URLEncoder.encode(value.toString().trim(), UTF_8));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("组装URL异常：", e);
        }
        String url = StringUtils.join(res, MARK_AND);
        return url;
    }

}
