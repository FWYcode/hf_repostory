package com.hf.game.jdk8_new_character.lambda;

/**
 * Created by 123 on 2019-8-20.
 */

public class Goods {
    private String name;
    private Integer pirce;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPirce() {
        return pirce;
    }

    public void setPirce(Integer pirce) {
        this.pirce = pirce;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", pirce=" + pirce +
                '}';
    }
}
