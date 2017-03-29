package woosun.common.authentication.cookie.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("common-auth.cookie")
public class CookieAuthenticationProperties {

	private String hash = "sha256";

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
