package com.ikite.seruvenn.CETypeClassMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.ikite.seruvenn")
@EnableCaching
@PropertySource({"classpath:/application_cetypeclassmap.properties"})
@EntityScan({"com.ikite.seruvenn"})
public class CETypeClassMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(CETypeClassMapApplication.class, args);
		
	}

}
