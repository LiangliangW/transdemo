//package com.wll.transdemo.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
///**
// * Created by WLL on 2018/10/23 10:54
// */
//@Configuration
//@EnableConfigurationProperties
//@MapperScan(basePackages = "com.wll.transdemo.mapper")
//public class DataSourceJTAUserConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-user")
//    @Primary
//    public DataSource dataSourceJTAUser() {
//        return new AtomikosDataSourceBean();
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionFactory jtaUserSqlSessionFactory(@Qualifier("dataSourceJTAUser") DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
//        bean.setTypeAliasesPackage("com.wll.transdemo.mapper");
//        return bean.getObject();
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionTemplate jtaUserSqlSessionTemplate(
//            @Qualifier("jtaUserSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}