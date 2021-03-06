package com.yzz.service_edu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServiceEduApplication
 * @Author yky
 * @Date 2020/12/25
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yzz")
//服务注册
@EnableDiscoveryClient
//服务调用
@EnableFeignClients(basePackages = "com.yzz")
public class ServiceEduApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceEduApplication.class, args);
	}
}
