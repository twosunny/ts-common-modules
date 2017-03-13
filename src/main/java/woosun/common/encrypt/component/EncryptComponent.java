package woosun.common.encrypt.component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import woosun.common.convert.component.ObjectConvertComponent;
import woosun.common.encrypt.configuration.EncryptProperties;


@Component
public class EncryptComponent {
	
	@Autowired
	private ObjectConvertComponent objectConvertComponent;
	
	@Autowired
	private EncryptProperties encryptProperties;
	
	private Key KEY;
	private final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private final String HEXES = "0123456789abcdef";
	private Cipher encryptor;
	private Cipher decryptor;
	
	@PostConstruct
	public void init() {
		KEY = new SecretKeySpec(encryptProperties.getKey().getBytes(), "AES");
		try {
			encryptor = Cipher.getInstance(ALGORITHM);
			decryptor = Cipher.getInstance(ALGORITHM);

			encryptor.init(Cipher.ENCRYPT_MODE, KEY);
			decryptor.init(Cipher.DECRYPT_MODE, KEY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
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

	
	public String getMd5Hash(Object obj){
		String json = objectConvertComponent.objectToJson(obj);
		return getMd5Hash(json);
	}
	
	public String getSha256Hash(Object obj){
		String json = objectConvertComponent.objectToJson(obj);
		return getSha256Hash(json);
	}

	public String getMd5Hash(String inputStr) {
		MessageDigest m = null;

		try {
			m = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (m != null) {
			byte[] data;

			try {
				data = inputStr.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
			m.update(data, 0, data.length);
			BigInteger i = new BigInteger(1, m.digest());

			return String.format("%1$032x", i);
		}

		return null;
	}
	
	public String getSha256Hash(String inputStr) {
		MessageDigest m = null;

		try {
			m = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (m != null) {
			byte[] data;

			try {
				data = inputStr.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
			m.update(data, 0, data.length);
			BigInteger i = new BigInteger(1, m.digest());

			return String.format("%1$064x", i);
		}

		return null;
	}
	
}
