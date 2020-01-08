package com.bonc.dxbrgrmp;

import com.bonc.dxbrgrmp.common.utils.resttemplate.WrppedRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.bonc.dxbrgrmp.*")
public class DxbrgrmpApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DxbrgrmpApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DxbrgrmpApplication.class,args);
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        pool.setCorePoolSize(1);
        // 线程池维护线程的最大数量
        pool.setMaxPoolSize(10);
        // 当调度器shutdown被调用时等待当前被调度的任务完成
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
    @Bean
    public WrppedRestTemplate wrppedRestTemplate() {
        return new WrppedRestTemplate();
    }

    @Bean
    @DependsOn(value = {"wrppedRestTemplate"})
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(wrppedRestTemplate());
    }
}
