package com.coy.pay.route.util.security;

/**
 * SHA 摘要算法
 */
public class SHA {
    public static class SHA1 {
        private static final Digest digest = new Digest("SHA1");

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

        public static String encode2base64(byte[] bytes) {
            byte[] data = digest.encode2bytes(bytes);
            return Base64Util.encodeString(data);
        }

        public static String encode2base64(String data, String charset) {
            if (data == null) {
                return null;
            }

            byte[] enData = encode2bytes(data, charset);

            return Base64Util.encodeString(enData);
        }
    }

    public static class SHA256 {
        private static final Digest digest = new Digest("SHA-256");

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

        public static String encode2base64(byte[] bytes, boolean urlSafe) {
            byte[] data = digest.encode2bytes(bytes);
            return Base64Util.encodeString(data);
        }

        public static String encode2base64(byte[] bytes) {
            return encode2base64(bytes, false);
        }

        public static String encode2base64(String data, String charset, boolean urlSafe) {
            if (data == null) {
                return null;
            }

            byte[] enData = encode2bytes(data, charset);

            return Base64Util.encodeString(enData, urlSafe);
        }

        public static String encode2base64(String data, String charset) {
            // 将urlSafe从false修改为true,避免签名串在网络传输中=号等特殊字符被解析为参数
            return encode2base64(data, charset, true);
        }
    }
}
