package com.z.tests.magis.tests;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryptor_ege {
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(byte[] key, byte[] initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String decrypt(String key, String initVector, String encrypted) {
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
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        System.out.println("encypted RSA:" + encrypt(key, initVector, "MIICITANBgkqhkiG9w0BAQEFAAOCAg4AMIICCQKCAQEAyS4wjzRyj6awlkCjS2JAgDXGafkxwfVpJLDfonVDXvdZht/f36lhdz6i5rK+e2mU2u5xZKs1URe/8GlAWK5mX+R3L6gDh6LolEow46zRCXMAYuxE9CPOJuCWDLHwCQ0caz2qptvr8cDJGcAQkF00X71V12uOSFuO3csXUftILCYa302ztt+J5AupUAXyGSSb9jLMd6uPXx1ZJi+dodsYpAaqmd8DV//yq7I5t2OMT6bNxpt8k2CAqz8d73pKBHGSswM7GKMW20FbHQS7EDMFKwNh7Zv5DBC+B7FQKS9Zr6QykvZ51KoNImPc6zyf4evR/QuSXI94k7+r0bnjZWz0KQKCAQB+I1LZBzZpMUd4/dAk3Na6TH06Rr9W8EtbcPbXApUaPxZIBah1sNrmjERRR3M9J+boGibIyrwkOzLiRuvMbpTLvRCptbt42MDg8V+T8PAj75IndCKLvMNuBtSt4wcwdaMC7mhINI3MR2cPmSgJjBbhjHH9HnfYkGeUUtktUtNeseyCIjyP6c9EC3l7YTB3YqtS0vamUhwL20kUJ9uzyzTbiVSwkJHVZCj62EB7E6/e9FAPf8/Nv+4eRNnxx6OvLULeyrLVyL60e9/mRLW5nm8UwG2X4hV8971QzW9FzS+APmbJjMwm+0w1xwBX7ilBdGQkAjf01OCcFK6xsauIQxOx"));
        System.out.println(decrypt(key, initVector, "05qMks2RTDVl6uiryFYpuqe9Nc2Y2l2SSdFi0TLeBDk="));
        System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")));
        System.out.println(Base64.encodeBase64String("Hello world".getBytes()));
        
        String key1 = "e8ffc7e56311679f12b6fc91aa77a5eb"; // 32 bytes key
        System.out.println(key1.length());
        byte[] keyBytes = key1.getBytes("UTF-8");

        byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; //IV

        
        String ege ="ebPNFu0g+fqvGJUZm1/+rg==";
        System.out.println(decrypt(keyBytes, ivBytes, ege));
    }
}