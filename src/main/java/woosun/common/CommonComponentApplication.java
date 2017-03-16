package woosun.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonComponentApplication.class, args);
	}
	
	/* Set EncryptKeyStore 
	@Bean
	public EncryptKeyStore getKeyStore(){
		
		EncryptKeyStore encryptKeyStore = new EncryptKeyStore();
		encryptKeyStore.addKey("userKey", "1234567890123456");
		encryptKeyStore.addKey("tokenKey","abcdefghijklmnop");
		
		return encryptKeyStore;
	}
	*/
	
	/* Set Cache
	@Bean
	public CacheHelper getCacheHelper(){
		
		CacheHelper cacheHelper = new CacheHelper();
		cacheHelper.addProperties(new CacheSetProperties("cacheName1",10,10,10));
		cacheHelper.addProperties(new CacheSetProperties("cacheName2",20,20,20));
		
		return cacheHelper;
	}
	*/
}
