package com.magis.utilities;

import java.io.UnsupportedEncodingException;

public class AESTestClass {

	public static void main(String[] args) throws UnsupportedEncodingException {

	   String testData = "For Test text file data etc etc etc şĞ ç Ü İi";	

	   String dec = AES128.encrypt(testData);
	  
        long start = System.currentTimeMillis();
        System.out.println("Encrypted:"  + dec);
        System.out.println("Finished:" + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        System.out.println("Decrypted:" + AES128.decrypt(dec));
        System.out.println("Finished:" + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        
		System.out.println("get Key Hash:" + AES128.getKeyHash());
        System.out.println("get Key Hash:" + AES128.getKeyHash());

        System.out.println("get Init Vector Hash:" + AES128.getInitVectorHash());
        System.out.println("get Init Vector Hash:" + AES128.getInitVectorHash());

	}

}
