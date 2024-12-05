package com.z.tests.magis.tests;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAPub2Priv2 {
  public static void main(String[] args) throws Exception {
  
	try {
		

		String base64Public ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl4MTmzxHGDi1VybA6R3VIhxEkOFXRR/e8UV8l6+tLeTW1y9cYy6IBkSwR84udy3nriImSQdoP36XvS4MtH1fkdTQo5/dX+jYFJ4Be54+wEd6HarQSpCiJ0yMNcYaH+FFNjwipeUIDl2YmC0uQPMMZcM/yXHR9/ygZiOZ00hL7CA6W4pvlaXQvtRo9SHMUJFftldRfKwf3fpUHmd79ntB5jphGK0LwSKPw6bb8aCSjIOXp6liN+DS6h8cUM8atXQyincWxTcJ+vbNtiiiZR1xMpYfeOK2rIcFxBUcBJ1oKUYc8eYmOe8uFYTwiAfhZsO/Ffenk/4kJ5Zwov/feoB8AQIDAQAB";
		String base64Private ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXgxObPEcYOLVXJsDpHdUiHESQ4VdFH97xRXyXr60t5NbXL1xjLogGRLBHzi53LeeuIiZJB2g/fpe9Lgy0fV+R1NCjn91f6NgUngF7nj7AR3odqtBKkKInTIw1xhof4UU2PCKl5QgOXZiYLS5A8wxlwz/JcdH3/KBmI5nTSEvsIDpbim+VpdC+1Gj1IcxQkV+2V1F8rB/d+lQeZ3v2e0HmOmEYrQvBIo/DptvxoJKMg5enqWI34NLqHxxQzxq1dDKKdxbFNwn69s22KKJlHXEylh944rashwXEFRwEnWgpRhzx5iY57y4VhPCIB+Fmw78V96eT/iQnlnCi/996gHwBAgMBAAECggEAS5rF6t3OYEDUBLQ66VjQpR/T+zPnN4Aq4NyaypqIYXFi36grgvaSPAaErKAvQZtvk85IqoJ+u8hw8Z8MCfjR+tRmdG/uqNBpGwiplsUlbQ992VYGcF7x+4hQIvlCygvAXMrvQzchqQF9htpszU47LoCa0OvyKbwvUiiLtwnJkP7RDZgP4RFKhMUGEiPEJhRWo9ttVhEI9Qhu4Lai4WiedWoIyrBXR7x0jXoaAJR4DlaZL7/tnAh1pa03bBKUPwm00rG+zBo/uAtR/47TMjDrgjNt0eoCGZ4QuXbEWywtETd6SaXzLDMDnTCn4MTguyANK+0w76PllrLiVWbPyxqt/QKBgQDmLEm+uWkd0fG5jBIVjgqgsQPNwqE++I/0vvHGxxQfco3lsyQoAWxZQvX8hMx8LX9jRftymEWRZ3R+T1RMxZSkeDUdlnMC2+JdNUw93ZJbRs8FordoHJGQ3jECquTsH+NybaEkyY7ec9N/hVRKu2xqIGJPupZjKaXlhANBLWGDLwKBgQCog0EeIXeDnr3rDwZRaV5/JH81h1hkNfZ+tRQoddnpAG75wECxJdydbReco4zu92RGHf1VOUcncSf/emY01uT3GMonWoQnCrQDc7oTmGlrbPTTA4St6yFsipbAbrARSGOR940SZLAAeRZIFRFnT0kFGRKjwElVFfPKtLRbNzjnzwKBgQDXS7PLD3jQaNE24lsECHHObuUE8LmMwkk8/+H9MbeT1r39jjwSo4oqUCTxB3KR8KjIPex9+wqPgHqJr0BF80YfNUVRoKmBkhVBZBolf/xnHmuEdPgjDR3nqL2lJh6s8wL3xK3HUDUvxJ2fGSKIKJm3BwWOWBRVOt6vOXUl4QHmBwKBgDER54hqnm7Jbuxf96sFT+seKZzgyLCFtj23ZSNTDh13btlzTmNHbzK4bcPDLGr5x01Ttk123G1z2f3fGpwQZ6Q8p8FF7m2gBENtE6ZZH3mUxMYdpCkw6w68XKG0mbiup6/9sirE/ytkF0TuPZ8jy1OctXzRpYkNV2yTqmFVd1CpAoGALPBber36tAztT1DkQA5iWjlziYjX/bVLpXutzImdkq30y9kZNNEBmEthJK9g6CAjxEPKmAapi25okTQe3RG1k+zxGm1pE6JuOq/ba5POVp+QyfcszFQApMG0tFQ2SlvxzLh7AnyHs4SIZME0ps83Vj/0RcCjLEWBaAbZmeu3E34=";
		
		System.out.println("\n------------ DECRYPTION COMING SOON -------------------");

		String b64encStr ="EfDpLT0ngY2b9dFiDdSFmt/3tfaFc5g1us232sisgg93zk2MUtm3a2HUUPdzvyKSxRaATHhfpbFuWcBWYpwU33UEEXvg9R1n73S86ZCdhpuhyPR/ZlmL+RPL2u7eDGThYZTpJq6DDxwIYvGjwB7h/Rr2/N+UDjIYYnl6XkJ6ZFOEf0dbcWvMWfc9aqrPz1y9hbDJQT3oANj2dZWxTa4OtHN9EwKbj07SShh8Ihjob7lSxqdQaYhdtWN4Rd+3msLXvUSnN6VP43UZlRbcMdMUvjexSbVyGnH921ZsqwesWxwylA5IB8s4F1FoiKz5V279asGvsKs3XDPhMmE1LN/kFA==";


		PKCS8EncodedKeySpec rsaPrivateKeySpec2 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64Private));
		KeyFactory fact2 = KeyFactory.getInstance("RSA");
		PrivateKey privKey =  fact2.generatePrivate(rsaPrivateKeySpec2);
	
		Cipher cipherPriv = Cipher.getInstance("RSA");
		cipherPriv.init(Cipher.DECRYPT_MODE,privKey);

		byte[] descryptedData= cipherPriv.doFinal(Base64.getDecoder().decode(b64encStr));
		System.out.println("Decrypted Data:" + new String(descryptedData));

		String enc ="aaa xxTTzz leeeyn";
		
		Cipher cipher= Cipher.getInstance("RSA");
		cipherPriv.init(Cipher.ENCRYPT_MODE,privKey);

		byte[] encData= cipherPriv.doFinal(enc.getBytes());
		System.out.println("Encrypted Data:" + Base64.getEncoder().encodeToString(encData));

		
		
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