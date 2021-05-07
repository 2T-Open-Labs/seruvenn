package com.ikite.seruvenn.CETypeClassMap.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.ikite.seruvenn.CETypeClassMap.repository", 
		entityManagerFactoryRef = "cetypeclassmapEntityManagerFactory", 
		transactionManagerRef = "cetypeclassmapTransactionManager")
public class CETypeclassmapDataSourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.mysql")
	@Qualifier("mysqlDataSource")
	public DataSourceProperties cetypeclassmapDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.mysql.configuration")
	public DataSource cetypeclassmapDataSource() {
		return cetypeclassmapDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Primary
	@Bean(name = "cetypeclassmapEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cetypeclassmapEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(cetypeclassmapDataSource()).packages("com.ikite.seruvenn.CETypeClassMap.model").build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager cetypeclassmapTransactionManager(
			final @Qualifier("cetypeclassmapEntityManagerFactory") LocalContainerEntityManagerFactoryBean cetypeclassmapEntityManagerFactory) {
		return new JpaTransactionManager(cetypeclassmapEntityManagerFactory.getObject());
	}

}
