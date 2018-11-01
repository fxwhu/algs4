package com.fxwhu.exercise;

import org.junit.*;
import org.junit.Test;

import java.util.BitSet;

/**
 * @author: fengxuan
 * @create 2018-10-13 上午11:08
 **/
public class Chapter1Test {
    Chapter1 chapter1 = new Chapter1();


    @org.junit.Test
    public void convertTwoDimensional() throws Exception {
        Integer[][] ints = new Integer[3][5];
        ints[0] = new Integer[]{1, 2, 3, 4, 5};
        ints[1] = new Integer[]{6, 7, 8, 9, 10};
        ints[2] = new Integer[]{11, 12, 13, 14, 15};
        Object[][] objects = chapter1.ex13(ints);
        for (Object[] object : objects) {
            for (Object o : object) {
                System.out.print(o + " ");
            }
            System.out.println("");
        }
    }


    @org.junit.Test
    public void testEx14() {
        System.out.println(chapter1.ex14V2(128));
    }

    @Test
    //java 中的 bitmap
    public void testBitMap() {
        BitSet bitSet = new BitSet(10000000);
        bitSet.set(20000, true);
        bitSet.set(8111111, true);
        System.out.println(bitSet.get(1999));
        System.out.println(bitSet.cardinality());
        System.out.println(bitSet.length());
        //for (byte b : bitSet.toByteArray()) {
        //    System.out.println(b);
        //}
    }

}