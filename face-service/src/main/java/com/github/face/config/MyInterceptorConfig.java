package com.github.face.config;

import com.github.face.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 自定义拦截器,放行接口
 *
 * @author wo
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private OpenProperties openProperties;

    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtValidateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(openProperties.getUri());
    }

}

