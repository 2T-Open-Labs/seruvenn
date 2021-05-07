package com.ikite.seruvenn.SagaOrchestrator.configurator;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.ikite.seruvenn.SagaOrchestrator.repository", 
		entityManagerFactoryRef = "sagaOrchestratorEntityManagerFactory", 
		transactionManagerRef = "sagaOrchestratorTransactionManager")
public class SagaOrchestratorDataSourceConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.mysql")
	@Qualifier("mysqlDataSource")
	public DataSourceProperties sagaOrchestratorDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.mysql.configuration")
	public DataSource sagaOrchestratorDataSource() {
		return sagaOrchestratorDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "sagaOrchestratorEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean sagaOrchestratorEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(sagaOrchestratorDataSource()).packages("com.ikite.seruvenn.SagaOrchestrator.model").build();
	}
	
	@Bean
	public PlatformTransactionManager sagaOrchestratorTransactionManager(
			final @Qualifier("sagaOrchestratorEntityManagerFactory") LocalContainerEntityManagerFactoryBean sagaOrchestratorEntityManagerFactory) {
		return new JpaTransactionManager(sagaOrchestratorEntityManagerFactory.getObject());
	}

}
