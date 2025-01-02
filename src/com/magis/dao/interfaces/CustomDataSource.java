package com.magis.dao.interfaces;

import org.apache.commons.dbcp2.BasicDataSource;

public class CustomDataSource extends BasicDataSource{

	@Override
	public void setUsername(String userName) {
		// TODO Auto-generated method stub
//		AES256 crypto = new AES256();
//		String un= crypto.decrypt(userName);
		
		super.setUsername(userName);
	}
	
	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
//		AES256 crypto = new AES256();
//		String up= crypto.decrypt(password);
		super.setPassword(password);
	}
	
}
