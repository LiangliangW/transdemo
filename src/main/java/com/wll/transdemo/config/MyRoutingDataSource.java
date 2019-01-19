package com.wll.transdemo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by WLL on 2018-12-10 21:17
 * RoutingDataSource 有默认数据源，必须设主库为默认
 * 如果想强制走主库，则 determineCurrentLookupKey 方法返回空，自动走默认主库
 * 避免了多数据源不能由一个确定的尴尬
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        if (DataSourceHolder.getIsLocked() == false) {
            return DataSourceHolder.getDataSource();
        } else {
            return null;
        }
    }
}
