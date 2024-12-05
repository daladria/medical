package com.magis.utilities;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class AES128 {
    private static String key = "e8ffc7e56311679f"; // 128 bit key
    private static byte[] keyBytes= null;
    private static String initVector = "b45ea2f4667026ec"; // 16 bytes IV
    private static byte[] initVectorBytes= null;

    public static byte[] getInitVectorHash() throws UnsupportedEncodingException {
    	if (initVectorBytes!=null) return initVectorBytes;
    	if (keyBytes==null) getKeyHash();
    	byte[] transform = initVector.getBytes("UTF-8");
    	int int1,int2=0;
    	byte byte1,byte2;
    	for (int i=0;i<transform.length;i++) {
    		int1= transform[i];
    		int2= int1 % (i +3) + 9;
    		byte1=(byte) int1;
    		byte2 = (byte) int2;
    		byte2= (byte) (byte1 ^ byte2); //XOR
    		byte2 = (byte) (byte2 ^ keyBytes[i]); //XOR
    		transform[i]= byte2;
    		
    //		System.out.println(byte2);
    	}
    	initVectorBytes = transform;
    	return initVectorBytes;
    }
    
    public static byte[] getKeyHash() throws UnsupportedEncodingException {
    	if (keyBytes!=null) return keyBytes;
    	byte[] transform = key.getBytes("UTF-8");
    	int int1,int2=0;
    	byte byte1,byte2;
    	for (int i=0;i<transform.length;i++) {
    		int1= transform[i];
    		int2= int1 % (i +16) + 17;
    		byte1=(byte) int1;
    		byte2 = (byte) int2;
    		byte2= (byte) (byte1 ^ byte2); //XOR
    		transform[i]= byte2;
    		
   // 		System.out.println(byte2);
    	}
    	keyBytes = transform;
    	return transform;
    }
    
    public static String encrypt(String value) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(getKeyHash(), "AES");
        	IvParameterSpec iv = new IvParameterSpec(getInitVectorHash());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
   
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    
    public static String decrypt(String encrypted) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(getKeyHash(), "AES");
        	IvParameterSpec iv = new IvParameterSpec(getInitVectorHash());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
 
        String testData = "For Test text file data etc etc etc şĞ ç Ü İi";	

        AES128  crypto  = new AES128();
        String dec = crypto.encrypt(testData);
        
        long start = System.currentTimeMillis();
        System.out.println("Encrypted:"  + dec);
        System.out.println("Finished:" + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        System.out.println("Decrypted:" + crypto.decrypt(dec));
        System.out.println("Finished:" + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();

        System.out.println(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(DigestUtils.md5(("a").getBytes())));
        System.out.println("get Key Hash:" + getKeyHash());
        System.out.println("get Key Hash:" + getKeyHash());
    }
}