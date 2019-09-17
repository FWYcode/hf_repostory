package com.hf.game.sort;

import java.util.Arrays;

/**
 * Created by 123 on 2019-8-28.
 */
public class ShellSort {
    public static void sort(int[] data) {
        int prefix;
        int temp;
        int len = data.length;
        int group = len / 2;

        while (group > 0) {
            for (int i = 0; i < len - group; i++) {
                prefix = i;
                temp = data[prefix + group];
                while (prefix >= 0 && temp < data[prefix]) {
                    data[prefix + group] = data[prefix];
                    prefix -= group;
                }
                data[prefix + group] = temp;
                System.out.println(Arrays.toString(data));

            }
            group /= 2;
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 4, 2, 5, 5, 8, 8, 13, 0, 1};
        ShellSort.sort(data);
    }
}
