package com.hf.game.sort;

import java.util.Arrays;

/**
 * Created by 123 on 2019-8-28.
 */
public class MergeSort {
    public static void sort(long[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    public static void merge(long[] a, int low, int mid, int high) {
        long[] temp = new long[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            a[low + x] = temp[x];
        }

    }

    public static void main(String[] args) {
      /*  int[] data = {4, 7, 6, 1, 2, 5};
        MergeSort.sort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));*/
    }
}
