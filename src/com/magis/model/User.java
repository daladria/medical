package com.magis.model;

public class User {

	String userName="";

	String test="";
	
	String password ="";
	String name="";
	String surname="";
	String roleName="";
	String roles="";
	String email="";
	String avatar="bos.png";

	String company="";
	String phone="";
	String mobilePhone="";
	boolean isDeleted=false;
	

	

	public User(Object userName, Object password, Object name, Object surname, Object roleName, 
			Object roles, Object email, Object avatar, Object company, Object phone, Object mobilePhone, Object isDeleted) {
		super();

		this.userName = (userName==null) ? "": "" + userName;
		this.password =  (password==null) ? "": "" + password;
		this.name =  (name==null) ? "": "" + name;
		this.surname =  (surname==null) ? "": "" + surname;
		this.roleName =  (roleName==null) ? "": "" + roleName;
		this.roles =  (roles==null) ? "": "" + roles;
		this.email =  (email==null) ? "": "" + email;
		this.avatar =  (avatar==null) ? "": "" + avatar;
		this.company =  (company==null) ? "": "" + company;
		this.phone =  (phone==null) ? "": "" + phone;
		this.mobilePhone =  (mobilePhone==null) ? "": "" + mobilePhone;
		this.isDeleted =  (isDeleted==null) ? false: (boolean) isDeleted;

	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

	
}
