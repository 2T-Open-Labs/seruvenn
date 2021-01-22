package com.ikite.seruvenn.DemoConsumerMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.ikite.seruvenn")
@PropertySource({"classpath:/application_DemoConsumerMS.properties"})
@EntityScan({"com.ikite.seruvenn"})
public class DenemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenemeApplication.class, args);
	}
	
}
