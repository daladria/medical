package com.magis.utilities;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256 {
    private static String key = "e8ffc7e56311679f12b6fc91aa77a5eb"; // 256 bit key
    //private static String key = "e8ffc7e56311679f"; // 128 bit key
    private static String initVector = "b45ea2f4667026ec"; // 16 bytes IV

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
           // System.out.println("encrypted string: "
//                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

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
 
        String testData = "deneme için text file data vs vs vs";	
        //String key1 = "e8ffc7e56311679f12b6fc91aa77a5eb"; // 32 bytes key
//        System.out.println(key1.length());
//        byte[] keyBytes = key1.getBytes("UTF-8");

//        byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; //IV
        AES256  crypto  = new AES256();
        String dec = crypto.encrypt(testData);
        
        System.out.println("Decrypted:"  + dec);
        System.out.println("Encrypted:" + crypto.decrypt(dec));
        
        System.out.println("\r\n\r\n");
        
        System.out.println("Username:" + crypto.encrypt("carrefoursa"));
        System.out.println("Password:" + crypto.encrypt("123456"));

        System.out.println("\r\n\r\n");
        
        System.out.println("Username For Development:" + crypto.encrypt("mobileappusr"));
        System.out.println("Password For Development:" + crypto.encrypt("TYvuhyg_87uk8"));
        
        System.out.println("\r\n\r\n");
        
        System.out.println("Username For Production:" + crypto.encrypt("prodmobileusr"));
        System.out.println("Password For Production:" + crypto.encrypt("ksd_23RTs"));
        
        System.out.println("\r\n\r\n");
        
        System.out.println("Username For Teknosa Local:" + crypto.encrypt("teknosa"));
        System.out.println("Password For Teknosa Local:" + crypto.encrypt("123456"));
    }
}