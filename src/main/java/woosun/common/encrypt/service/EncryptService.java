package woosun.common.encrypt.service;

public interface EncryptService {

	public String textEncrypt(String inputStr);
	
	public String textDecrypt(String inputStr);
	
	public String getMd5Hash(Object obj);
	
	public String getSha256Hash(Object obj);
	
	public String getMd5Hash(String inputStr);
	
	public String getSha256Hash(String inputStr);
	
}
