package com.wy.common.error;

/**
 * 描述：错误接口实现类
 * @author wangyu
 * @date 2019/5/23
 */

public enum EmBusinessError implements CommonError{

    //定义一个通用类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    //定义一个未知错误
    UNKNOW_ERROR(10002,"未知错误"),

    //服务器连接
    SERVER_CREATE_CONNECTION_ERROR(10003,"文件服务器建立连接错误"),
    SERVER_CLOSE_CONNECTION_ERROR(10003,"文件服务器关闭连接错误"),

    MYSQL_RUN_ERROR(10005,"数据库运行异常"),
    REDIS_RUN_ERROR(10006,"Redis运行错误"),
    Message_SERVICE_ERROR(10007,"短信服务器错误"),




    //以2开头的为用户相关错误
    USER_NOT_EXIT(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户账号或密码不正确"),
    USER_NOT_LOGIN(20003,"用户还未登录"),



    //以3开头的为商品相关错误
    ITEM_NOT_EXIT(30001,"商品信息不存在"),
    ITEM_STOCK_NOT_EXIT(30002,"商品详情不存在"),


    //以4开头为交易错误
    STOCK_NOT_ENOUGH(40001,"库存不够了"),



    //以5开头为订单错误
    ORDER_DELETE_ERROR(50001,"订单信息删除失败"),
    ORDER_SHIPPING_DELETE_ERROR(50002,"快递信息删除失败"),
    ORDER_ITEM_DELETE_ERROR(50003,"订单商品信息删除失败"),


    //以6开头快递


    //以9开头ES错误
    ES_IMPORT_FAIL(90001,"ES导入数据异常"),
    ES_CONNECT_FAIL(90002,"ES连接失败")
    ;



    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMessage() {
        return errMsg;
    }

    //定义一个通用的错误类型00001
    @Override
    public CommonError setErrMessage(String errMsg) {
        this.errMsg = errMsg ;
        return this;
    }
}
