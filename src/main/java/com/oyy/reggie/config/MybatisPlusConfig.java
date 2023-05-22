package com.oyy.reggie.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置MP
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 添加分页拦截
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor () {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
