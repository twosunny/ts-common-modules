package woosun.common.jta.configuration;


import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class OtherRepositoryConfiguration {
	
	@Autowired
	@Qualifier("otherDataSource") 
	private DataSource datasource;
	
    @Bean("otherEntityManagerFactory")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
    		EntityManagerFactoryBuilder builder) {
    	
    	HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
    	
    	LocalContainerEntityManagerFactoryBean entityManager = 
    			builder
                .dataSource(datasource)
                .packages("woosun.common.jta.entity")
                .persistenceUnit("other")
                .properties(properties)
                .jta(true)
                .build();

    	return entityManager;
    }

}
