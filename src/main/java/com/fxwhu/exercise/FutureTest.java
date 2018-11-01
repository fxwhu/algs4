package com.fxwhu.exercise;

import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author: fengxuan
 * @create 2018-10-27 下午12:27
 **/
public class FutureTest {

    public static void main(String[] args) {
        //testCompleteFuture();
        testFuture();
    }


    //测试 future 能否捕获异常
    public static void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> submit = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LoggerUtil.ROOT.info("====");
            getException();
            return 100;
        });

        try {
            Integer result = submit.get();
            LoggerUtil.ROOT.info("result:", result);
        } catch (Exception e) {
            LoggerUtil.ROOT.info("future result error", e);
        }

        LoggerUtil.ROOT.info("====");
    }


    //测试 competleFuture 能否捕获异常
    public static void testCompleteFuture() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LoggerUtil.ROOT.info("====");
            getException();
            return 100;
        });
        try {
            Integer result = future.get();
            LoggerUtil.ROOT.info("completeFuture result:", result);
        } catch (Exception e) {
            LoggerUtil.ROOT.info("result error", e);
        }

        LoggerUtil.ROOT.info("====");
    }


    public static void getException() {
        int i = 100 / 0;
    }
}
