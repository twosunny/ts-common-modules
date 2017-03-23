package woosun.common.encrypt.component;

import java.util.HashMap;
import java.util.Map;

public class EncryptKeyStore {

	private static Map<String,String> encryptKeyPair = new HashMap<String,String>();
	
	public static void addKey(String storeName, String key){
		encryptKeyPair.put(storeName, key);
	}


	public static Map<String, String> getEncryptKeyPair() {
		return encryptKeyPair;
	}
	
}
