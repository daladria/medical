
package com.magis.restfull.web.dispatchers;
//<img src="http://localhost/sodexo/getImage.html?pictid=90ae26f0-5eff-41ec-a853-f9d981070f34"/>

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magis.constants.ReturnMessages;
import com.magis.constants.Sql;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.Hospital;
import com.magis.model.ResultSetModel;
import com.magis.model.User;
import com.magis.restfullAndWeb.utilities.ConstructResponse;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.DateTimeUtilities;
import com.magis.utilities.UserUtilities;

public class SystemParameterOperations {
	protected final Log logger = LogFactory.getLog(getClass());	
	static LinkedHashMap<String, Object> responseCommandNotFound=null;
	DateTimeUtilities du = new DateTimeUtilities();

	
	public static LinkedHashMap<String, Object>  dispatch(ApplicationParameters parameters,LinkedHashMap<String, Object> request,  HashMap<String,Object> user,ObjectMapper obm) throws Exception{

		if (((String)request.get("command")).equals("systemParameters.getHospital")) return getHospital(parameters, request,user,obm);
		return responseCommandNotFound;
	}
	
	public static LinkedHashMap<String, Object> getHospital(ApplicationParameters parameters, Map<String, Object> request, HashMap<String, Object> user,ObjectMapper obm){
		LinkedHashMap<String, Object>  response =null;
		Hospital item= null;

		try {	
			response =ConstructResponse.constructResponse("success","application.contentUpdates.updateDone", "application.contentUpdates.updateDone");
			List <Object> sqlParams = new ArrayList<Object>();
			sqlParams.add(Sql.systemParameters.SELECT_HOSPITAL_BY_ID);
			sqlParams.add(request.get("key"));
			List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, true, parameters.getHttpTimeout());
			item= new Hospital(cmps.get(0).get("hospital_id"), cmps.get(0).get("hospital_name"), cmps.get(0).get("hospital_address"), 
					cmps.get(0).get("hospital_phone"), cmps.get(0).get("hospital_email"), cmps.get(0).get("is_deleted"));

		} catch (Exception e) {
			item= new Hospital("", "", "", "", "", false);
		}
		response.put("item", item);
		return response;
	}
	

}
