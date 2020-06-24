package com.coy.pay.route.util.httpclient;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 结果dto
 *
 * @Author chenck
 * @Date 2019/1/18 11:20
 */
@Data
public class HttpResultDto {

    /**
     * 请求成功
     */
    public final static String SUCC = "T";
    /**
     * 请求失败
     */
    public final static String FAIL = "F";
    /**
     * 超时（含连接超时、读取超时两种情况）
     */
    public final static String TIMEOUT = "timeout";

    /**
     * 响应内容输出到文件<br>
     * 当bizRetCode字段值为response_output_to_file时，表示本次请求的响应内容输出到文件，否则，
     * 将响应内容设置到bizDataObj并返回
     */
    public final static String RESPONSE_OUTPUT_TO_FILE = "response_output_to_file";

    /**
     * 返回码，默认为false
     */
    private String retCode = SUCC;

    /**
     * 返回描述信息
     */
    private String retMsg = "ok";

    /**
     * 具体业务的返回码
     */
    private String bizRetCode;

    /**
     * 具体业务的描述信息
     */
    private String bizRetMsg;

    /**
     * 具体业务返回的数据
     */
    private Object bizDataObj;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * token
     */
    private String token;

    /**
     * 代理IP
     */
    private String proxyIp;

    /**
     * 响应头属性
     */
    Map<String, List<String>> headerFields;

    /**
     * 判断处理结果
     *
     * @return true表示成功，false表示失败
     */
    public boolean isSuccess() {
        return checkRetCode(SUCC);
    }

    public boolean isFail() {
        return checkRetCode(FAIL);
    }

    /**
     * 判断是否超时（含连接超时、读取超时两种情况）
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/18 11:20
     */
    public boolean isTimeout() {
        return checkRetCode(TIMEOUT);
    }

    /**
     * 校验返回码
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/18 11:20
     */
    public boolean checkRetCode(String retCode) {
        if (retCode.equals(getRetCode())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 响应内容是否输出到文件
     *
     * @Param
     * @Author chenck
     * @Date 2019/1/18 11:21
     */
    public boolean isOutputToFile() {
        if (RESPONSE_OUTPUT_TO_FILE.equals(getBizRetCode())) {
            return true;
        } else {
            return false;
        }
    }


    public Object getBizDataObj() {
        if (null == bizDataObj) {
            return "";
        }
        return bizDataObj;
    }

    @Override
    public String toString() {
        if (null != this.bizDataObj) {
            return this.bizDataObj.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[retCode=").append(this.retCode).append(",");
        sb.append("retMsg=").append(this.retMsg).append("]");
        return sb.toString();
    }
}
