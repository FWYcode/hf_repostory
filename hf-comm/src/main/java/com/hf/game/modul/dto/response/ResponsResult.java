package com.hf.game.modul.dto.response;

import java.util.List;

/**
 * Created by 123 on 2019-5-23.
 */
public class ResponsResult <T>{
    private int code;
    private String msg;
    private List<T> data;
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
