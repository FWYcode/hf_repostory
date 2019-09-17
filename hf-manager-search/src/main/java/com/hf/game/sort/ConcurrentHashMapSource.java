package com.hf.game.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 123 on 2019-9-4.
 */
public class ConcurrentHashMapSource {
    private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
      /*  map.put("你好", "2");
        Map<String, String> map1 = new HashMap<>();
        map1.put("你好", "2");
        */
        int[] arr = {1, 5, 7, 3};
        int[] brr = arr;
        brr[3]=4;
        System.out.println(Arrays.toString(arr));
        int i = 100222 & 3;
        System.out.println(i);
    }
}
