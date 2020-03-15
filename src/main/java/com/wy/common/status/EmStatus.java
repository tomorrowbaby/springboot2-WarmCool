package com.wy.common.status;

public enum EmStatus implements CommonStatus{
    //订单状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
    ORDER_PAY_NO(0,"未付款"),
    ORDER_PAY(1,"已付款"),
    ORDER_DELIVER_NO(2,"未发货"),
    ORDER_DELIVER(3,"已发货"),
    ORDER_DEAL(4,"交易成功"),
    ORDER_CLOSE(5,"交易关闭"),
    ORDER_NO_PAY(6,"交易失败"),
    ORDER_Other(7,"其他"),
    ;

    private Integer statusCode;

    private String statusInfo;


    EmStatus(Integer statusCode,String statusInfo){
        this.statusCode = statusCode;
        this.statusInfo = statusInfo;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getStatusInfo() {
        return this.statusInfo;
    }

    @Override
    public CommonStatus setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
        return this;
    }
}
