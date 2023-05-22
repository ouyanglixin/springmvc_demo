package com.oyy.reggie.common;

/**
 * 基于ThreadLocal 封装工具雷 ，用户保存和获取当前用户的id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private static void setCurrentId (Long id) {
        threadLocal.set(id);
    }

    private static long getCurrentId () {
        return threadLocal.get();
    }

}
