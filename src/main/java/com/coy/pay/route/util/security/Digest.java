package com.coy.pay.route.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class Digest {
    private static final Logger logger = LoggerFactory.getLogger(Digest.class);
    protected String algorithm;

    public Digest(String alg) {
        this.algorithm = alg;
    }

    public byte[] encode2bytes(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return md.digest(bytes);
        } catch (Exception e) {
            logger.error("MessageDigest with {} fail", algorithm);
            logger.error("MessageDigest fail", e);
        }

        return null;
    }

    public byte[] encode2bytes(String data, String charset) {
        if (null == data)
            return null;

        try {
            byte[] bytes;
            if (null == charset)
                bytes = data.getBytes();
            else
                bytes = data.getBytes(charset);

            return encode2bytes(bytes);
        } catch (Exception e) {
            logger.error("MD5 encode fail", e);
        }

        return null;
    }

    public String encode(String data, String charset, boolean lowercase) {
        byte[] endata = encode2bytes(data, charset);

        if (null == endata)
            return null;

        return HexCode.encode(endata, lowercase);
    }

    public String encode(String data, boolean lowercase) {
        return encode(data, null, lowercase);
    }

    public String encode(String data) {
        return encode(data, null, true);
    }

    public String encode(String data, String charset) {
        return encode(data, charset, true);
    }
}
