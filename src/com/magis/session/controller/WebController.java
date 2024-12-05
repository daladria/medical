package com.magis.session.controller;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magis.constants.Sql;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.LoginModel;
import com.magis.model.ResultSetModel;
import com.magis.model.User;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.DateTimeUtilities;
import com.magis.utilities.UserUtilities;

 
 
@Controller
public class WebController {  
	protected final Log logger = LogFactory.getLog("webLogger");
	private ApplicationParameters parameters =null;
	private ExecuteSqls executeSqls = null;
	
	private MessageSource messageSource;
	
    public WebController() {
	}

	@RequestMapping(value="/dashboard", method = {RequestMethod.GET}, produces = "text/plain;charset=utf-8")
	public String main(Model model,HttpSession session, Locale locale,
			HttpServletRequest request,HttpServletResponse httpResponse) throws Exception {
//		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);             
//		localeResolver.setLocale(request, httpResponse,new Locale("en"));
		User user=null;
		try {
			user = UserUtilities.getUserInfo(true, parameters, executeSqls, messageSource, locale, logger);
			if(user==null) return "cms/login";
			model.addAttribute("user", user);

		} catch (Exception e) {
			logger.error("Error,",e);
		}	
		model.addAttribute("dashboardActive","active open");
		return "cms/dashboard";
	}

	
	@RequestMapping(value="/login", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/plain;charset=utf-8")
	public String login(Model model,HttpSession session,HttpServletRequest request,HttpServletResponse httpResponse) throws Exception {


		
		if (logger.isDebugEnabled()) logger.info("{\"command\":\"Login Page Called\", \"status\":\"SUCCESS\",\"ip\":\"" + getRemoteAddr(request) + "\"}");
		String responseStatus="success";
		httpResponse.setStatus(200);
		String responseDetailStatus="Login Page Sent";
		String command="Login Action";
		long startTime = System.currentTimeMillis();
		boolean directToLogin=true;
		String userName =null;
		LoginModel user = null;
		Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();	    
		if (auth1!=null) userName = auth1.getName(); //get logged in username
		if ((userName!=null) && (userName.length()>0) && (!userName.toLowerCase().startsWith("anonymous")) ){
			responseDetailStatus="User Already Logged in";
			command="Login Page Requested";
			directToLogin = false;			
		}else if (userName == null || userName.length() == 0){
			user = new LoginModel();
			command="Login Page Requested";
		}else {
		}

		
		logger.info("{\"userName\":\"" + userName + "\", \"responseStatus\":\"" + responseStatus + "\", \"responseDetailStatus\":\"" + responseDetailStatus + "\", \"command\":\"" + command + "\", \"directToLogin\":" + directToLogin + "}");
		if (directToLogin) {
			model.addAttribute("login",user);
			return "cms/login";
		}
		model.addAttribute("dashboardActive","active open");
		return "cms/dashboard";

}

 
    
	private String getRemoteAddr(HttpServletRequest request) {
	    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	    if (ipFromHeader != null && ipFromHeader.length() > 0) {
	    	//if (logger.isDebugEnabled()) logger.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}