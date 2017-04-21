package woosun.common.authentication.session.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJdbcHttpSession
public class JdbcHttpSessionConfiguration {
	
	@Autowired
	@Qualifier("sessionDataSource") 
	DataSource dataSource;

	
	@Primary
    @Bean("sessionTransactionManager")
    @Qualifier("sessionTransactionManager")
    public PlatformTransactionManager sessionTransactionManager() {
            return new DataSourceTransactionManager(dataSource); 
    }
    

}
