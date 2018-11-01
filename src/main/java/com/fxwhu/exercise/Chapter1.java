package com.fxwhu.exercise;

import com.google.common.collect.Maps;

import java.util.BitSet;
import java.util.Map;

/**
 * @author: fengxuan
 * @create 2018-10-13 上午11:04
 **/
public class Chapter1 {


    public Object[][] ex13(Object[][] objects) {
        int m = objects.length;
        int n = objects[0].length;
        Object[][] result = new Object[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = objects[i][j];
            }
        }
        return result;
    }


    public int ex14(int num) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            int power = 1 << i;
            if ((num & power) == (power)) {
                result = i;
            }
        }
        return result;
    }

    public int ex14V2(int num) {
        int result = 0;
        while (num != 0) {
            result++;
            num = num >> 1;
        }
        return result - 1;
    }


    static class BitKeeper {
        int bitCounter;
    }

    public int getLowZeroBit(int num) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (num >> i << i == num) {
                result = i;
            }
        }
        return result;
    }


    static class Experiment {
        private int n;

        public Experiment(int n) {
            this.n = n;
        }


    }


    //java 中的 bitmap
    public void testBitMap() {
        BitSet bitSet = new BitSet(10000000);
        bitSet.set(20000, true);
        System.out.println(bitSet.get(1999));
    }


    //时空复杂度为2^n
    public int goUp(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return goUp(n - 1) + goUp(n - 2);
    }


    //备忘录法，时空复杂度都为 n
    public long goUpV2(int n, Map<Integer, Long> cache) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            long value = goUpV2(n - 1, cache) + goUpV2(n - 2, cache);
            cache.put(n, value);
            return value;
        }
    }


    public long goUpV3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        long a = 1L;
        long b = 2L;
        long temp = 0L;

        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }





    public static void main(String[] args) {
        Chapter1 chapter1 = new Chapter1();
        //System.out.println(chapter1.goUp(100));
        System.out.println(chapter1.goUpV2(50, Maps.newHashMap()));
        System.out.println(chapter1.goUpV3(50));
    }


}
