package com.ikite.seruvenn.EventManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.ikite.seruvenn")
@EntityScan({"com.ikite.seruvenn"})
@PropertySource({"classpath:/application_eventmanager.properties"})
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

}
