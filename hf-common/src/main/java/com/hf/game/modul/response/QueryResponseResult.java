package com.hf.game.modul.response;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 123 on 2019-7-6.
 */
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {
    private QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult result) {
        super(resultCode);
        this.queryResult=result;
    }

    public QueryResponseResult() {

    }
}
