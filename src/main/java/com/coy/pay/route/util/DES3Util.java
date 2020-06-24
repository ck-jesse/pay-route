package com.coy.pay.route.util;

import com.coy.pay.route.util.security.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 3DES加解密
 *
 * @author zhl
 */
public class DES3Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(DES3Util.class);

    public static final String CHARSET_UTF8 = "utf-8";

    public static final String CHARSET_GBK = "gbk";

    // 算法名称
    private static final String KEY_ALGORITHM = "DESEDE";

    // 算法名称/加密模式/填充方式
    private static final String CIPHER_ALGORITHM = "desede/CBC/PKCS5Padding";

    // 加密KEY
    private static final String KEY = "3X41955KP0S00O8938AS9L9YKIU2YT1MNS0S219BGU0R12C0";

    // 加密偏移量
    private static final byte[] KEY_IV = {0, 1, 2, 3, 4, 5, 6, 7};


    /**
     * 加密参数,1次
     */
    public static String des3EncodeStr(String param) {
        return des3EncodeStr(param, 1);
    }

    /**
     * 加密参数
     */
    public static String des3EncodeStr(String param, int times) {
        try {
            if (null == param || "".equals(param.trim())) {
                return "";
            }
            return des3EncodeCBC(param, CHARSET_UTF8, times);
        } catch (Exception e) {
            LOGGER.error("加密参数发生异常", e);
            return "";
        }
    }

    /**
     * 解密参数,1次
     */
    public static String des3DecodeStr(String param) {
        return des3DecodeStr(param, 1);
    }

    /**
     * 解密参数
     */
    public static String des3DecodeStr(String param, int times) {
        try {
            if (null == param || "".equals(param.trim())) {
                return "";
            }
            return des3DecodeCBC(param, CHARSET_UTF8, times);
        } catch (Exception e) {
            LOGGER.error("解密参数发生异常", e);
            return "";
        }
    }

    // ====================================

    /**
     * CBC加密
     *
     * @param data
     * @param charset
     * @return
     * @throws Exception
     */
    public static String des3EncodeCBC(String data, String charset)
            throws Exception {
        //Security.addProvider(new BouncyCastleProvider());
        Key deskey = keyGenerator(new String(KEY.getBytes()));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data.getBytes(charset));
        return new String(Base64Util.encode(bOut), charset);
    }

    /**
     * CBC加密
     *
     * @param data
     * @param charset
     * @param times   加密次数
     * @return
     * @throws Exception
     * @author Gu.Dongying
     * @date 2016年3月12日 下午3:55:16
     */
    public static String des3EncodeCBC(String data, String charset, int times)
            throws Exception {
        if (times < 2) {
            return des3EncodeCBC(data, charset);
        }
        String v_cbc = data;

        for (int i = 0; i < times; i++) {
            v_cbc = des3EncodeCBC(v_cbc, charset);
        }

        return v_cbc;
    }

    /**
     * CBC解密
     *
     * @param data
     * @param charset
     * @return
     * @throws Exception
     */
    public static String des3DecodeCBC(String data, String charset)
            throws Exception {
        Key deskey = keyGenerator(new String(KEY.getBytes()));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(Base64Util.decode(data));
        return new String(bOut, charset);
    }

    /**
     * CBC解密
     *
     * @param data
     * @param charset
     * @param times   解密次数
     * @return
     * @throws Exception
     * @author Gu.Dongying
     * @date 2016年3月12日 下午3:56:56
     */
    public static String des3DecodeCBC(String data, String charset, int times)
            throws Exception {
        if (times < 2) {
            return des3DecodeCBC(data, charset);
        }
        String v_cbc = data;

        for (int i = 0; i < times; i++) {
            v_cbc = des3DecodeCBC(v_cbc, charset);
        }

        return v_cbc;
    }

    /**
     * 生成密钥key对象
     *
     * @param keyStr 密钥字符串
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static Key keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory
                .getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory
                .generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

}