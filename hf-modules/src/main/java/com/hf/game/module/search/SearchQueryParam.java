package com.hf.game.module.search;

import lombok.Data;
import lombok.ToString;

import java.io.FileWriter;
import java.io.Serializable;

/**
 * Created by 123 on 2019-7-6.
 */
@Data
@ToString
public class SearchQueryParam implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    //关键字
    private String keyWord;
    //一级分类
    private Integer categoryId1;
    //二级分类
    private Integer categoryId2;
    //品牌
    private String itemBarnd;
    //价格区间
    private Float priceMin;
    private Float priceMax;
    //排序字段
    private String sort;
    //过滤字段
    private String filter;
}
