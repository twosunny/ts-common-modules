package woosun.common.authentication.session.configuration;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class RedisAppInitializer extends AbstractHttpSessionApplicationInitializer {
	public RedisAppInitializer() {
		super(RedisHttpSessionConfig.class);
	}
} 
