package com.ikite.seruvenn.DemoTriggerMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.ikite.seruvenn")
@EntityScan({"com.ikite.seruvenn"})
@PropertySource({"application_DemoTriggerMS.properties"})
public class DemoTriggerMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTriggerMSApplication.class, args);
	}

}
