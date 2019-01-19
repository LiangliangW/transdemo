package com.wll.transdemo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wll.transdemo.config.DataSourceHolder;
import com.wll.transdemo.config.DataSourceTypeEnum;

/**
 * Created by WLL on 2018-12-23 11:05
 * 如果没有事务，不管是select还是update操作，该用哪个数据源就用哪个数据源，在DAO层自己识别去；
 * 如果有事务，必须加 @Transactional 注解，所有数据源全部走主库，用识别注解实现。
 * 如果不加 @Transactional 注解判断控制，则进入方法第一个数据源是哪个就会选择哪个
 */
@Aspect
@Component
public class DataSourceAop {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.wll.transdemo.mapper.user..*.insert*(..))" + "execution(* com.wll.transdemo.mapper.user..*.update*(..))")
    public void setJtaUserDataSource() {
        DataSourceHolder.cleanDataSource();
        DataSourceHolder.setDataSource(DataSourceTypeEnum.USER.getName());
        logger.info("切换到数据源：" + DataSourceHolder.getDataSource());
    }

    @Before("execution(* com.wll.transdemo.mapper.user..*.select*(..))" + "execution(* com.wll.transdemo.mapper.user..*.get*(..))")
    public void setJtaUserSlaveDataSource() {
        DataSourceHolder.cleanDataSource();
        DataSourceHolder.setDataSource(DataSourceTypeEnum.USER_SLAVE.getName());
        logger.info("切换到数据源：" + DataSourceHolder.getDataSource());
    }

    @Before("execution(* com.wll.transdemo.mapper.income..*.insert*(..))" + "execution(* com.wll.transdemo.mapper.income..*.update*(..))")
    public void setJtaIncomeDataSource() {
        DataSourceHolder.cleanDataSource();
        DataSourceHolder.setDataSource(DataSourceTypeEnum.INCOME.getName());
        logger.info("切换到数据源：" + DataSourceHolder.getDataSource());
    }

    @Before("execution(* com.wll.transdemo.mapper.income..*.select*(..))" + "execution(* com.wll.transdemo.mapper.income..*.get*(..))")
    public void setJtaIncomeSlaveDataSource() {
        DataSourceHolder.cleanDataSource();
        DataSourceHolder.setDataSource(DataSourceTypeEnum.INCOME_SLAVE.getName());
        logger.info("切换到数据源：" + DataSourceHolder.getDataSource());
    }

//    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
//    public Object setTransactionalLock(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//        Object target = proceedingJoinPoint.getTarget();
//        try {
//            Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
//            Transactional transactional = currentMethod.getAnnotation(Transactional.class);
//            if (transactional != null) {
//                DataSourceHolder.setIsLocked(true);
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        Object result = proceedingJoinPoint.proceed();
//        DataSourceHolder.setIsLocked(false);
//        return result;
//    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setTransactionalLockTrue() {
        DataSourceHolder.setIsLocked(true);
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setTransactionalLockFalse() {
        DataSourceHolder.setIsLocked(false);
    }
}