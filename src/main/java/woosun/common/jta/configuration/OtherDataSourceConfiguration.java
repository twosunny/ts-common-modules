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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackages = "woosun.common.jta.repository", 
    entityManagerFactoryRef = "otherEntityManagerFactory",
    transactionManagerRef = "transactionManager"
    )
public class OtherDataSourceConfiguration {
	
	@Bean(name = "otherDataSourceProperties")
	@ConfigurationProperties("other.datasource")
	public DataSourceProperties gameDataSourceProperties() {
	    return new DataSourceProperties();
	}

	
	@Bean(name = "otherDataSource")
	@ConfigurationProperties("other.datasource")
	public org.apache.tomcat.jdbc.pool.DataSource gameDataSource() {
		
		return (org.apache.tomcat.jdbc.pool.DataSource)gameDataSourceProperties().
			initializeDataSourceBuilder().
			type(org.apache.tomcat.jdbc.pool.DataSource.class).
			build();
	    	
	}
	
    @Bean(name = "otherEntityManagerFactory")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean otherEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("gameDataSource") DataSource dataSource) {
    	
    	HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		
		LocalContainerEntityManagerFactoryBean entityManager = 
    			builder
                .dataSource(dataSource)
                .packages("woosun.common.jta.entity")
                .persistenceUnit("other")
                .properties(properties)
                .build();
    	
    	return entityManager;
    	
    }
	
}
