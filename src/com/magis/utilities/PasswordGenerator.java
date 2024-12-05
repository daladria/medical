package com.magis.utilities;

import java.util.Random;

public class PasswordGenerator {
    //final static char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();
    final static char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();

	public static void main(String[] args) throws Exception {
		for (int i=0;i<100;i++)
			System.out.println(generateOTP(16));
	}
	public static String generateOTP(int digit) {
		StringBuilder password = new StringBuilder(); 		
		Random random = new Random();
		for (int i=0;i<digit;i++)
			 password.append(allAllowed[random.nextInt(allAllowed.length)]);
		return password.toString();
	}

}
