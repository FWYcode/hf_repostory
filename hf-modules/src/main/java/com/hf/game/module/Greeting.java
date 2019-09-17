package com.hf.game.module;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 123 on 2019-8-26.
 */
@Data
@ToString
public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}
