package com.smn.el.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.*;
import org.apache.commons.net.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptionUtil {

	private static final byte[] DEFAULT_KEYVAL = new byte[] { 'o', 'd', 'E', 'x', 'I', 'n', 'd', 'i', 'a', 'P', 'v',
			't', 'L', 't', 'd', '7' };
	private static Key DEFAULT_KEY;
	private static Cipher DEFAULT_CIPHER;

	/**
	 * @Method used for encrypt data
	 * @param String plainText
	 * @return String Intgers
	 * @throws Exception
	 */
	/*
	 * public static String encrypt(String plainText) throws Exception {
	 * log.info("Entering encrypt()..");
	 * 
	 * Cipher cipher; Key key = generateKey(keyValue); cipher =
	 * Cipher.getInstance("AES"); byte[] plainTextByte = plainText.getBytes();
	 * cipher.init(Cipher.ENCRYPT_MODE, key); byte[] encryptedByte =
	 * cipher.doFinal(plainTextByte); String encryptedText =
	 * Base64.encodeBase64URLSafeString(encryptedByte);
	 * 
	 * log.info("Existing encrypt().."); return encryptedText; }
	 */ // ddedss
	/**
	 * @Method used for encrypt data
	 * @param String plainText
	 * @return String
	 * @throws Exception
	 */

	public static String encrypt(String plainText) throws Exception {
		// log.info("Entering encrypt()..");

		if (DEFAULT_KEY == null) {
			DEFAULT_KEY = generateKey(DEFAULT_KEYVAL);
		}

		if (DEFAULT_CIPHER == null) {
			DEFAULT_CIPHER = Cipher.getInstance("AES");
			DEFAULT_CIPHER.init(Cipher.ENCRYPT_MODE, DEFAULT_KEY);
		}

		// log.info("Existing encrypt()..");
		return Base64.encodeBase64URLSafeString(DEFAULT_CIPHER.doFinal(plainText.getBytes()));

	}

	public static String generateKeyValue(String keyValue) {
		log.info("Entering generateKeyValue()..");

		if (keyValue.length() >= 16) {
			keyValue = keyValue.substring(0, 16);
		} else if (keyValue.length() < 16) {
			keyValue = keyValue.substring(0, keyValue.length());
			Integer count = 16 - keyValue.length();

			for (int i = 1; i <= count; i++) {
				keyValue = keyValue + "A";
			}
		}

		log.info("Existing generateKeyValue()..");
		return keyValue;
	}

	/**
	 * @Method used for decrypt data
	 * @param String encryptedText
	 * @return String decryptedText
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText) throws Exception {
		log.info("Entering decrypt()..");

		String decryptedText = null;
		try {
			Cipher cipher;
			Key key = generateKey(DEFAULT_KEYVAL);
			cipher = Cipher.getInstance("AES");
			byte[] encryptedTextByte = Base64.decodeBase64(encryptedText.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
			decryptedText = new String(decryptedByte);

		} catch (IllegalBlockSizeException e) {
			log.error("EncryptionUtil.decrypt() :" + e.getMessage());
			throw e;
		}

		log.info("Exiting decrypt()..");
		return decryptedText;
	}

	/**
	 * @Method used for decrypt data
	 * @param String keyValue
	 * @param String encryptedText
	 * @return String decryptedText
	 * @throws Exception
	 */

	/**
	 * @Method used for generate key for encryption and decryption
	 * @param byte[] keyValue
	 * @return Key key
	 * @throws Exception
	 */
	private static Key generateKey(byte[] keyValue) throws Exception {
		// log.info("Entering generateKey()..");

		Key key = new SecretKeySpec(keyValue, "AES");

		// log.info("Exiting generateKey()..");
		return key;
	}

	/**
	 * @method used for decryption of type integer
	 * @param String encId
	 * @return Integer decId
	 * @throws Exception
	 */
	public static Integer decryptInt(String encId) throws Exception {
		log.info("Entering decryptId().. ");

		Integer id = null;
		if (StringUtils.isNotBlank(encId)) {
			id = Integer.parseInt(decrypt(encId));
		}

		log.info("Exiting decryptId().. ");
		return id;
	}

	/**
	 * @method used for decryption of type Long
	 * @param String encId
	 * @return Long decId
	 * @throws Exception
	 */
	public static Long decryptLong(String encId) throws Exception {

		log.info("Entering decryptId().. ");

		Long id = null;
		if (StringUtils.isNotBlank(encId)) {
			id = Long.parseLong(decrypt(encId));
		}

		log.info("Exiting decryptId().. ");
		return id;

	}

	/**
	 * @Method used for generate key for encryption
	 * @param byte[]  keyValue
	 * @param Boolean keyReq
	 * @return Key key
	 * @throws Exception
	 */
	public static Key generateKey(byte[] keyVal, Boolean keyReq) throws Exception {
		log.info("Entering generateKey()..");

		Key key;
		if (keyReq) {
			key = new SecretKeySpec(keyVal, "AES");
		} else {
			key = new SecretKeySpec(DEFAULT_KEYVAL, "AES");
		}

		log.info("Exiting generateKey()..");
		return key;
	}

	/**
	 * @Method used for encrypt data
	 * @param String plainText
	 * @param Key    key
	 * @return String
	 * @throws Exception
	 */
	public static String encrypt(Key key, String plainText) throws Exception {
		log.info("Entering encrypt()..");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		log.info("Existing encrypt()..");
		return Base64.encodeBase64URLSafeString(cipher.doFinal(plainText.getBytes()));
	}

}