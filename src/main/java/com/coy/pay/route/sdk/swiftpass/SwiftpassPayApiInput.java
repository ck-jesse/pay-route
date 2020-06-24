package com.coy.pay.route.sdk.swiftpass;


import com.coy.pay.route.sdk.PayApiInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 威富通支付基础入参
 *
 * @Author chenck
 * @Date 2018/3/9 11:10
 */
@JsonRootName("xml")
public class SwiftpassPayApiInput<T extends SwiftpassPayApiResult> extends PayApiInput<SwiftpassPayApiResult> {

    public SwiftpassPayApiInput() {
        super();
    }

    private String service;// 接口类型,接口类型：unified.trade.refundquery
    private String version;// 版本号,版本号，version默认值是2.0。
    private String charset = "UTF-8";// 字符集,可选值 UTF-8 ，默认为 UTF-8。
    private String sign_type = "MD5";// 签名方式,签名类型，取值：MD5默认：MD5
    private String mch_id;// 商户号,商户号，由威富通分配

    private String nonce_str;// 随机字符串
    private String sign;// 签名,MD5签名结果，详见“第4章 MD5签名规则”

    // 非威富通支付api接口的字段
    @JsonIgnore
    private String secrtkey;// 签名秘钥
    @JsonIgnore
    private String rsaPrivateKey;// RSA私钥（我方私钥，用于对请求数据进行签名，请求第三方支付）
    @JsonIgnore
    private String rsaPublicKey;// RSA公钥（对方公钥，用于对第三方支付响应数据进行验签）

    // getter and setter

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

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

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSecrtkey() {
        return secrtkey;
    }

    public void setSecrtkey(String secrtkey) {
        this.secrtkey = secrtkey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }
}
