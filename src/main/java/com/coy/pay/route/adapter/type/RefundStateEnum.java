package com.coy.pay.route.adapter.type;

/**
 * 退款状态
 * 注：此状态值，基于威富通的api接口返回值，其他的第三方支付对应的状态需要转换为改状态后输出
 *
 * @author chenck
 * @date 2018/4/13 15:16
 */
public enum RefundStateEnum {

    SUCCESS("SUCCESS", "退款成功"),
    FAIL("FAIL", "退款失败"),
    PROCESSING("PROCESSING", "退款处理中"),
    NOTSURE("NOTSURE", "未确定"), // 需要商户原退款单号重新发起
    CHANGE("CHANGE", "转入代发"), // 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者平台转账的方式进行退款。
    ;

    private RefundStateEnum(String state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    private String state;
    private String desc;

    public String getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }

    public static String getName(String state) {
        for (RefundStateEnum type : RefundStateEnum.values()) {
            if (type.getState().equals(state)) {
                return type.getDesc();
            }
        }
        return null;
    }
}
