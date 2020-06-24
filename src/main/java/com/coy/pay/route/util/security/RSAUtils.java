package com.coy.pay.route.util.security;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    private static Logger LOG = LoggerFactory.getLogger(RSAUtils.class);

    public static enum SignatureSuite {
        SHA1("SHA1WithRSA"), SHA256("SHA256WithRSA");

        private String suite;

        SignatureSuite(String suite) {
            this.suite = suite;
        }

        public String val() {
            return suite;
        }
    }


    private static KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("初始化RSA KeyFactory失败");
        }
    }

    public static byte[] sign(SignatureSuite suite, byte[] msgBuf, String privateKeyStr) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(suite.val());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            PrivateKey privateKey = getKeyFactory().generatePrivate(keySpec);
            signature.initSign(privateKey);
        } catch (Exception e) {
            LOG.error("解析私钥失败：{}", e.getMessage());
            throw new RuntimeException("INVALID_PRIVATE_KEY");
        }
        try {
            signature.update(msgBuf);
            return signature.sign();
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean verifySign(SignatureSuite suite, byte[] msgBuf, byte[] sign, String publicKeyStr) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(suite.val());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = getKeyFactory().generatePublic(keySpec);
            signature.initVerify(publicKey);
        } catch (Exception e) {
            throw new RuntimeException("INVALID_PUBLIC_KEY");
        }
        try {
            signature.update(msgBuf);
            return signature.verify(sign);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
