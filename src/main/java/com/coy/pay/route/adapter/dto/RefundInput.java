package com.coy.pay.route.adapter.dto;

import com.coy.pay.route.adapter.dto.base.PayApiInput;
import com.coy.pay.route.adapter.type.CustomApiIdEnum;

/**
 * 退款入参
 *
 * @author chenck
 * @date 2018/4/10 21:50
 */
public class RefundInput extends PayApiInput<RefundResult> {

    public RefundInput() {
        super();
        // 设置api标识
        this.setApiId(CustomApiIdEnum.TRADE_REFUND.getValue());
    }

    private String listid;// 订单号
    private String refundListid;// 退款订单号
    private Long totalFee;// 总金额，以分为单位
    private Long refundFee;// 退款金额, 退款总金额,单位为分,可以做部分退款
    private String refundChannel;// 1-原路退款，默认 2-余额

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getRefundListid() {
        return refundListid;
    }

    public void setRefundListid(String refundListid) {
        this.refundListid = refundListid;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }
}
