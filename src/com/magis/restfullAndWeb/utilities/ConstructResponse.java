package com.magis.restfullAndWeb.utilities;

import java.util.LinkedHashMap;

public class ConstructResponse {
	
	public  final static LinkedHashMap<String, Object>  constructResponse(String status,String statusDetail, String statusDetailMessage){
		LinkedHashMap<String, Object> responseJson = new LinkedHashMap<String, Object>();
		responseJson.put("status", status);
		responseJson.put("statusDetail", statusDetail);
		responseJson.put("statusDetailMessage", statusDetailMessage);
		return responseJson;
	}

}