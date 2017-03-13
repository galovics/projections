package com.arnoldgalovics.blog;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.arnoldgalovics.blog.domain")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.arnoldgalovics.blog.service")
public class Configuration {
}
