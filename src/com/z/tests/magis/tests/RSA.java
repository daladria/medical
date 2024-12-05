package com.z.tests.magis.tests;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class RSA {
  public static void main(String[] args) throws Exception {
	  
	try {
		System.out.println("------- GENERATE PUBLIC AND PRIVATE KEY --------");
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		
		System.out.println("\r\n" + keyPair.getPublic().toString() + "\r\n\r\n");
		
		PublicKey publicKey= keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("Public Key:" + publicKey);
		System.out.println("Private Key:" + privateKey);
		
		System.out.println("\n------- PULLING OUT PARAMETERS --------");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		RSAPrivateKeySpec rsaPrivateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		
		BigInteger publicKeyModulus = rsaPublicKeySpec.getModulus();
		BigInteger publicKeyExponent = rsaPublicKeySpec.getPublicExponent();
		BigInteger privateKeyModulus =  rsaPrivateKeySpec.getModulus();
		BigInteger privateKeyExponent = rsaPrivateKeySpec.getPrivateExponent();
		
		System.out.println("Public Key Modulus:" +  privateKeyModulus);
		System.out.println("Public Key Exponent:" + privateKeyExponent);
		System.out.println("Private Key Modulus:" +  publicKeyModulus);
		System.out.println("Private Key Exponent:" + publicKeyExponent);
		
		System.out.println("\n------------ ENCYPTION COMING SOON -------------------");
		String data = "Text to be Encypted";
		data = "Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted";
		RSAPublicKeySpec rsaPublicKeySpec_2 = new RSAPublicKeySpec(publicKeyModulus, publicKeyExponent);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		PublicKey pubKey = fact.generatePublic(rsaPublicKeySpec_2);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		String b64encStr =  Base64.encode(encryptedData);
		System.out.println("Encypted Data:" + b64encStr);
		
		System.out.println("\n------------ DECRYPTION COMING SOON -------------------");
		RSAPrivateKeySpec rsaPrivateKeySpec2 = new RSAPrivateKeySpec(privateKeyModulus, privateKeyExponent);
		KeyFactory fact2 = KeyFactory.getInstance("RSA");
		PrivateKey privKey =  fact2.generatePrivate(rsaPrivateKeySpec2);
		Cipher cipherPriv = Cipher.getInstance("RSA");
		cipherPriv.init(Cipher.DECRYPT_MODE,privKey);
		byte[] descryptedData= cipherPriv.doFinal(Base64.decode(b64encStr));
		System.out.println("Decrypted Data:" + new String(descryptedData));
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

  
	
  public static void main1(String[] args) throws Exception {
  
	try {
		System.out.println("------- GENERATE PUBLIC AND PRIVATE KEY --------");
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(20480);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey= keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("Public Key:" + publicKey);
		System.out.println("Private Key:" + privateKey);
		
		System.out.println("\n------- PULLING OUT PARAMETERS --------");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		RSAPrivateKeySpec rsaPrivateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		
		BigInteger publicKeyModulus = rsaPublicKeySpec.getModulus();
		BigInteger publicKeyExponent = rsaPublicKeySpec.getPublicExponent();
		BigInteger privateKeyModulus =  rsaPrivateKeySpec.getModulus();
		BigInteger privateKeyExponent = rsaPrivateKeySpec.getPrivateExponent();
		
		System.out.println("Public Key Modulus:" +  privateKeyModulus);
		System.out.println("Public Key Exponent:" + privateKeyExponent);
		System.out.println("Private Key Modulus:" +  publicKeyModulus);
		System.out.println("Private Key Exponent:" + publicKeyExponent);
		
		System.out.println("\n------------ ENCYPTION COMING SOON -------------------");
		String data = "Text to be Encypted";
		data = "Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted Text to be Encypted";
		RSAPublicKeySpec rsaPublicKeySpec_2 = new RSAPublicKeySpec(publicKeyModulus, publicKeyExponent);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		PublicKey pubKey = fact.generatePublic(rsaPublicKeySpec_2);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		String b64encStr =  Base64.encode(encryptedData);
		System.out.println("Encypted Data:" + b64encStr);
		
		System.out.println("\n------------ DECRYPTION COMING SOON -------------------");
		RSAPrivateKeySpec rsaPrivateKeySpec2 = new RSAPrivateKeySpec(privateKeyModulus, privateKeyExponent);
		KeyFactory fact2 = KeyFactory.getInstance("RSA");
		PrivateKey privKey =  fact2.generatePrivate(rsaPrivateKeySpec2);
		Cipher cipherPriv = Cipher.getInstance("RSA");
		cipherPriv.init(Cipher.DECRYPT_MODE,privKey);
		byte[] descryptedData= cipherPriv.doFinal(Base64.decode(b64encStr));
		System.out.println("Decrypted Data:" + new String(descryptedData));
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

}

/*
System.out.println("Public Key Modulus:" +  rsaPublicKeySpec.getModulus());
System.out.println("Public Key Exponent:" + rsaPublicKeySpec.getPublicExponent());
System.out.println("Private Key Modulus:" +  rsaPrivateKeySpec.getModulus());
System.out.println("Private Key Exponent:" + rsaPrivateKeySpec.getPrivateExponent());
*/