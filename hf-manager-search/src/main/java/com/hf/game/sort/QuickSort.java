package com.hf.game.sort;

import java.util.Arrays;

/**
 * Created by 123 on 2019-8-28.
 */
public class QuickSort {
    public static void sort(long[] data, int l, int h) {
        int i = l;
        int j = h;

        if (i<j) {
            long temp = data[l];
            while (i < j) {
                while (i < j && temp <= data[j]) {
                    j--;
                }
                data[i] = data[j];
                while (i < j && temp > data[i]) {
                        i++;
                }
                data[j] = data[i];
            }

        data[i] = temp;
        sort(data,l,i-1);
        sort(data,i+1,h);
        }
    }
    public static void main(String[] args) {
        long[] data = {4, 7, 6, 1, 2, 8};
        QuickSort.sort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }
}
