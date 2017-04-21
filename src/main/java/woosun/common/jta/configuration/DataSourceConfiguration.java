package woosun.common.jta.configuration;

import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AbstractDataSourceBean;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
public class DataSourceConfiguration {
	
	@Primary
	@Bean("sessionDataSourceProperties")
	@Qualifier("sessionDataSourceProperties")
	@ConfigurationProperties(prefix = "session.datasource")
	public DataSourceProperties sessionDataSourceProperties(){
		return new DataSourceProperties();
	}
	
	
	@Primary
	@Bean("sessionDataSource")
	@Qualifier("sessionDataSource")
	@ConfigurationProperties(prefix = "session.datasource")
	public org.apache.tomcat.jdbc.pool.DataSource sessionDataSource(
			@Qualifier("sessionDataSourceProperties") DataSourceProperties dataSourceProperties) {

		return (org.apache.tomcat.jdbc.pool.DataSource)DataSourceBuilder
				.create()
				.build();
	    	
	}

	
	@Bean("accountDataSourceProperties")
	@Qualifier("accountDataSourceProperties")
	@ConfigurationProperties(prefix = "account.datasource")
	public DataSourceProperties accountDataSourceProperties(){
		return new DataSourceProperties();
	}
	
	
	@Bean("accountDataSource")
	@Qualifier("accountDataSource")
	@ConfigurationProperties(prefix = "account.datasource")
	public AbstractDataSourceBean accountDataSource(
			@Qualifier("accountDataSourceProperties") DataSourceProperties dataSourceProperties) {
				
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(
				(XADataSource)dataSourceProperties.initializeDataSourceBuilder().build());
		
		return xaDataSource;
	    	
	}
	
	@Bean("gameDataSourceProperties")
	@Qualifier("gameDataSourceProperties")
	@ConfigurationProperties(prefix = "other.datasource")
	public DataSourceProperties gameDataSourceProperties(){
		return new DataSourceProperties();
	}
	
	
	@Bean("gameDataSource")
	@Qualifier("gameDataSource")
	@ConfigurationProperties(prefix = "other.datasource")
	public AbstractDataSourceBean gameDataSource(
			@Qualifier("gameDataSourceProperties") DataSourceProperties dataSourceProperties) {
				
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(
				(XADataSource)dataSourceProperties.initializeDataSourceBuilder().build());
		
		return xaDataSource;
	    	
	}
	
}
