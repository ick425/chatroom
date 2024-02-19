package org.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author wo
 */
@SpringBootApplication
public class ChatRoomApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ChatRoomApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("============> 系统启动成功！后台地址：http://localhost:" + environment.getProperty("server.port"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatRoomApplication.class);
    }
}
