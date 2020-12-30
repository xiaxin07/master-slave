package com.xiaxin.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.xiaxin.utils.DbUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Import(DbConfig.class)
@Configuration
public class DruidConfig {

    public final static String MAPPER_XML_PATH = "classpath:mybatis/mapper/*.xml";

    @ConfigurationProperties(prefix = "master.datasource")
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }


    @Bean
    public PlatformTransactionManager txManager(DataSource dbFactoryBean) {
        return new DataSourceTransactionManager(dbFactoryBean);
    }


    @ConfigurationProperties(prefix = "slave.datasource")
    @Bean
    public DataSource slaveDataSource(){
        return  new DruidDataSource();
    }


    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        Map<Object,Object> map=new HashMap<>();
        map.put(DbUtil.master,masterDataSource());
        map.put(DbUtil.slave,slaveDataSource());
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dbFactoryBean) throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dbFactoryBean);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
        return sqlSessionTemplate;
    }
}
