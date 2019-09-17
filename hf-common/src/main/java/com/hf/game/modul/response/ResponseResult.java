package com.hf.game.modul.response;

import lombok.Data;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

/**
 * Created by 123 on 2019-7-6.
 */
@Data
@ToString
public class ResponseResult<T> implements Response{
    int code=SUCCESS_CODE;
    boolean success=SUCCESS;
    String msg;
    public ResponseResult(ResultCode resultCode) {
        this.code=resultCode.code();
        this.success=resultCode.success();
        this.msg=resultCode.msg();
    }
    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }

    public ResponseResult() {
    }
}
