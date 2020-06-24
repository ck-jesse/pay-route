package com.coy.pay.route.sdk.kalian;


import com.coy.pay.route.sdk.PayApiInput;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 中旅银行支付基础入参
 *
 * @author yehl
 * @date 2018/8/15 11:30
 */
public class KalianPayApiInput<T extends KalianPayApiResult> extends PayApiInput<KalianPayApiResult> {

    public KalianPayApiInput() {
        super();
    }

    private String txn_num;
    private String version;
    private String date_time;
    private String dev_id;
    private String mcht_id;
    private String agent_no;
    private String out_order_no;
    private String sign;
    private String nonce_str;

    @JsonIgnore
    private String charset;

    @JsonIgnore
    private String  secrtkey;

    public String getTxn_num() {
        return txn_num;
    }

    public void setTxn_num(String txn_num) {
        this.txn_num = txn_num;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getDev_id() {
        return dev_id;
    }

    public void setDev_id(String dev_id) {
        this.dev_id = dev_id;
    }

    public String getMcht_id() {
        return mcht_id;
    }

    public void setMcht_id(String mcht_id) {
        this.mcht_id = mcht_id;
    }

    public String getAgent_no() {
        return agent_no;
    }

    public void setAgent_no(String agent_no) {
        this.agent_no = agent_no;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public void setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSecrtkey() {
        return secrtkey;
    }

    public void setSecrtkey(String secrtkey) {
        this.secrtkey = secrtkey;
    }
}
