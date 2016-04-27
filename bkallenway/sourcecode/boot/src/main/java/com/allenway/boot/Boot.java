package com.allenway.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.allenway.*" })
@EntityScan(basePackages = { "com.allenway.*" })
@EnableJpaRepositories(basePackages = { "com.allenway.*" })
public class Boot {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Boot.class, args);
    }
}
