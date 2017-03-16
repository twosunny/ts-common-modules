package woosun.common.encrypt.service;

public interface EncryptService {
	
	public void setKey(String key);

	public String textEncrypt(String inputStr);
	
	public String textDecrypt(String inputStr);
	
}
