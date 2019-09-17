package com.hf.game.module.items;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 123 on 2019-6-27.
 */
@Data
@ToString
public class CateGoryResult implements Serializable{
    private static final long serialVersionUID = -916357110051689485L;
    private Integer id;
    private String label;
    private List<CateGoryResult> children;
}
