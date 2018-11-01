package com.fxwhu.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: fengxuan
 * @create 2018-10-27 下午1:44
 **/
public class LoggerUtil {

    private final static String ROOT_LOGGER = "root";

    public static Logger ROOT = LoggerFactory.getLogger(ROOT_LOGGER);
}
