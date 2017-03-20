package woosun.common.encrypt.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptUtils {

	public static String getMd5Hash(String inputStr) {
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

	
	public static String getSha256Hash(String inputStr) {
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
