package woosun.common.jta.configuration;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public interface DataSourceConfiguration {

	public DataSourceProperties getDataSourceProperties();
	
	public DataSource getDataSource(DataSourceProperties dataSourceProperties);
	
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			DataSource datasource);
}
