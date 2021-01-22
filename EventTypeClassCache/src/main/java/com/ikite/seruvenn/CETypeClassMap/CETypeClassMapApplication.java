package com.ikite.seruvenn.CETypeClassMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableCaching
@PropertySource({"classpath:/application_cetypeclassmap.properties"})
public class CETypeClassMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(CETypeClassMapApplication.class, args);
	}

}
