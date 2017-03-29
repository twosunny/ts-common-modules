package woosun.common.authentication.session.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("common-auth.session")
public class SessionAuthenticationProperties {

	private String redisHost = "localhost";
	private int redisPort = 6379;
	private String redisPassword = null;
	private int redisTimeout = 1000;
	private int redisPoolMaxTotal = 8;
	private int redisPoolMaxIdle = 8;
	private int redisPoolMinIdle = 1;
	private int redisPoolMaxWaitMillis = 1000;
	
	public String getRedisHost() {
		return redisHost;
	}
	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}
	public int getRedisPort() {
		return redisPort;
	}
	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}
	public String getRedisPassword() {
		return redisPassword;
	}
	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}
	public int getRedisTimeout() {
		return redisTimeout;
	}
	public void setRedisTimeout(int redisTimeout) {
		this.redisTimeout = redisTimeout;
	}
	public int getRedisPoolMaxTotal() {
		return redisPoolMaxTotal;
	}
	public void setRedisPoolMaxTotal(int redisPoolMaxTotal) {
		this.redisPoolMaxTotal = redisPoolMaxTotal;
	}
	public int getRedisPoolMaxIdle() {
		return redisPoolMaxIdle;
	}
	public void setRedisPoolMaxIdle(int redisPoolMaxIdle) {
		this.redisPoolMaxIdle = redisPoolMaxIdle;
	}
	public int getRedisPoolMinIdle() {
		return redisPoolMinIdle;
	}
	public void setRedisPoolMinIdle(int redisPoolMinIdle) {
		this.redisPoolMinIdle = redisPoolMinIdle;
	}
	public int getRedisPoolMaxWaitMillis() {
		return redisPoolMaxWaitMillis;
	}
	public void setRedisPoolMaxWaitMillis(int redisPoolMaxWaitMillis) {
		this.redisPoolMaxWaitMillis = redisPoolMaxWaitMillis;
	}
	

	
}
