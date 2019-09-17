package com.hf.game.modul.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by 123 on 2019-7-6.
 */
@Data
@ToString
public class QueryResult<T> {
    List<T> list;
    Long total;
}
