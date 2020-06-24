package com.coy.pay.route.adapter.dto.base;

import java.io.Serializable;

/**
 * 支付出参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class PayApiResult implements Serializable {

    /**
     * 返回码 0表示成功，其他表示失败
     */
    private String code = "0";

    /**
     * 描述信息
     */
    private String msg = "ok";

    public PayApiResult() {
    }

    public PayApiResult(String msg) {
        this.msg = msg;
    }

    public PayApiResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
