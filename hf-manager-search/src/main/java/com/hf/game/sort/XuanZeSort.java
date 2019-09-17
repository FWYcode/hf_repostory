package com.hf.game.sort;

import java.util.Arrays;

/**
 * Created by 123 on 2019-8-28.
 */
public class XuanZeSort {
    private XuanZeSort() {

    }

    public static void sort(int[] data) {
        int maxindex = 0;
        for (int i = data.length - 1; i > 0; i--) {
            maxindex = i;
            for (int j = 0; j <i- 1; j++) {
                if (data[j] > data[maxindex]) {
                    maxindex = j;
                }
            }
            System.out.println(maxindex + "........." + i);

            if (maxindex!=i) {
                int temp = data[i];
                data[i] = data[maxindex];
                data[maxindex] = temp;
                System.out.println(Arrays.toString(data));
            }

        }

    }
}
