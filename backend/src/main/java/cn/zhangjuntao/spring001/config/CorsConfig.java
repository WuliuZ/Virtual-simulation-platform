package cn.zhangjuntao.spring001.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问源请求头
        corsConfiguration.addAllowedMethod("*");//设置访问源请求方法
        //对接口配置跨域设置
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }
}

