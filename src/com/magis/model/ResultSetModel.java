package com.magis.model;

import java.sql.Array;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResultSetModel {
	Map<String,Object> params = new HashMap<String, Object>();
	protected final Log logger = LogFactory.getLog(getClass()); 
	
	public Object get(String parameter) {
		Object result;
		try {
			 result =params.get(parameter.toUpperCase(Locale.ENGLISH));
		} catch (Exception e) {
			logger.error("Error:",e);
			result =null;
		}
		return result; 
	}

	public Object getArray(String parameter) {
		Object result;
		try {
			 //result =params.get(parameter.toUpperCase(Locale.ENGLISH));
			 result = ((Array) params.get(parameter.toUpperCase(Locale.ENGLISH))).getArray();
		} catch (Exception e) {
			//logger.error("Error:",e);
			result =null;
		}
		return result; 
	}
	
	public Set<String> keySet(){
		return params.keySet();
	}

	public void add(String parameterName,Object parameterValue){
		if(parameterValue==null) parameterValue =null;
		params.put(parameterName.toUpperCase(Locale.ENGLISH), parameterValue);
	}
	
	public void remove(String parameter) {
		try {
			params.remove(parameter.toUpperCase(Locale.ENGLISH));
		} catch (Exception e) {
			logger.error("Error:",e);
		}
	}

	public Map<String, Object> getParams() {
		return params;
	}

	
}





/*
package com.magis.model;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResultSetModel {
	Map<String,String> params = new LinkedHashMap<String, String>();
	protected final Log logger = LogFactory.getLog(getClass()); 
	
	public String get(String parameter) {
		String result;
		try {
			 result =params.get(parameter.toUpperCase(Locale.ENGLISH));
		} catch (Exception e) {
			logger.error("Error:",e);
			result =null;
		}
		return result; 
	}
	
	public void add(String parameterName,String parameterValue){
		if(parameterValue==null) parameterValue ="";
		params.put(parameterName.toUpperCase(Locale.ENGLISH), parameterValue);
	}
	
	public void remove(String parameter) {
		try {
			params.remove(parameter.toUpperCase(Locale.ENGLISH));
		} catch (Exception e) {
			logger.error("Error:",e);
		}
	}

	public Map<String, String> getParams() {
		return params;
	}

	
}
*/