package com.galaxy.mecury.java.deep.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.galaxy.mecury.java.deep.config.property.DBProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("com.galaxy.mecury.java.deep.dao")
public class DBConfig {
    /**
     * 配置数据源
     *
     * @date 2018/6/24
     **/
    @Bean
    public DataSource dataSource(DBProperty dbProperty) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(dbProperty.getUsername());
        dataSource.setPassword(dbProperty.getPassword());
        dataSource.setUrl(dbProperty.getUrl());
        dataSource.setDriverClassName(dbProperty.getDriverClassName());
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(50);
//        dataSource.setMaxEvictableIdleTimeMillis(30000L);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        return dataSource;
    }

    /**
     * 配置数据源
     *
     * @date 2018/6/24
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, DBProperty dbProperty) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 动态获取SqlMapper
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:db/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(resourcePatternResolver.getResource("classpath:db/mybatisConfig.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);

        return dataSourceTransactionManager;
    }
}
