package com.wangcl;

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
@SpringBootApplication
public class WangApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WangApplication.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WangApplication.class, args);
        Environment env = context.getBean(Environment.class);
        logger.info("""
                                                
                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
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
        return application.sources(WangApplication.class);
    }
}
