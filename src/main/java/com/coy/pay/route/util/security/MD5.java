package com.coy.pay.route.util.security;


/**
 * 计算MD5值
 */
public class MD5 {

    private static final Digest digest = new Digest("MD5");

    public static byte[] encode2bytes(byte[] bytes) {
        return digest.encode2bytes(bytes);
    }

    public static byte[] encode2bytes(String data, String charset) {
        return digest.encode2bytes(data, charset);
    }

    public static String encode(String data, String charset, boolean lowercase) {
        return digest.encode(data, charset, lowercase);
    }

    public static String encode(String data, boolean lowercase) {
        return encode(data, null, lowercase);
    }

    public static String encode(String data) {
        return encode(data, null, true);
    }

    public static String encode(String data, String charset) {
        return encode(data, charset, true);
    }
}
