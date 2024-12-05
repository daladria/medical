package com.magis.session.controller;
 
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magis.constants.ReturnMessages;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.restfull.web.dispatchers.ParametersOperations;
import com.magis.restfull.web.dispatchers.SystemParameterOperations;
import com.magis.restfull.web.dispatchers.UserOperations;
import com.magis.restfullAndWeb.utilities.ConstructResponse;
import com.magis.schedulers.ApplicationParameters;

 
 
@RestController
public class ServiceControllerWithHttp {  
	protected final Log logger = LogFactory.getLog(getClass());
	protected final Log requestResponseLogger = LogFactory.getLog("webLogger");
	private ApplicationParameters parameters =null;
	private final static LinkedHashMap<String,Object> responseCommandNotFound = ConstructResponse.constructResponse("failed", ReturnMessages.command_not_found, ReturnMessages.command_not_found);
	private final static LinkedHashMap<String,Object> responseInvalidToken = ConstructResponse.constructResponse("failed", ReturnMessages.invalid_token, ReturnMessages.invalid_token);
	private final static LinkedHashMap<String,Object> authorizationError = ConstructResponse.constructResponse("failed", ReturnMessages.application_authorization_error, ReturnMessages.application_authorization_error);
	private ExecuteSqls executeSqls = null;

    public ServiceControllerWithHttp() {
	}
    
    @CrossOrigin
	@RequestMapping(value = "/getRequestWithHttp", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    public ResponseEntity<Map<String,Object>> getRequest(HttpSession session,@RequestBody LinkedHashMap<String,Object> request, HttpServletRequest httpRequest,Principal principal,
    		HttpServletResponse httpResponse) {
  	
		LinkedHashMap<String, Object> response =null;
		ObjectMapper obm =  new ObjectMapper();
		long startTime = System.currentTimeMillis();
		try {
			request.put("sessionId", session.getId());
			request.put("clientIp", getRemoteAddr(httpRequest));
			request.put("authorities", ((UsernamePasswordAuthenticationToken)principal).getAuthorities());
			
			response = checkRequest(request, httpResponse,session,obm);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error:", e);
			response =ConstructResponse.constructResponse("failed", ReturnMessages.request_unExpectedError, "");
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);	
		} finally {
           	logRequest(httpRequest,request,response, obm,startTime); 
		}
		
    }

	private LinkedHashMap<String, Object> dispatchRequest(LinkedHashMap<String, Object> request,HashMap<String,Object> user,ObjectMapper obm) {
		try {
//			if (((String)request.get("command")).startsWith("contentUpdates.")) return contentUpdates.dispatch(parameters, request,user);
			
			String userName = "";
			Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();	    
			if (auth1!=null) userName = auth1.getName(); //get logged in username

			
			// call commands before Login on upper
			
			if ( auth1 ==null || userName==null || userName.length() ==0) 
				return ConstructResponse.constructResponse("failed", ReturnMessages.application_userIsNotLoggedIn, "");
			
			request.put("userName", userName);
			if (((String)request.get("command")).startsWith("params.")) return ParametersOperations.dispatch(parameters, request,user,obm);

			if (((String)request.get("command")).startsWith("userOperations.")) return UserOperations.dispatch(parameters, request,user,obm);
			if (((String)request.get("command")).startsWith("systemParameters.")) return SystemParameterOperations.dispatch(parameters, request,user,obm);
				
			// call commands after Login
			//return responseCommandNotFound;
			return null;
		} catch (Exception e) {
			logger.error("Error:", e);
			//e.printStackTrace(new PrintWriter(error));
			return ConstructResponse.constructResponse("failed", ReturnMessages.request_unExpectedError , "");
		}

	}

	private LinkedHashMap<String, Object> checkRequest(LinkedHashMap<String, Object> request,HttpServletResponse httpResponse,HttpSession session,ObjectMapper obm) {
		HashMap<String,Object> user = new HashMap<>();
		LinkedHashMap<String, Object> response = null;
		try {
			request.put("user", user);
			response =  dispatchRequest(request,user,obm);
		} catch (Exception e) {
			logger.error("Error",e);
			return ConstructResponse.constructResponse("failed", ReturnMessages.request_unExpectedError , "");
		}
		
		return response;
	}


	private void logRequest(HttpServletRequest httpRequest, LinkedHashMap<String,Object> request, LinkedHashMap<String,Object> response, ObjectMapper obm, long startTime ) {
		
		try {
			if (request.get("up") !=null) request.put("up", "***********");
		    Map<String, Object> log = new LinkedHashMap<String,Object>();
		    log.put("session", request.get("sessionId"));
		    // memcache gore degistirelecek
		    try {
		    	if ( request.get("user")!=null && ((Map)request.get("user")).get("userName")!=null)
		    		log.put("userName", ((Map)request.get("user")).get("userName"));
		    	else if (request.get("userName")!=null)
		    			log.put("userName", request.get("userName"));
		    	else log.put("userName", request.get("un"));
			} catch (Exception e) {
				try {
					log.put("userName", request.get("un"));
				} catch (Exception e2) {
					log.put("userName", "");
				}
			}
		    // memcache gore degistirelecek
		    log.put("completeTime", System.currentTimeMillis() -startTime);
		    log.put("IP", request.get("clientIp"));
		    log.put("httpMethod" , httpRequest.getMethod());
	        log.put("request", request);
	        log.put("response", response);
	        String jsonLog = obm.writeValueAsString(log);
	        requestResponseLogger.info(jsonLog);
		} catch (Exception e) {
			logger.error("Error:",e);
		}
	}
	
	private String getRemoteAddr(HttpServletRequest request) {
	    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	    if (ipFromHeader != null && ipFromHeader.length() > 0) {
	    	if (logger.isDebugEnabled()) logger.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
	        return ipFromHeader;
	    }
	    return request.getRemoteAddr();
	}


	public ApplicationParameters getParameters() {
		return parameters;
	}

	public void setParameters(ApplicationParameters parameters) {
		this.parameters = parameters;
	}

	public ExecuteSqls getExecuteSqls() {
		return executeSqls;
	}

	public void setExecuteSqls(ExecuteSqls executeSqls) {
		this.executeSqls = executeSqls;
	}


}