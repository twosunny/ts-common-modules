package woosun.common.jta.configuration;


import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;


@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackages = "woosun.common.jta.repository", 
    entityManagerFactoryRef = "accountEntityManagerFactory", 
    transactionManagerRef = "transactionManager"
    )
public class AccountDataSourceConfiguration implements DataSourceConfiguration {
	
	@Bean("accountDataSourceProperties")
	@Qualifier("accountDataSourceProperties")
	@ConfigurationProperties(prefix = "account.datasource")
	@PostConstruct
	public DataSourceProperties getDataSourceProperties(){
		return new DataSourceProperties();
	}
	

	@Primary
	@Bean("accountDataSource")
	@Qualifier("accountDataSource")
	public DataSource getDataSource(
			@Qualifier("accountDataSourceProperties") DataSourceProperties dataSourceProperties) {
				
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(AtomikosJtaPlatform.getXADataSource(dataSourceProperties));
		xaDataSource.setUniqueResourceName(dataSourceProperties.getUniqueResourceName());
		xaDataSource.setMaxPoolSize(dataSourceProperties.getMaxPoolSize());
		xaDataSource.setBorrowConnectionTimeout(dataSourceProperties.getBorrowConnectionTimeout());
		xaDataSource.setMaxIdleTime(dataSourceProperties.getMaxIdleTime());
		xaDataSource.setMaxPoolSize(dataSourceProperties.getMaxPoolSize());
		xaDataSource.setMinPoolSize(dataSourceProperties.getMinPoolSize());
		xaDataSource.setTestQuery(dataSourceProperties.getTestQuery());
		
		return xaDataSource;
	    	
	}
	
	@Primary
    @Bean("accountEntityManagerFactory")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
    		EntityManagerFactoryBuilder builder,
    		@Qualifier("accountDataSource") DataSource datasource) {
    	
    	HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
    	
    	LocalContainerEntityManagerFactoryBean entityManager = 
    			builder
                .dataSource(datasource)
                .packages("woosun.common.jta.entity")
                .persistenceUnit("account")
                .properties(properties)
                .jta(true)
                .build();

    	return entityManager;
    }
	
}
