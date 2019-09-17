package com.hf.game.module.pojo.userManager;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by 123 on 2019-6-3.
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String  name;
    private List<Role> roles;
}
