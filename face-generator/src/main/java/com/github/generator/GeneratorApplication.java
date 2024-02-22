package com.github.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wo
 */
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeneratorApplication.class, args);
        System.out.println("代码已生成！");
    }
}
