package woosun.common.authentication.session.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.DefaultLettucePool;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.StringUtils;

import redis.embedded.RedisServer;

@EnableRedisHttpSession
public class HttpSessionConfig {
	
	@Autowired
	private SessionAuthenticationProperties authenticationProperties;
	
	@PostConstruct
	private void init(){
		if(authenticationProperties.isUseEmbeddedRedis()){
			RedisServer redisServer;
			try {
				redisServer = new RedisServer(6379);
				redisServer.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		
		LettuceConnectionFactory lettuceConnectionFactory = null;
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(authenticationProperties.getRedisPoolMaxTotal());
		poolConfig.setMaxIdle(authenticationProperties.getRedisPoolMaxIdle());
		poolConfig.setMinIdle(authenticationProperties.getRedisPoolMinIdle());
		poolConfig.setMaxWaitMillis(authenticationProperties.getRedisPoolMaxWaitMillis());
		
		
		DefaultLettucePool lettucePool = new DefaultLettucePool(
				authenticationProperties.getRedisHost(), 
				authenticationProperties.getRedisPort(),
				poolConfig);
		lettucePool.afterPropertiesSet();
		
		lettuceConnectionFactory = new LettuceConnectionFactory(lettucePool);
		
		String passwd = authenticationProperties.getRedisPassword();
		if(!StringUtils.isEmpty(passwd)){
			lettuceConnectionFactory.setPassword(passwd);
		}
		
		return lettuceConnectionFactory;
		
	}
}
