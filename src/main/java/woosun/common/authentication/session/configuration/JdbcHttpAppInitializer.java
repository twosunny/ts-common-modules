package woosun.common.authentication.session.configuration;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class JdbcHttpAppInitializer extends AbstractHttpSessionApplicationInitializer {
	public JdbcHttpAppInitializer() {
		super(JdbcHttpSessionConfiguration.class);
	}
} 
