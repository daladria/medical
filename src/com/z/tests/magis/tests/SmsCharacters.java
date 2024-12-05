package com.z.tests.magis.tests;

public class SmsCharacters {

	public static int check7Bit(String text) {
		char character ;
		int ascii ;
		for (int i=0;i<text.length();i++) {
			character = text.charAt(i); // This gives the character 'a'
			ascii = (int) character;
			if (ascii<0 || ascii>127) return 1;
		}
		return 0;
	}

	
	public static void main(String[] args) {
		SmsCharacters sc = new SmsCharacters();
		String text ="@5deneme ÇçÖşŞİĞi الأسود، စီးပွားရေး";
		
		System.out.println(sc.check7Bit(text));
		System.out.println(sc.check7Bit("acv34455:@"));
	}

}
