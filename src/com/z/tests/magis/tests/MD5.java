package com.z.tests.magis.tests;

import java.time.ZoneId;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class MD5 {

	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());
		System.out.println(ZoneId.getAvailableZoneIds());
		//Calendar d = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.getAvailableZoneIds().));
		//System.out.println(d.getTimeInMillis());
		String password = "Message Nerden Geliyor deme Bak"; 
		byte[] key = {(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04,(byte)0x05,(byte)0x06};
		byte[] iv = {(byte)0x10,(byte)0x20,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
		
		byte[] md5Hex = DigestUtils.md5(password);
		System.out.println(Base64.encode(md5Hex));
		
		IvParameterSpec zeroIv = new IvParameterSpec(iv);

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		
		Cipher desCipher;
		// Create the cipher
		desCipher = Cipher.getInstance("des/cbc/pkcs5padding");
		desCipher.init(Cipher.ENCRYPT_MODE, desKey,zeroIv);
		byte[] encryptedData = desCipher.doFinal(password.getBytes());
		String enc = Base64.encode(encryptedData);
		System.out.println("Encrypted Hex:" + enc);
		System.out.println("IV:" + Hex.encodeHexString(desCipher.getIV()));
		
		
		Cipher ecipher = Cipher.getInstance("des/cbc/pkcs5padding");
		ecipher.init(Cipher.DECRYPT_MODE, desKey,zeroIv);
		encryptedData = ecipher.doFinal(Base64.decode(enc));
		System.out.println("Decrypted Data:" + new String(encryptedData));
		
	}	

}
