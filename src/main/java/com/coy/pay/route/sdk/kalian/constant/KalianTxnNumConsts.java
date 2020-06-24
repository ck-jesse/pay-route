package com.coy.pay.route.sdk.kalian.constant;

/**
 * 交易码常量值
 * @author yehl
 * @date 2018/8/20 17:54
 */
public class KalianTxnNumConsts {

    /**
     * 微信支付相关交易码
     * @author yehl
     * @date 2018/8/20 18:13
     */
    public interface WeiPay {
        public static final String CARD_PAY_TXNNUM = "101021";
        public static final String CODE_PAY_TXNNUM = "101022";
        public static final String PUBLIC_PAY_TXNNUM = "101024";
        public static final String ORDER_QUERY_TXNNUM = "001020";
        public static final String ORDER_CANCEL_TXNNUM = "301021";
        public static final String ORDER_CLOSE_TXNNUM = "301022";
        public static final String REFUND_TXNNUM = "301020";
        public static final String REFUND_QUERY_TXNNUM = "001030";
    }

    public interface WeiUnionPay {
        public static final String CARD_PAY_TXNNUM = "111021";
        public static final String CODE_PAY_TXNNUM = "111022";
        public static final String PUBLIC_PAY_TXNNUM = "111024";
        public static final String ORDER_QUERY_TXNNUM = "011020";
        public static final String ORDER_CANCEL_TXNNUM = "311021";
        public static final String ORDER_CLOSE_TXNNUM = "311022";
        public static final String REFUND_TXNNUM = "311020";
        public static final String REFUND_QUERY_TXNNUM = "011030";
    }

    /**
     * 支付宝相关交易码
     * @author yehl
     * @date 2018/8/20 18:14
     */
    public interface AliPay {
        public static final String CARD_PAY_TXNNUM = "102121";
        public static final String CODE_PAY_TXNNUM = "102122";
        public static final String PUBLIC_PAY_TXNNUM = "102124";
        public static final String ORDER_QUERY_TXNNUM = "002020";
        public static final String ORDER_CANCEL_TXNNUM = "302121";
        public static final String ORDER_CLOSE_TXNNUM = "302022";
        public static final String REFUND_TXNNUM = "302120";
        public static final String REFUND_QUERY_TXNNUM = "002130";
    }
    public static final String PAY_NOTIFY_TXNNUM = "401020";
}
