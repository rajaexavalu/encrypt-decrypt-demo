package com.example.demo.encryptiondecryptionservice;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class EncryptDecryptMessageService {
	public static Map<String, Object> map = new HashMap<>();
	
//	@Value("${encryption.publicKeyString}")
//	public String publicKeyString;
//	@Value("${encryption.privateKeyString}")
//	public String privateKeyString;
//	
//	private PublicKey publicKey;
//    private PrivateKey privateKey;
//    


	public void createKeys() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(4096);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			map.put("publicKey", publicKey);
			map.put("privateKey", privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encryptMessage(String plainText) {

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
			PublicKey publicKey = (PublicKey) map.get("publicKey");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encrypt = cipher.doFinal(plainText.getBytes());
			return new String(Base64.getEncoder().encodeToString(encrypt));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String decryptMessage(String encryptedMessage) {

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
			PrivateKey privateKey = (PrivateKey) map.get("privateKey");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
			return new String(decrypt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
