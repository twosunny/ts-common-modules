package woosun.common.jta.configuration;


import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackages = "woosun.common.jta.repository", 
    entityManagerFactoryRef = "accountEntityManagerFactory", 
    transactionManagerRef = "transactionManager"
    )
public class AccountDataSourceConfiguration {
	
	@Bean(name = "accountDataSourceProperties")
	@ConfigurationProperties("account.datasource")
	@Primary
	public DataSourceProperties accountDataSourceProperties() {
	    return new DataSourceProperties();
	}

	
	@Bean(name = "accountDataSource")
	@ConfigurationProperties("account.datasource")
	@Primary
	public org.apache.tomcat.jdbc.pool.DataSource accountDataSource() {
		
		return (org.apache.tomcat.jdbc.pool.DataSource)accountDataSourceProperties().
			initializeDataSourceBuilder().
			type(org.apache.tomcat.jdbc.pool.DataSource.class).
			build();
	    	
	}
	
	
    @Bean(name = "accountEntityManagerFactory")
    @DependsOn("transactionManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("accountDataSource") DataSource dataSource) {
    	
    	HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
    	
    	LocalContainerEntityManagerFactoryBean entityManager = 
    			builder
                .dataSource(dataSource)
                .packages("woosun.common.jta.entity")
                .persistenceUnit("account")
                .properties(properties)
                .build();
    	
    	return entityManager;
    }
	
}
