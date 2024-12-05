package com.magis.model;

public class SystemParams {
	String id="";
	String key="";
	
	
	
	public SystemParams(Object id, Object key) {
		super();
		this.id = (id==null) ? "" : "" + id;
		this.key = (key==null) ? "" : "" + key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
