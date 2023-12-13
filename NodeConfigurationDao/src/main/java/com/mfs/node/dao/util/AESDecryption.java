package com.mfs.node.dao.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class AESDecryption {

	public static boolean checkPassword(String hexa, String key,String password)
			throws IllegalBlockSizeException, BadPaddingException, DecoderException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		boolean result = false;
		final Cipher decryptCipher = Cipher.getInstance("AES");
		decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
		byte[] b = decryptCipher.doFinal(Hex.decodeHex(hexa.toCharArray()));
		String decryptedPassword = new String(b);
		if (decryptedPassword.equals(password)) {
			result = true;
		}

		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// if (encoder.matches(password, key)) {
		// result = true;
		// }

		return result;

	}

	public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for (byte b : key.getBytes(encoding))
				finalKey[i++ % 16] ^= b;
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String encryption(String password,String key) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		final Cipher encryptCipher = Cipher.getInstance("AES");	        				
		encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));		
		String s=new String(Hex.encodeHex(encryptCipher.doFinal(password.getBytes("UTF-8")))); 
		return s;	
	}

}
