package com.fxwhu.exercise;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * @author: fengxuan
 * @create 2018-10-25 下午11:44
 **/
public class Charpter2 {

    public void mergeSort(int[] source, int n) {
        sortByMergeSort(source, 0, n - 1);
    }


    public void sortByMergeSort(int[] source, int p, int r) {
        if (p >= r) {
            return;
        }
        int mid = p + (r - p) / 2;
        sortByMergeSort(source, p, mid);
        sortByMergeSort(source, mid + 1, r);
        merge(source, p, mid, r);
    }

    public void merge(int[] source, int p, int mid, int r) {
        System.out.println("p:" + p + ",mid:" + mid + ",r:" + r);
        int i = p, j = mid + 1, k = 0;
        int[] tmp = new int[r - p + 1];

        while (i <= mid && j <= r) {
            if (source[i] <= source[j]) {
                tmp[k++] = source[i++];;
            } else {
                tmp[k++] = source[j++];
            }
        }

        int start = i;
        int end = mid;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            tmp[k++] = source[start++];
        }
        System.out.println(JSON.toJSONString(Lists.newArrayList(tmp)));


        for (int l = 0; l <= r - p; ++l) {
            source[p + l] = tmp[l];
        }
    }


    public static void main(String[] args) {
        Charpter2 charpter2 = new Charpter2();
        int[] source = {1, 5, 7, 9, 3, 11, 10};
        charpter2.mergeSort(source, source.length);
        for (int i : source) {
            System.out.println(i);
        }
    }


}
