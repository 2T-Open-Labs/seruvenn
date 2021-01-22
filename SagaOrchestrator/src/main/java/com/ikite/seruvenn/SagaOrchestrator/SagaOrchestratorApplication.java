package com.ikite.seruvenn.SagaOrchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.ikite.seruvenn")
@PropertySource({"classpath:/application_sagaorchestrator.properties"})
@EntityScan({"com.ikite.seruvenn"})
public class SagaOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaOrchestratorApplication.class, args);
	}

}
