package com.wll.transdemo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wll.transdemo.config.DataSourceHolder;
import com.wll.transdemo.config.DataSourceTypeEnum;

/**
 * Created by WLL on 2018-12-23 11:05
 */
@Aspect
@Component
public class DataSourceAop {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.wll.transdemo.mapper.user..*.insert*(..))" + "execution(* com.wll.transdemo.mapper.user..*.update*(..))")
    public void setJtaUserDataSource() {
        DataSourceHolder.clean();
        DataSourceHolder.setThreadLocal(DataSourceTypeEnum.USER.getName());
        logger.info("切换到数据源：" + DataSourceHolder.get());
    }

    @Before("execution(* com.wll.transdemo.mapper.user..*.select*(..))" + "execution(* com.wll.transdemo.mapper.user..*.get*(..))")
    public void setJtaUserSlaveDataSource() {
        DataSourceHolder.clean();
        DataSourceHolder.setThreadLocal(DataSourceTypeEnum.USER_SLAVE.getName());
        logger.info("切换到数据源：" + DataSourceHolder.get());
    }

    @Before("execution(* com.wll.transdemo.mapper.income..*.insert*(..))" + "execution(* com.wll.transdemo.mapper.income..*.update*(..))")
    public void setJtaIncomeDataSource() {
        DataSourceHolder.clean();
        DataSourceHolder.setThreadLocal(DataSourceTypeEnum.INCOME.getName());
        logger.info("切换到数据源：" + DataSourceHolder.get());
    }

    @Before("execution(* com.wll.transdemo.mapper.income..*.select*(..))" + "execution(* com.wll.transdemo.mapper.income..*.get*(..))")
    public void setJtaIncomeSlaveDataSource() {
        DataSourceHolder.clean();
        DataSourceHolder.setThreadLocal(DataSourceTypeEnum.INCOME_SLAVE.getName());
        logger.info("切换到数据源：" + DataSourceHolder.get());
    }
}
