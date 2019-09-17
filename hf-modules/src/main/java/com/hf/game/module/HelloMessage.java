package com.hf.game.module;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;
import lombok.ToString;

/**
 * Created by 123 on 2019-8-26.
 */
@Data
@ToString
public class HelloMessage {
    private String pid;
    private String content;
    private String id;
}
