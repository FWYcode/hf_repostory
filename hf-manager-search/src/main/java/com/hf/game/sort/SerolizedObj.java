package com.hf.game.sort;

import java.io.Serializable;

/**
 * Created by 123 on 2019-9-4.
 */
public class SerolizedObj implements Serializable {

    private static final SerolizedObj OBJ = new SerolizedObj();

    private SerolizedObj() {
    }

    public static SerolizedObj getObj() {
        return OBJ;
    }

    private Object readResolve() {
        System.out.println(1222);
        return OBJ;
    }
}
