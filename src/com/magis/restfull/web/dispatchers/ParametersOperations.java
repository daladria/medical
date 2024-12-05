
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
import com.magis.restfullAndWeb.utilities.ConstructResponse;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.DateTimeUtilities;

public class ParametersOperations {
	protected final Log logger = LogFactory.getLog(getClass());	
	static LinkedHashMap<String, Object> responseCommandNotFound=null;
	DateTimeUtilities du = new DateTimeUtilities();

	
	public static LinkedHashMap<String, Object>  dispatch(ApplicationParameters parameters,LinkedHashMap<String, Object> request,  HashMap<String,Object> user,ObjectMapper obm) throws Exception{

		if (((String)request.get("command")).equals("params.setParam")) return setParameter(parameters, request,user,obm);
		return responseCommandNotFound;
	}
	
	public static LinkedHashMap<String, Object> setParameter(ApplicationParameters parameters, Map<String, Object> request, HashMap<String, Object> user,ObjectMapper obm){
		LinkedHashMap<String, Object>  response =null;
		
		try {	
			response =ConstructResponse.constructResponse("success","application.contentUpdates.updateDone", "application.contentUpdates.updateDone");
			
			List <Object> sqlParams = new ArrayList<Object>();
			sqlParams.add(Sql.crons.SET_APPLICATION_PARAMETER);
			sqlParams.add(request.get("value"));
			sqlParams.add(request.get("key"));

			parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
			parameters.getParameters().put( (String) request.get("key"), (String)request.get("value"));
		} catch (Exception e) {
			response =ConstructResponse.constructResponse("failed","application.contentUpdates.updateFailed", "application.contentUpdates.updateFailed");
		}
		return response;
	}
	

}
