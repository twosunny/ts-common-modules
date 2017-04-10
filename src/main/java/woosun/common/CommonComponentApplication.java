package woosun.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonComponentApplication {

	public static void main(String[] args) {
		/* set static init
		initStatic();
		*/
		SpringApplication.run(CommonComponentApplication.class, args);
	}
	
	public static void initStatic(){
		
		/* Set EncryptKeyStore 
		EncryptKeyStore.addKey("userKey", "1234567890123456");
		EncryptKeyStore.addKey("tokenKey", "abcdefghijklmnop");
		
		/* Set Cache
		CacheHelper.addProperties(new CacheSetProperties("cacheName1",10,10,10));
		CacheHelper.addProperties(new CacheSetProperties("cacheName2",10,10,10));
		*/
	}
	
}
