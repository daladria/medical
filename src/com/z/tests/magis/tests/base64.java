package com.z.tests.magis.tests;

import java.util.Base64;





public class base64 {
	public static void main(String[] args) throws Exception  {
		String str = Base64.getEncoder().encodeToString(("deneme").getBytes());
		System.out.println(str);
		str = new String(Base64.getDecoder().decode(str));
		System.out.println(str);
	}
}
