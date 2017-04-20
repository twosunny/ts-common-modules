package woosun.common.jta.configuration;

import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.orm.jpa.vendor.Database;

public class AtomikosJtaPlatform extends AbstractJtaPlatform{

	private static final long serialVersionUID = 1L;

	static TransactionManager transactionManager;
	static UserTransaction transaction;

	@Override
	protected TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return transaction;
	}
	
	public static XADataSource getXADataSource(DataSourceProperties dataSourceProperties){
		XADataSource datasource = null;
		
		if(Database.H2.equals(dataSourceProperties.getDatabase())){
			JdbcDataSource h2JdbcDataSource = new JdbcDataSource();
			h2JdbcDataSource.setURL(dataSourceProperties.getUrl());
			h2JdbcDataSource.setUser(dataSourceProperties.getUser());
			h2JdbcDataSource.setPassword(dataSourceProperties.getPassword());
			
			datasource = h2JdbcDataSource;
		}
		
		return datasource;
	}
}
