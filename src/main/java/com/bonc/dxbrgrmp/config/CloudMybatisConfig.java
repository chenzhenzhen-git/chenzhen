package com.bonc.dxbrgrmp.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Auther: lgf
 * @Date: 2019/12/23
 * @Description: com.bonc.dxbrgrmp.config
 * @version: 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.bonc.dxbrgrmp.dao.mybatis.cloudmapper"},
        sqlSessionTemplateRef = "cloudiipSqlSessionTemplate")
public class CloudMybatisConfig {

    @Bean(name = "cloudiipDataSource")
    //@Primary //必须加此注解，不然报错，下一个类则不需要添加
    @ConfigurationProperties(prefix = "cloudiip.datasource") // prefix值必须是application.properteis中对应属性的前缀
    public DataSource cloudiipDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory cloudiipSqlSessionFactory(@Qualifier("cloudiipDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:cloud-mybatis/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate cloudiipSqlSessionTemplate(@Qualifier("cloudiipSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); //
        // 使用上面配置的Factory
        return template;     }

}
