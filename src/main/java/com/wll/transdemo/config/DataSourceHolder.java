package com.wll.transdemo.config;

/**
 * Created by WLL on 2018-12-10 21:18
 * 加锁确保 service 层如果规定走主库则 DAO 层改不了
 * 坑：threadlocal 是线程相关的，只能用一次
 */
public class DataSourceHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static ThreadLocal<Boolean> isLocked = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    public static Boolean getIsLocked() {
        return isLocked.get();
    }

    public static void setIsLocked(Boolean locked) {
        isLocked.set(locked);
    }

    public static void setDataSource(String name){
        threadLocal.set(name);
    }

    public static String getDataSource(){
        return threadLocal.get();
    }

    public static void cleanDataSource(){
        threadLocal.remove();
    }
}
