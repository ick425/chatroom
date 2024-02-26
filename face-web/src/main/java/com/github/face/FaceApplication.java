package com.github.face;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author wo
 */
@EnableMethodCache(basePackages = "com.github.face")
@MapperScan("com.github.**.mapper")
@SpringBootApplication
public class FaceApplication extends SpringBootServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FaceApplication.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(FaceApplication.class, args);
        Environment env = context.getBean(Environment.class);
        LOGGER.info("""
                                                
                        ----------------------------------------------------------
                        \t项目 '{}' 启动成功！:
                        \tLocal:    http://localhost:{}
                        \tExternal: http://{}:{}
                        \tDoc:      http://{}:{}/doc.html
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FaceApplication.class);
    }
}
