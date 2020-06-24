package com.coy.pay.route.sdk.swiftpass;


import com.coy.pay.route.sdk.PayApiResult;

/**
 * 威富通支付基础出参
 *
 * @Author chenck
 * @Date 2018/3/9 11:10
 */
public class SwiftpassPayApiResult extends PayApiResult {

    private String version;// 版本号,版本号，version默认值是2.0。
    private String charset = "UTF-8";// 字符集,可选值 UTF-8 ，默认为 UTF-8。
    private String sign_type = "MD5";// 签名方式,签名类型，取值：MD5默认：MD5
    private String status;// 返回状态码，0表示成功，非0表示失败此字段是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断
    private String message;//返回信息，如非空，为错误原因签名失败参数格式校验错误

    //以下字段在 status 为 0的时候有返回
    private String result_code;// 业务结果，0表示成功，非0表示失败    注：此处返回0表示退款申请接收成功，实际的退款结果根据退款查询接口查询
    private String mch_id;// 商户号,商户号，由威富通分配
    private String device_info;// 设备号,平台支付分配的终端设备号
    private String nonce_str;// 随机字符串,随机字符串，不长于 32 位
    private String code;//
    private String err_code;// 错误代码,参考错误码
    private String err_msg;// 错误代码描述,结果信息描述
    private String sign;// 签名,MD5签名结果，详见“安全规范”

    // 以下字段在 status 和 result_code 都为 0的时候有返回
    // 注：业务字段在具体的子类中


    // getter and setter

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
