package com.coy.pay.route.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果码
 * 建议：接口中增加一个标识业务系统的字段，可快速区分错误来源，然后定义统一错误码，当然特殊业务场景的错误码由业务系统自信定义约定
 */
public enum BizResultCode implements ResultCode {

    SUCC("0", "ok"),
    ERR_SYSTEM("10001", "系统错误"),
    ERR_PARAM("10002", "参数错误"),
    ERR_CONFIG("10003", "配置错误"),
    ERR_DATE_FORMAT("10004", "时间格式错误"),
    ERR_SIGN("10005", "签名错误"),
    ERR_HTTP_TIMEOUT("10006", "HTTP请求超时"),
    ERR_INVALID_HTTP_RESP("10007", "无效的HTTP响应"),
    ;

    /**
     * 初始化保存到map里方便根据code获取
     */
    private static Map<String, BizResultCode> RESULT_CODES = new HashMap<String, BizResultCode>();

    static {
        for (BizResultCode resultCode : BizResultCode.values()) {
            RESULT_CODES.put(resultCode.code, resultCode);
        }
    }

    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码信息
     */
    private String msg;

    /**
     * 构造函数
     *
     * @param code 结果码
     * @param msg  结果码信息
     */
    private BizResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 结果码
     * @return 枚举
     */
    public static BizResultCode getResultCode(String code) {
        return RESULT_CODES.get(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
