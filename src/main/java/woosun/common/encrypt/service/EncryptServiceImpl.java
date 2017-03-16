package woosun.common.encrypt.service;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class EncryptServiceImpl implements EncryptService {
	
	private String KEY_STRING;
	private Key KEY;
	private final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private final String HEXES = "0123456789abcdef";
	private Cipher encryptor;
	private Cipher decryptor;
	
	private void init() {
		KEY = new SecretKeySpec(KEY_STRING.getBytes(), "AES");
		try {
			encryptor = Cipher.getInstance(ALGORITHM);
			decryptor = Cipher.getInstance(ALGORITHM);

			encryptor.init(Cipher.ENCRYPT_MODE, KEY);
			decryptor.init(Cipher.DECRYPT_MODE, KEY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void setKey(String key) {
		this.KEY_STRING = key;
		init();
		
	}

	@Override
	public String textEncrypt(String inputStr) {
		byte[] encodeBytes = null;
		try {
			encodeBytes = encryptor.doFinal(inputStr.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return getHex(encodeBytes);
	}

	@Override
	public String textDecrypt(String inputStr) {
		byte[] decodeBytes = null;
		try {
			decodeBytes = decryptor.doFinal(Hex.decodeHex(inputStr
					.toCharArray()));
			return new String(decodeBytes, "UTF-8").trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		StringBuilder hex = new StringBuilder(2 * raw.length);
		for (byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}

		return hex.toString();
	}

}
