package com.wll.transdemo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by WLL on 2018-12-10 21:08
 */
@Configuration
@EnableConfigurationProperties
@MapperScan(basePackages = "com.wll.transdemo.mapper")
public class DataSourceConfig {
    @Autowired
    private Environment env;

    private final static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);


    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-user")
    @Primary
    public DataSource dataSourceJTAUser() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-income")
    public DataSource dataSourceJTAIncome() {
        return new AtomikosDataSourceBean();
    }


    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        Map<Object,Object> targetDataSource = new HashMap();
        myRoutingDataSource.setDefaultTargetDataSource(dataSourceJTAUser());
        targetDataSource.put(DataSourceTypeEnum.USER.getName(), dataSourceJTAUser());
        targetDataSource.put(DataSourceTypeEnum.INCOME.getName(), dataSourceJTAIncome());
        myRoutingDataSource.setTargetDataSources(targetDataSource);
        return myRoutingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setTransactionFactory(new ManagedTransactionFactory());
        sqlSessionFactoryBean.setDataSource(routingDataSource());
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate jtaIncomeSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManagers() {
        logger.info("-------------------- transactionManager init ---------------------");
        return new DataSourceTransactionManager(routingDataSource());
    }


}
