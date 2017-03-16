package woosun.common.encrypt.component;

import java.util.HashMap;
import java.util.Map;

public class EncryptKeyStore {

	private Map<String,String> encryptKeyPair = new HashMap<String,String>();
	
	public void addKey(String storeName, String key){
		encryptKeyPair.put(storeName, key);
	}

	public Map<String, String> getEncryptKeyPair() {
		return encryptKeyPair;
	}

	public void setEncryptKeyPair(Map<String, String> encryptKeyPair) {
		this.encryptKeyPair = encryptKeyPair;
	}
	
	
}
