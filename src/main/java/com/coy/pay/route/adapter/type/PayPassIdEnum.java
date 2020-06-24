package com.coy.pay.route.adapter.type;

/**
 * 支付通道标识类型
 *
 * @author chenck
 * @date 2018/4/10 22:11
 */
public enum PayPassIdEnum {

    SWIFTPASS("SWT_PASS", "威富通"),
    SWT_CIB("SWT_CIB_PASS", "威富通兴业"),
    SWT_SPDB("SWT_SPDB_PASS", "威富通浦发"),
    SWT_CITIC("SWT_CITIC_PASS", "威富通中兴"),
    HSTYPAY("HSTY_PASS", "汇商通盈"),
    KALIAN("KALIAN_PASS", "中旅银行");

    private PayPassIdEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;// 支付通道
    private String name;// 支付通道名称

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getName(String value) {
        for (PayPassIdEnum type : PayPassIdEnum.values()) {
            if (type.getValue().equals(value)) {
                return type.getName();
            }
        }
        return null;
    }
}
