package woosun.common.encrypt.component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import woosun.common.encrypt.service.EncryptService;
import woosun.common.encrypt.service.EncryptServiceImpl;

@Component
public class EncryptServiceStore {
	
	private Map<String, EncryptService> serviceStore = new HashMap<String, EncryptService>();

	@PostConstruct
	private void init(){
		
		try{
			
			Map<String,String> keyPair = EncryptKeyStore.getEncryptKeyPair();
			
			if(keyPair.size() != 0){
				for(String storeName : keyPair.keySet()){
					EncryptService encryptService = new EncryptServiceImpl();
					encryptService.setKey(keyPair.get(storeName));
					serviceStore.put(storeName, encryptService);
				}
			}
			
		}catch(Exception e){
			return;
		}
		
	}
	
	public EncryptService getEncryptService(String storeName){
		return serviceStore.get(storeName);
	}
	
	public void addEncryptService(String storeName, EncryptService encryptService){
		this.serviceStore.put(storeName, encryptService);
	}
}
