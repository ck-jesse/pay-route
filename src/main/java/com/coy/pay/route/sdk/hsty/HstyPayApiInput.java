package com.coy.pay.route.sdk.hsty;


import com.coy.pay.route.sdk.PayApiInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 汇商通盈支付基础入参
 *
 * @author chenck
 * @date 2018/4/12 16:21
 */
@JsonRootName("xml")
public class HstyPayApiInput<T extends HstyPayApiResult> extends PayApiInput<HstyPayApiResult> {

    public HstyPayApiInput() {
        super();
    }

    private String service;// 接口类型,接口类型：unified.trade.refundquery
    private String version = "1.0";// 版本号,版本号，version 默认值是 1.0
    private String charset = "UTF-8";// 字符集,可选值 UTF-8 ，默认为 UTF-8。
    private String sign_type = "MD5";// 签名方式,签名类型，取值：MD5默认：MD5
    private String sign_agentno;// 代理商号，由汇商分配。传入了此参数时，数据的签名使用的将是服务商的 signKey
    private String store_merchant_id;// 门店号，由汇商分配

    private String nonce_str;// 随机字符串
    private String sign;// 签名,MD5签名结果，详见“第4章 MD5签名规则”

    // 非支付api接口的字段
    @JsonIgnore
    private String secrtkey;// 签名秘钥

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

    public String getSign_agentno() {
        return sign_agentno;
    }

    public void setSign_agentno(String sign_agentno) {
        this.sign_agentno = sign_agentno;
    }

    public String getStore_merchant_id() {
        return store_merchant_id;
    }

    public void setStore_merchant_id(String store_merchant_id) {
        this.store_merchant_id = store_merchant_id;
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
}
