package com.github.face.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j配置类
 *
 * @author wo
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI docket() {
        return new OpenAPI()
                .info(new Info()
                        .title("swagger文档")
                        .description("引入knife4j，美化样式")
                        .contact(new Contact().name("作者123").url("www.baidu.com").email("xxx@163.com"))
                        .version("v1.0"));
    }

}
