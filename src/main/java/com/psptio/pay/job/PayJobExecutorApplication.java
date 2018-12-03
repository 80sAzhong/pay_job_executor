package com.psptio.pay.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"com.psptio.pay.job","com.psptio.reliable.message"})
@EnableAsync
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
public class PayJobExecutorApplication   extends SpringBootServletInitializer {
private static final Logger logger = (Logger) LoggerFactory.getLogger(PayJobExecutorApplication.class);
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PayJobExecutorApplication.class);
	}
	public static void main(String[] args) {
		logger.debug("pay customer starting........................");
		SpringApplication.run(PayJobExecutorApplication.class, args);
	}
}
