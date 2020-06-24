package com.coy.pay.route.adapter.type;

/**
 * 交易状态
 * 注：此状态值，基于威富通的api接口返回值，其他的第三方支付对应的状态需要转换为改状态后输出
 *
 * @author chenck
 * @date 2018/4/13 15:16
 */
public enum TradeStateEnum {

    SUCCESS("SUCCESS", "支付成功"),
    REFUND("REFUND", "转入退款"),
    NOTPAY("NOTPAY", "未支付"),
    CLOSED("CLOSED", "已关闭"),
    PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)"),
    REVERSE("REVERSE", "已冲正"),
    CANCEL("CANCEL", "已撤销");

    private TradeStateEnum(String state, String desc) {
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
        for (TradeStateEnum type : TradeStateEnum.values()) {
            if (type.getState().equals(state)) {
                return type.getDesc();
            }
        }
        return null;
    }
}
