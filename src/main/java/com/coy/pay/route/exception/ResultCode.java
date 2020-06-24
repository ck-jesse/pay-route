package com.coy.pay.route.exception;

/**
 * 结果码
 *
 * @author chenck
 * @date 2019/6/26 14:18
 */
public interface ResultCode {

    /**
     * 编码
     *
     * @return
     */
    public String getCode();

    /**
     * 描述信息
     *
     * @return
     */
    public String getMsg();
}
