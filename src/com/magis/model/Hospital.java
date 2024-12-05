package com.magis.model;

public class Hospital {
	String hospitalId="";
	String hospitalName="";
	String hospitalAddress="";
	String hospitalPhone="";
	String hospitalEmail="";
	boolean isDeleted=false;
	
	
	
	public Hospital(Object hospitalId, Object hospitalName, Object hospitalAddress, Object hospitalPhone,
			Object hospitalEmail, Object isDeleted) {
		super();
		this.hospitalId = (hospitalId==null) ? "" : "" + hospitalId;
		this.hospitalName = (hospitalName==null) ? "" : "" + hospitalName;
		this.hospitalAddress = (hospitalAddress==null) ? "" : "" + hospitalAddress;
		this.hospitalPhone = (hospitalPhone==null) ? "" : "" + hospitalPhone;
		this.hospitalEmail = (hospitalEmail==null) ? "" : "" + hospitalEmail;
		this.isDeleted = (isDeleted==null) ? false : Boolean.parseBoolean("" + isDeleted);
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	public String getHospitalPhone() {
		return hospitalPhone;
	}
	public void setHospitalPhone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}
	public String getHospitalEmail() {
		return hospitalEmail;
	}
	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
