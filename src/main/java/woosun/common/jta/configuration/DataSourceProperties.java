package woosun.common.jta.configuration;

import org.springframework.orm.jpa.vendor.Database;

import com.atomikos.datasource.pool.ConnectionFactory;
import com.atomikos.jdbc.AbstractDataSourceBean;

public class DataSourceProperties extends AbstractDataSourceBean {
	
	
	private static final long serialVersionUID = 1L;
	
	private String url;
	private String user;
	private String password;
	private Database database;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	@Override
	protected ConnectionFactory doInit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isAssignableFromWrappedVendorClass(Class<?> iface) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object unwrapVendorInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
