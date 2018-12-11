package com.wll.transdemo.config;

/**
 * Created by WLL on 2018-12-10 21:18
 */
public class DataSourceHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void setThreadLocal(String name){
        threadLocal.set(name);
    }

    public static String get(){
        return threadLocal.get();
    }

    public static void clean(){
        threadLocal.remove();
    }
}
