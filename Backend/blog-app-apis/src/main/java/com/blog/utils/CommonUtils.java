package com.blog.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

@Service
public class CommonUtils {
	
	public byte[] getEncryptPassword(String password) throws Exception {
		//Creating KeyPair generator object
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	      
	      //Initializing the key pair generator
	      keyPairGen.initialize(2048);
	      
	      //Generate the pair of keys
	      KeyPair pair = keyPairGen.generateKeyPair();   
	      
	      //Getting the public key from the key pair
	      PublicKey publicKey = pair.getPublic();  

	      //Creating a Cipher object
	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

	      //Initializing a Cipher object
	      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		  
	      //Add data to the cipher
	      byte[] input = password.getBytes();	  
	      cipher.update(input);
		  
	      //encrypting the data
	      byte[] cipherText = cipher.doFinal();
		return cipherText;	
	}
	
	public String getDecryptedPassword(byte[] encryptedPassword) throws Exception{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	      
	      //Initializing the key pair generator
	      keyPairGen.initialize(2048);
	      
	      //Generate the pair of keys
	      KeyPair pair = keyPairGen.generateKeyPair();   
	      
	      //Getting the public key from the key  pair
	      PublicKey publicKey = pair.getPublic();  
		//Initializing the same cipher for decryption
		 Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	     cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
	     //Decrypting the text
	      byte[] decipheredText = cipher.doFinal(encryptedPassword);
		return new String(decipheredText, "UTF-8");
	}

}
