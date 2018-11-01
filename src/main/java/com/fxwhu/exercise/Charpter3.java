package com.fxwhu.exercise;

/**
 * @author: fengxuan
 * @create 2018-10-27 上午11:10
 **/
public class Charpter3 {

    static int[] axu;

    public static void main(String[] args) {
        Charpter3 charpter2 = new Charpter3();
        int[] source = {1, 5, 7, 9, 3, 11, 10};
        axu = new int[source.length];
        charpter2.sortByMergeSort(source, 0, source.length - 1);
        for (int i : source) {
            System.out.println(i);
        }
    }

    public void sortByMergeSort(int[] source, int p, int r) {
        if (r <= p) {
            return;
        }
        int mid = p + (r - p) / 2;
        sortByMergeSort(source, p, mid);
        sortByMergeSort(source, mid + 1, r);
        merge(source, p, mid, r);
    }

    public void merge(int[] source, int p, int mid, int r) {
        int i = p, j = mid + 1;
        for (int k = p; k <= r; k++) {
            axu[k] = source[k];
        }

        for (int k = p; k <= r; k++) {
            if (i > mid) {
                source[k] = axu[j++];
            }else if (j > r) {
                source[k] = axu[i++];
            }else if (axu[i] > axu[j]) {
                source[k] = axu[j++];
            } else {
                source[k] = axu[i++];
            }
        }
    }

}
