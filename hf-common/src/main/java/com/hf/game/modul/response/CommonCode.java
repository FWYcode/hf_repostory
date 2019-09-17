package com.hf.game.modul.response;

import lombok.ToString;

/**
 * Created by 123 on 2019-7-6.
 */
@ToString
public enum CommonCode implements ResultCode{
    INVALID_PARAM(false,10003,"非法参数！"),
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    boolean success;
    int code;
    String msg;

    CommonCode(boolean success,  int code, String msg) {
        this.success=success;
        this.code=code;
        this.msg=msg;

    }
    @Override
    public boolean success() {
        return this.success;
    }

    @Override
    public String msg() {
        return this.msg;
    }

    @Override
    public int code() {
        return this.code;
    }
}
