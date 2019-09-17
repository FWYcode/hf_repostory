package com.hf.game.module.pojo.userManager;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by 123 on 2019-6-3.
 */
@Accessors(chain = true)
@Data
public class Role {
    private Long id;
    private String name;
    private List<Power> powers;

}
