package com.hf.game.module.items;

import com.hf.game.module.items.TestItem;

import java.io.Serializable;

/**
 * Created by 123 on 2019-9-3.
 */
public enum Singleton implements Serializable {
    INSTANCE;
    private TestItem item;


    Singleton() {
        item = new TestItem();
    }

    public TestItem getInstance() {
        return item;
    }
}
