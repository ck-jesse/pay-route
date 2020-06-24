package com.coy.pay.route.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {
    private static final Logger logger = LoggerFactory.getLogger(RSA.class);

    public static final int PUBLIC_KEY = 1;
    public static final int PRIVATE_KEY = 2;

    protected static String DEFAULT_RSA_ALG = "RSA/ECB/PKCS1Padding";

    protected static String DEFAULT_RSA_MODE_PADD = "ECB/PKCS1Padding";

    /**
     * RSA最大加密明文大小
     */
    protected static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    protected static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成rsa公私钥
     *
     * @param keyRandom 随机串
     * @param keySize   密钥长度
     * @return
     */
    public static Tuple.Tuple2<PublicKey, PrivateKey> genKeyPair(String keyRandom, int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

            generator.initialize(keySize, new SecureRandom(keyRandom.getBytes()));
            KeyPair pair = generator.generateKeyPair();
            PublicKey pubKey = pair.getPublic();
            PrivateKey privKey = pair.getPrivate();

            return new Tuple.Tuple2<PublicKey, PrivateKey>(pubKey, privKey);
        } catch (NoSuchAlgorithmException e) {
            logger.error("not support RSA", e);
        } catch (Throwable t) {
            logger.error("generate RSA key fail", t);
        }

        return null;
    }

    /**
     * 生成RSA公私钥，以字串形返回公私钥
     *
     * @param keyRandom
     * @param keySize
     * @return 返回的_1()是公钥，_2()是私钥
     */
    public static Tuple.Tuple2<String, String> genKeyStrPair(String keyRandom, int keySize) {
        Tuple.Tuple2<PublicKey, PrivateKey> keyPair = genKeyPair(keyRandom, keySize);
        if (keyPair == null)
            return null;

        String pubStrKey = Base64Util.encodeString(keyPair._1().getEncoded());
        String privStrKey = Base64Util.encodeString(keyPair._2().getEncoded());

        return new Tuple.Tuple2<String, String>(pubStrKey, privStrKey);
    }

    /**
     * 从编码好的公钥串产生公钥KEY对象
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] keyBytes = Base64Util.decode(key);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            return publicKey;

        } catch (Exception e) {
            logger.error("RSA getPublicKey fail", e);
        }

        return null;
    }

    /**
     * 从编码好的私钥串产生私钥KEY对象
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) {
        try {
            byte[] keyBytes = Base64Util.decode(key);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;

        } catch (Exception e) {
            logger.error("RSA getPrivateKey fail", e);
        }

        return null;
    }

    /**
     * 验证RSA签名
     *
     * @param text
     * @param sign
     * @param key
     * @return
     */
    public static boolean verifyBySHA1(String text, String sign, String key) {
        try {
            Signature signatureChecker = Signature.getInstance("SHA1WithRSA");
            PublicKey pubKey = getPublicKey(key);
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(text.getBytes());
            // 验证签名是否正常
            byte[] signBytes = Base64Util.decode(sign);

            if (signatureChecker.verify(signBytes))
                return true;

            return false;
        } catch (Throwable t) {
            logger.error("verifyBySHA1 fail", t);
        }

        return false;
    }

    /**
     * 验证RSA签名
     *
     * @param text
     * @param sign
     * @param key
     * @return
     */
    public static boolean verifyBySHA1(String text, String charset, String sign, String key) {
        try {
            Signature signatureChecker = Signature.getInstance("SHA1WithRSA");
            PublicKey pubKey = getPublicKey(key);
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(text.getBytes(charset));
            // 验证签名是否正常
            byte[] signBytes = Base64Util.decode(sign);

            if (signatureChecker.verify(signBytes))
                return true;

            return false;
        } catch (Throwable t) {
            logger.error("verifyBySHA1 fail", t);
        }

        return false;
    }

    /**
     * 生成RSA签名
     *
     * @param text
     * @param key
     * @return
     */
    public static byte[] genByteSignWithSHA1(String text, String key) {
        try {
            Signature signatureChecker = Signature.getInstance("SHA1WithRSA");
            PrivateKey privKey = getPrivateKey(key);
            signatureChecker.initSign(privKey);
            signatureChecker.update(text.getBytes());

            return signatureChecker.sign();

        } catch (Throwable e) {
            logger.error("genByteSignWithSHA1 fail", e);
        }

        return null;
    }

    public static byte[] genByteSignWithSHA1(String text, String charset, String key) {
        try {
            Signature signatureChecker = Signature.getInstance("SHA1WithRSA");
            PrivateKey privKey = getPrivateKey(key);
            signatureChecker.initSign(privKey);
            signatureChecker.update(text.getBytes(charset));

            return signatureChecker.sign();

        } catch (Throwable e) {
            logger.error("genByteSignWithSHA1 fail", e);
        }

        return null;
    }

    /**
     * 生成RSA签名
     *
     * @param text
     * @param charset
     * @param key
     * @return
     */
    public static String genSignWithSHA1(String text, String charset, String key) {
        return RSA.genSignWithSHA1(text, charset, key, false);
    }

    public static String genSignWithSHA1(String text, String charset, String key, boolean urlSafe) {
        byte[] signData = genByteSignWithSHA1(text, charset, key);
        if (signData == null || signData.length == 0)
            return null;

        return Base64Util.encodeString(signData, urlSafe);
    }

    public static String genSignWithSHA1(String text, String key) {
        return RSA.genSignWithSHA1(text, key, false);
    }

    public static String genSignWithSHA1(String text, String key, boolean urlSafe) {
        byte[] signData = genByteSignWithSHA1(text, key);
        if (signData == null || signData.length == 0)
            return null;

        return Base64Util.encodeString(signData, urlSafe);
    }

    /**
     * RSA加密
     *
     * @param ins
     * @param outs
     * @param key
     * @param modePadding
     * @return true-成功，false-失败
     */
    public static boolean encode(InputStream ins, OutputStream outs, Key key, String modePadding) {
        try {
            Cipher cipher = null;
            if (modePadding == null)
                cipher = Cipher.getInstance(DEFAULT_RSA_ALG);
            else if (modePadding.startsWith("RSA/")) {
                cipher = Cipher.getInstance(modePadding);
            } else {
                cipher = Cipher.getInstance("RSA/" + modePadding);
            }

            RSAKey rsaKey = (RSAKey) key;
            int keyBitlen = rsaKey.getModulus().bitLength();
            logger.debug("RSA endocde key length:", keyBitlen);
            int max_block_size = keyBitlen / 8 - 11;

            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int readSize = 0;
            byte[] buffer = new byte[max_block_size];
            //每次读取117个字符，循环读取
            while ((readSize = ins.read(buffer)) > 0) {
                byte[] enData = cipher.doFinal(buffer, 0, readSize);
                outs.write(enData);
            }

            return true;

        } catch (Throwable e) {
            logger.error("RSA encode fail", e);
        }

        return false;
    }

    /**
     * RSA加密
     *
     * @param srcData
     * @param key
     * @param modePadding
     * @return
     */
    public static byte[] encode(byte[] srcData, Key key, String modePadding) {
        InputStream in = new ByteArrayInputStream(srcData);
        int srcLen = srcData.length;

        RSAKey rsaKey = (RSAKey) key;
        int keyBitlen = rsaKey.getModulus().bitLength();
        logger.debug("RSA endocde key length:", keyBitlen);
        int max_block_size = keyBitlen / 8 - 11;

        //计算加密后的需要保存数据的长度
        int outLen = ((srcLen + max_block_size - 1) / max_block_size) * (keyBitlen / 8);
        ByteArrayOutputStream out = new ByteArrayOutputStream(outLen);
        boolean result = encode(in, out, key, modePadding);
        if (!result)
            return null;

        return out.toByteArray();
    }

    public static byte[] encode(byte[] srcData, Key key) {
        return encode(srcData, key, null);
    }

    public static byte[] encode(byte[] srcData, String key, int keyType) {
        Key encodeKey = null;
        if (keyType == RSA.PRIVATE_KEY)
            encodeKey = getPrivateKey(key);
        else
            encodeKey = getPublicKey(key);

        return encode(srcData, encodeKey);
    }

    public static String encode(String srcData, String key, int keyType, boolean urlSafe) {
        byte[] data = encode(srcData.getBytes(), key, keyType);

        return Base64Util.encodeString(data, urlSafe);
    }

    public static String encode(String srcData, String key, int keyType) {
        return encode(srcData, key, keyType, false);
    }

    /**
     * RSA加密，公钥加密
     *
     * @param srcData
     * @param key     公钥
     * @return
     */
    public static String encode(String srcData, String key) {
        return encode(srcData, key, RSA.PUBLIC_KEY);
    }

    public static String encode(String srcData, String key, boolean urlSafe) {
        return encode(srcData, key, RSA.PUBLIC_KEY, urlSafe);
    }

    /**
     * RSA解密
     *
     * @param ins
     * @param outs
     * @param key
     * @param modePadding
     * @return true-成功,false-失败
     */
    public static boolean decode(InputStream ins, OutputStream outs, Key key, String modePadding) {
        try {
            Cipher cipher = null;
            if (modePadding == null)
                cipher = Cipher.getInstance(DEFAULT_RSA_ALG);
            else if (modePadding.startsWith("RSA/")) {
                cipher = Cipher.getInstance(modePadding);
            } else {
                cipher = Cipher.getInstance("RSA/" + modePadding);
            }

            //计算key长度
            RSAKey rsaKey = (RSAKey) key;
            int keyBitlen = rsaKey.getModulus().bitLength();
            logger.debug("RSA decode key length:", keyBitlen);
            int max_block_size = keyBitlen / 8;

            cipher.init(Cipher.DECRYPT_MODE, key);
            int readSize = 0;
            byte[] buffer = new byte[max_block_size];
            //每次读取128个字符，循环读取
            while ((readSize = ins.read(buffer)) > 0) {
                byte[] deData = cipher.doFinal(buffer, 0, readSize);
                outs.write(deData);
            }

            return true;
        } catch (Throwable e) {
            logger.error("RSA decode fail", e);
        }

        return false;
    }

    /**
     * RSA解密
     *
     * @param enData
     * @param key
     * @param modePadding
     * @return
     */
    public static byte[] decode(byte[] enData, Key key, String modePadding) {
        InputStream in = new ByteArrayInputStream(enData);
        int len = enData.length;
        //解密后的数据长度不超过密文长度
        ByteArrayOutputStream out = new ByteArrayOutputStream(len);
        boolean result = decode(in, out, key, modePadding);
        if (!result)
            return null;

        return out.toByteArray();
    }

    public static byte[] decode(byte[] enData, Key key) {
        return decode(enData, key, null);
    }

    public static byte[] decode(byte[] enData, String key, int keyType) {
        Key decodeKey = null;
        if (keyType == RSA.PUBLIC_KEY)
            decodeKey = getPublicKey(key);
        else
            decodeKey = getPrivateKey(key);

        return decode(enData, decodeKey);
    }

    public static String decode(String enData, String key, int keyType) {
        byte[] inData = Base64Util.decode(enData);
        byte[] data = decode(inData, key, keyType);

        return new String(data);
    }

    /**
     * RSA解密，私钥解密
     *
     * @param enData
     * @param key
     * @return
     */
    public static String decode(String enData, String key) {
        return decode(enData, key, RSA.PRIVATE_KEY);
    }

    public static void main(String[] args) {
        try {

            Tuple.Tuple2<String, String> keys = genKeyStrPair("1234", 1024);
            System.out.println(keys._1());
            System.out.println(Base64Util.formatBase64Str(keys._2()));
            System.out.println(Base64Util.formatBase64Str(keys._2()));

            String srcText = "123wsdfQwehPE8ynEwZkHm0XRi80sMQnNJ85wnVqssuV+jAWnR565glvy2ks9aLz6TZAkA5KZ+1axquBMlmKpUaQFv7LXipfkVWF16h4QeWGA/h9xreRcAnt5rbwk2JFD0DupQeZelEWrHxxvaAWBkFhSsFAkBcwTiApqV+I6nAWKv16llzaIvKDqn7hPpXVNM7iV/VipM/zETXeJMvBSHJAm2OdD5eL6UlQ7WttaIS";
            String sign = genSignWithSHA1(srcText, keys._2());
            System.out.println(sign);

            System.out.println(verifyBySHA1(srcText, "12" + sign.substring(2), keys._1()));

            String srcData = "123wsdfQwehPE8ynEwZkHm0XRi80sMQnNJ85wnVqssuV+jAWnR565glvy2ks9aLz6TZAkA5KZ+1axquBMlmKpUaQFv7LXipfkVWF16h4QeWGA/h9xreRcAnt5rbwk2JFD0DupQeZelEWrHxxvaAWBkFhSsFAkBcwTiApqV+I6nAWKv16llzaIvKDqn7hPpXVNM7iV/VipM/zETXeJMvBSHJAm2OdD5eL6UlQ7WttaIS" +
                    "LAcGGQKBgF1CZT+Lsf2aLRsD8f/w5yp9/fSlDDmX/F4eFO+qUQUa1S+CrGh4/KiB7PGDeiffnE0y" +
                    "BfMmrHDWZALhLUn1PNoX6iW8QCf+Fq9SrUzGJarYcVyNPr6ATuhQuWGr++qHj3+fys8r+04vEIHO";
            //String srcData = "12312";
            String enData = RSA.encode(srcData, keys._1());
            System.out.println(enData);
            String deData = RSA.decode(enData, keys._2());
            System.out.println(deData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
