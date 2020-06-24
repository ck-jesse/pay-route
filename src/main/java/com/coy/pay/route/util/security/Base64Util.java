package com.coy.pay.route.util.security;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * base 64编码解码工具类
 */
public class Base64Util {

    /**
     * base 64编码
     *
     * @param data
     * @return
     */
    public static String encodeString(String data) {
        if (data == null || data.length() == 0)
            return null;

        return Base64Util.encodeString(data.getBytes());
    }

    /**
     * base64编码
     *
     * @param data
     * @param charset data的编码字符集
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeString(String data, String charset) throws UnsupportedEncodingException {
        if (data == null || data.length() == 0)
            return null;

        return Base64Util.encodeString(data.getBytes(charset));
    }

    /**
     * base 64编码,url safe模式,字符+和/分别变成-和_
     *
     * @param data
     * @param urlSafe true进行url安全处理,false不处理与encode(String data)相同
     * @return
     */
    public static String encodeString(String data, boolean urlSafe) {
        return Base64Util.encodeString(data.getBytes(), urlSafe);
    }

    /**
     * base 64编码,
     *
     * @param data
     * @param charset data字符集
     * @param urlSafe url safe模式,字符+和/分别变成-和_
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeString(String data, String charset, boolean urlSafe)
            throws UnsupportedEncodingException {
        return Base64Util.encodeString(data.getBytes(charset), urlSafe);
    }

    /**
     * base 64编码
     *
     * @param bytes
     * @return
     */
    public static String encodeString(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return null;

        return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
    }

    /**
     * base 64编码
     *
     * @param bytes
     * @return
     */
    public static byte[] encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return null;

        return org.apache.commons.codec.binary.Base64.encodeBase64(bytes);
    }

    /**
     * base 64编码
     *
     * @param bytes
     * @param urlSafe url safe模式,字符+和/分别变成-和_
     * @return
     */
    public static String encodeString(byte[] bytes, boolean urlSafe) {
        if (bytes == null || bytes.length == 0)
            return null;

        if (urlSafe) {
            return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(bytes);
        } else {
            return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        }
    }

    /**
     * base 64 解码
     *
     * @param data
     * @return
     */
    public static byte[] decode(String data) {
        if (data == null || data.length() == 0)
            return null;

        return org.apache.commons.codec.binary.Base64.decodeBase64(data);
    }

    /**
     * base 64解码，返回的结果是字串，该功能对编码时源内容是字串的使用
     *
     * @param data
     * @return
     */
    public static String decodeString(String data) {
        byte[] deData = decode(data);

        if (deData == null)
            return null;

        return new String(deData);
    }

    /**
     * base 64解码，返回的结果是字串，该功能对编码时源内容是字串的使用
     *
     * @param data
     * @param decodeCharset 解码后字串按指定的字符集处理
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeString(String data, String decodeCharset)
            throws UnsupportedEncodingException {
        byte[] deData = decode(data);

        if (deData == null)
            return null;

        return new String(deData, decodeCharset);
    }

    /**
     * 格式化base64的字串，每行76个字符，超过则换行
     *
     * @param srcText
     * @return
     */
    public static String formatBase64Str(String srcText) {
        if (StringUtils.isBlank(srcText))
            return null;

        int strLen = srcText.length();
        // 计算格式化后需要的长度，原长度+多出来的换行符即可,+10是为防止异常多出10个字符
        int maxLen = strLen + (strLen / 76) * 2 + 10;
        StringBuilder sb = new StringBuilder(maxLen);

        int count = 0;
        for (int i = 0; i < strLen; i++) {
            char ch = srcText.charAt(i);
            if (ch != '\r' && ch != '\n') {
                count++;

                if ((count - 1) % 76 == 0 && count > 1)
                    sb.append("\r\n");

                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String b64_lt_76 = "MIICdwIB";
            System.out.println(Base64Util.formatBase64Str(b64_lt_76));
            String b64_eq_76 = "rph/IUimDCuE8IoLGwGSx2XSLo6DBYC8+qEk59ZMsBFNeNNrSzPY+7+8e4aftyZI+VuSsc4sGKqd";
            System.out.println(Base64Util.formatBase64Str(b64_eq_76));
            String b64_gt_76 = "rnF44XL4/MY7VyrtKL8kgh2/zTo2m2MD3YmTdHVOtMWFNzkA6IVDzGYQgSUPkr9r30p1AgMBAAEC1234";
            System.out.println(Base64Util.formatBase64Str(b64_gt_76));
            String b64_gt_762 = "gYEAopb3KV0nFzUHcUl/BEEuSsCrwqra8vt2kP1RupXHBySqXFl4phI7wf1mzw8oRQHEOhSddAuky+4D+2hy/6v9Q5Kr+Tk8HWi8kNjVlbiGhgvgh7SRzjjfRxSGZcaKHKa36Db0ZpFLuTSBx50vF7I5";
            System.out.println(Base64Util.formatBase64Str(b64_gt_762));

            System.out.println("===end===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
