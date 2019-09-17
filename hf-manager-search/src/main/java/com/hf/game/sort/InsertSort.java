package com.hf.game.sort;

import java.util.Arrays;

/**
 * Created by 123 on 2019-8-28.
 */
public class InsertSort {
    public static void sort(long[] data) {
        int preindex;
        long current;
        for (int i =0;i<data.length-1;i++) {
             preindex=i;
             current = data[preindex + 1];
            while (preindex >= 0 && current < data[preindex]) {
                data[preindex+1]=data[preindex];
                preindex--;
            }
            data[preindex+1]=current;
        }
    }

  /*  public static void main(String[] args) {
        int[] data = {3, 4, 2, 5, 5, 8, 8, 13, 0, 1};
        InsertSort.sort(data);
        System.out.println(Arrays.toString(data));
    }*/
}
