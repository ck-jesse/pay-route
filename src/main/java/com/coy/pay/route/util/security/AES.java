package com.coy.pay.route.util.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AES {
    private static final Logger logger = LoggerFactory.getLogger(AES.class);

    // AES的默认加密算法
    protected static String DEFAULT_AES_ALG = "AES/CBC/PKCS5Padding";
    protected static String DEFAULT_AES_MODE_PADD = "CBC/PKCS5Padding";
    protected static int BLOCK_SIZE = 16;// 128bit,16字节
    // 默认的初始IV（向量）
    private static final byte[] KEY_IV = {0x1f, 0x32, 0x69, 0x5A, 0x71, 0x2D, 0x5F, 0x14, 0x4E,
            0x6A, 0x79, 0x52, 0x3B, 0x2D, 0x3A, 0x45};

    /**
     * AES加密
     *
     * @param ins
     * @param outs
     * @param key
     * @param modePadding
     * @return true-成功，false-失败
     */
    public static boolean encode(InputStream ins, OutputStream outs, Key key, String modePadding,
                                 byte[] iv) {
        try {
            Cipher cipher = null;
            if (modePadding == null)
                cipher = Cipher.getInstance(DEFAULT_AES_ALG);
            else if (modePadding.startsWith("AES/")) {
                cipher = Cipher.getInstance(modePadding);
            } else {
                cipher = Cipher.getInstance("AES/" + modePadding);
            }

            int max_block_size = BLOCK_SIZE;

            // encrypt the plain text using key
            if (iv != null) {
                IvParameterSpec ips = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key, ips);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }

            int readSize = 0;
            byte[] buffer = new byte[max_block_size];
            // 每次读取16个字节，循环读取
            byte[] enData = null;
            while ((readSize = ins.read(buffer)) > 0) {
                // 先进行多部分分组加密，最后一组数据需要使用doFinal处理
                // 当最后一次不够16字节时update结果返回空字节数组
                // 当最后一次为16字节时update返回16字节的加密结果，结尾填充由doFinal完成
                enData = cipher.update(buffer, 0, readSize);
                if (enData != null && enData.length > 0) {
                    outs.write(enData);
                }
            }

            // 处理最后一组数据
            enData = cipher.doFinal();
            if (enData != null && enData.length > 0) {
                outs.write(enData);
            }

            return true;

        } catch (Throwable e) {
            logger.error("AES encode fail", e);
        }

        return false;
    }

    public static byte[] encode(byte[] srcData, Key key, String modePadding) {
        InputStream in = new ByteArrayInputStream(srcData);
        int srcLen = srcData.length;

        int max_block_size = BLOCK_SIZE;

        // 计算加密后的需要保存数据的长度
        int outLen = ((srcLen + max_block_size - 1) / max_block_size) + BLOCK_SIZE;
        ByteArrayOutputStream out = new ByteArrayOutputStream(outLen);
        boolean result = encode(in, out, key, modePadding, KEY_IV);
        if (!result)
            return null;

        return out.toByteArray();
    }

    public static String encode(String srcData, String key) {
        byte[] keyData = HexCode.decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(keyData, "AES");

        byte[] enData = encode(srcData.getBytes(), skeySpec, null);

        return HexCode.encode(enData, true);
    }

    /**
     * 加密后base64
     *
     * @param srcData
     * @param key
     * @param urlSafe 是否支持url安全(+和/变为-和_)
     * @return
     * @author tinnfy
     * @date 2017年1月19日 下午6:34:07
     */
    public static String encode2base64(String srcData, String key, boolean urlSafe) {
        byte[] keyData = HexCode.decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(keyData, "AES");

        byte[] enData = encode(srcData.getBytes(), skeySpec, null);

        return Base64Util.encodeString(enData, urlSafe);
    }

    /**
     * 加密后base64
     *
     * @param srcData
     * @param key
     * @return
     * @author tinnfy
     * @date 2017年1月19日 下午6:33:33
     */
    public static String encode2base64(String srcData, String key) {
        return encode2base64(srcData, key, false);
    }

    public static boolean decode(InputStream ins, OutputStream outs, Key key, String modePadding) {
        try {
            Cipher cipher = null;
            if (modePadding == null)
                cipher = Cipher.getInstance(DEFAULT_AES_ALG);
            else if (modePadding.startsWith("AES/")) {
                cipher = Cipher.getInstance(modePadding);
            } else {
                cipher = Cipher.getInstance("AES/" + modePadding);
            }

            int max_block_size = BLOCK_SIZE;
            IvParameterSpec ips = new IvParameterSpec(KEY_IV);
            // encrypt the plain text using the public key
            cipher.init(Cipher.DECRYPT_MODE, key, ips);
            int readSize = 0;
            byte[] buffer = new byte[max_block_size];
            // 每次读取16个字节，循环读取
            byte[] deData = null;

            /**
             * 解密时：第一次update结果为空字节数组[]，第二次才得到第一次update输入的结果
             */
            while ((readSize = ins.read(buffer)) > 0) {
                // 解密当前数据
                deData = cipher.update(buffer, 0, readSize);

                if (deData != null && deData.length > 0) {
                    outs.write(deData);
                }
            }

            //处理最后一次数据解密
            deData = cipher.doFinal();
            if (deData != null && deData.length > 0) {
                outs.write(deData);
            }

            return true;

        } catch (Throwable e) {
            logger.error("AES decode fail", e);
        }

        return false;
    }

    public static byte[] decode(byte[] srcData, Key key, String modePadding) {
        InputStream in = new ByteArrayInputStream(srcData);
        int srcLen = srcData.length;

        int max_block_size = BLOCK_SIZE;

        // 计算加密后的需要保存数据的长度
        int outLen = ((srcLen + max_block_size - 1) / max_block_size);
        ByteArrayOutputStream out = new ByteArrayOutputStream(outLen);
        boolean result = decode(in, out, key, modePadding);
        if (!result)
            return null;

        return out.toByteArray();
    }

    public static String decode(String srcData, String key) {
        byte[] keyData = HexCode.decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(keyData, "AES");

        byte[] enData = HexCode.decode(srcData);

        byte[] deData = decode(enData, skeySpec, null);

        return new String(deData);
    }

    public static String decodeFromBase64(String srcData, String key) {
        byte[] keyData = HexCode.decode(key);
        SecretKeySpec skeySpec = new SecretKeySpec(keyData, "AES");

        byte[] enData = Base64Util.decode(srcData);

        byte[] deData = decode(enData, skeySpec, null);

        return new String(deData);
    }

    public static void main(String[] args) {
        try {
            //String srcData = "2b6ce6afb0356424bb10089002cf85dca-123";
            String srcData = "2b6ce6afb0356424bb10089002cf85dc";
            //String srcData = "2b6c";
            String key = "b6ce6afb0356424bb10089002cf85dca";
            String enData = AES.encode2base64(srcData, key, true);
            System.out.println(enData);
            String deData = AES.decodeFromBase64(enData, key);
            System.out.println(deData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
