package com.coy.pay.route.util.security;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名验签工具类，支持md5,rsa,sha1,sha-256
 */
public class SignUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    private static String ST_MD5 = "md5";
    private static String ST_RSA = "rsa";
    private static String ST_RSA2 = "rsa2";
    private static String ST_RSA_SHA256 = "rsa-256";
    private static String ST_SHA1 = "sha1";
    private static String ST_SHA256 = "sha-256";

    /**
     * 验签
     *
     * @param signData  待签名数据
     * @param charset   字符集
     * @param sign_type 签名类型
     * @param sign      签名
     * @param key       秘钥
     * @return
     */
    public static boolean checkSign(String signData, String charset, String sign_type, String sign,
                                    String key) {
        String type = sign_type;
        boolean checkResult = false;
        String calcSign = "";
        if (ST_MD5.equalsIgnoreCase(type)) {
            calcSign = MD5.encode(signData + "&key=" + key, charset);
            checkResult = sign.equalsIgnoreCase(calcSign);
        } else if (ST_RSA.equalsIgnoreCase(type)) {
            // SHA1WithRSA 算法
            checkResult = RSA.verifyBySHA1(signData, charset, sign, key);
        } else if (ST_RSA_SHA256.equalsIgnoreCase(type) || ST_RSA2.equalsIgnoreCase(type)) {
            // SHA256WithRSA 算法
            try {
                checkResult = RSAUtils.verifySign(RSAUtils.SignatureSuite.SHA256, signData.getBytes(charset), Base64Util.decode(sign), key);
            } catch (UnsupportedEncodingException e) {
                logger.error("checkSign error:{}", e.getMessage());
            }
        } else if (ST_SHA1.equalsIgnoreCase(type)) {
            calcSign = SHA.SHA1.encode(signData + "&key=" + key, charset);
            checkResult = sign.equalsIgnoreCase(calcSign);

        } else if (ST_SHA256.equalsIgnoreCase(type)) {
            // 由于encode采用的16进制，会导致签名串过长，替换为base64可减少签名串的长度
            // calcSign = SHA256.encode(signData + "&key=" + key, charset);
            calcSign = SHA.SHA256.encode2base64(signData + "&key=" + key, charset);
            // logger.info("checkSign：" + signData + "&key=" + key);
            // logger.info("checkSign：calcSign=" + calcSign);
            checkResult = sign.equalsIgnoreCase(calcSign);
        } else {
            logger.error("invalid sign type:{}", type);
        }

        if (!checkResult) {
            logger.debug("signData:{}, input sign:{}, calcsign:{}", signData, sign, calcSign);
        }
        return checkResult;
    }

    /**
     * 生成签名
     *
     * @param signData  待签名数据
     * @param charset   字符集
     * @param sign_type 签名类型
     * @param key       秘钥
     * @return
     */
    public static String genSign(String signData, String charset, String sign_type, String key) {
        String type = sign_type;
        String signResult = null;
        if (ST_MD5.equalsIgnoreCase(type)) {
            signResult = MD5.encode(signData + "&key=" + key, charset);
        } else if (ST_RSA.equalsIgnoreCase(type)) {
            // SHA1WithRSA 算法
            signResult = RSA.genSignWithSHA1(signData, charset, key, true);
        } else if (ST_RSA_SHA256.equalsIgnoreCase(type) || ST_RSA2.equalsIgnoreCase(type)) {
            // SHA256WithRSA 算法
            try {
                byte[] signBuf = RSAUtils.sign(RSAUtils.SignatureSuite.SHA256, signData.getBytes(charset), key);
                return new String(Base64Util.encodeString(signBuf, true));
            } catch (UnsupportedEncodingException e) {
                logger.error("genSign error:{}", e.getMessage());
            }
        } else if (ST_SHA1.equalsIgnoreCase(type)) {
            signResult = SHA.SHA1.encode(signData + "&key=" + key, charset);
        } else if (ST_SHA256.equalsIgnoreCase(type)) {
            // 由于encode采用的16进制，会导致签名串过长，替换为base64可减少签名串的长度
            // signResult = SHA256.encode(signData + "&key=" + key, charset);
            signResult = SHA.SHA256.encode2base64(signData + "&key=" + key, charset);
            // logger.info("genSign：" + signData + "&key=" + key);
            // logger.info("genSign：signResult=" + signResult);
        } else {
            logger.error("invalid sign type:{}", type);
        }

        return signResult;
    }

    /**
     * 将对象转换为k=v&k=v形式字符串<br>
     * 注：对象字段按照ASCII码递增排序（字母升序排序）<br>
     * 注：去掉为空和为null的字段<br>
     *
     * @param input 待签名对象 自定义对象或JSON格式字符串
     * @return
     */
    public static String calcSingData(Object input) {
        if (input == null) {
            return null;
        }
        Map<String, Object> objFields = null;
        if (input instanceof String) {
            objFields = JSON.parseObject(input.toString(), TreeMap.class);
        } else {
            objFields = JSON.parseObject(JSON.toJSONString(input), TreeMap.class);
        }
        objFields.remove("sign");

        return map2Params(objFields, true);
    }

    /**
     * 将Map对象转换为k=v&k=v形式字符串<br>
     * 注：去掉为空和为null的字段<br>
     *
     * @author chenck
     * @date 2017年3月1日 下午5:39:59
     */
    public static String map2Params(Map<String, Object> params, boolean removeBlankField) {
        StringBuilder sb = new StringBuilder(1024);
        boolean first = true;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            // 移除值为空或为null的字段，不拼接到k=v串中
            if (removeBlankField
                    && (entry.getValue() == null || "".equals(entry.getValue().toString()))) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue().toString());
        }
        return sb.toString();
    }
}
