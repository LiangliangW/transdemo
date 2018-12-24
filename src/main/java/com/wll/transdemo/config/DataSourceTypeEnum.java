package com.wll.transdemo.config;

import lombok.Getter;

/**
 * Created by WLL on 2018-12-10 21:24
 */
@Getter
public enum DataSourceTypeEnum {
    USER("user"),
    INCOME("income"),
    USER_SLAVE("user-slave"),
    INCOME_SLAVE("income-slave");

    private String name;

    DataSourceTypeEnum(String name) {
        this.name = name;
    }
}
