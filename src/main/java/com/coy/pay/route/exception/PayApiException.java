package com.coy.pay.route.exception;

public class PayApiException extends BizException {
    public PayApiException(ResultCode resultCode) {
        super(resultCode);
    }

    public PayApiException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public PayApiException(ResultCode resultCode, String detailMessage) {
        super(resultCode, detailMessage);
    }

    public PayApiException(ResultCode resultCode, String detailMessage, Throwable cause) {
        super(resultCode, detailMessage, cause);
    }

    public PayApiException(String code, String msg) {
        super(code, msg);
    }

    public PayApiException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public PayApiException(String code, String msg, String detailMessage) {
        super(code, msg, detailMessage);
    }

    public PayApiException(String code, String msg, String detailMessage, Throwable cause) {
        super(code, msg, detailMessage, cause);
    }
}
