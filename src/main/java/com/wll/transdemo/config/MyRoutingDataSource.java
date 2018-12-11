package com.wll.transdemo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by WLL on 2018-12-10 21:17
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DataSourceHolder.get();
        return datasource;
    }
}
