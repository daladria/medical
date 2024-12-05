package com.magis.utilities;

import java.util.Base64;

import org.apache.commons.codec.binary.Hex;

public class PublicAccess {

	public static String encrypt(String data) {
		String resp = "";
		try {
			byte[] tmp = Base64.getEncoder().encode(data.getBytes());
			resp = Hex.encodeHexString(tmp);
			resp = Base64.getEncoder().encodeToString(resp.getBytes());
		} catch (Exception e) {
			resp = "ENC ERROR";
		}
		return resp;
	}

	public static String decrypt(String data) {
		String resp = "";
		try {
			
			byte[] tmp = Base64.getDecoder().decode(data);
			tmp = Hex.decodeHex(new String(tmp));
			tmp = Base64.getDecoder().decode(tmp);
			resp = new String(tmp);
		} catch (Exception e) {
			resp = "DEC ERROR";
		}
		return resp;
	}
	public static void main(String[] args) {
			String username = "testapi";
			String password = "123456";
			String auth = username + "//" + password;
			
			String enc = encrypt(auth);
			System.out.println("Encrypted Value: " + enc);
			String decrypted = decrypt(enc);
			System.out.println("Decrypted Value: " + decrypted);
			System.out.println("------------------------");
			

			username = "BeL_MOB_2021SGS_U";
			password = "aUv?3RV8ugGUqPuPTT6UmhZ#PfL=mUQJ";
			auth = username + "//" + password;
			
			enc = encrypt(auth);
			System.out.println(username + " :Encrypted Value: " + enc);
			decrypted = decrypt(enc);
			System.out.println(username + " :Decrypted Value: " + decrypted);
			System.out.println("------------------------");

			username = "BeL_WEB_2021SGS_U";
			password = "H-TpkF_4QVEg62E!rfGeF8d+Kg%b_zHe";
			auth = username + "//" + password;
			
			enc = encrypt(auth);
			System.out.println(username + " :Encrypted Value: " + enc);
			decrypted = decrypt(enc);
			System.out.println(username + " :Decrypted Value: " + decrypted);
			System.out.println("------------------------");

	}

}
