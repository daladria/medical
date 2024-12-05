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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
public class MyProfileController {  

	
	protected final Log logger = LogFactory.getLog("webLogger");
	private ApplicationParameters parameters =null;
	private ExecuteSqls executeSqls = null;
    public MyProfileController() {
	}

	private MessageSource messageSource;
	
	@RequestMapping(value="/myProfile", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String myProfile(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null;
		String message="",messageColor="";
		try {
			user = UserUtilities.getUserInfo(true, parameters, executeSqls, messageSource, locale, logger);
			if(user==null) return "cms/login";
			UserUtilities.saveAvatarFile(parameters, avatar, user, executeSqls, logger);
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.size()>3) {
				List <Object> sqlParams = new ArrayList<Object>();
				sqlParams.add(Sql.logins.UPDATE_USER_INFO_BY_USERNAME);
				sqlParams.add(requestParams.get("password"));
				sqlParams.add(requestParams.get("name"));
				sqlParams.add(requestParams.get("surname"));
				sqlParams.add(requestParams.get("email"));
//				if (requestParams.get("avatar")!=null) sqlParams.add(requestParams.get("avatar"));
//				else sqlParams.add("bos.png");
				sqlParams.add(requestParams.get("company"));
				sqlParams.add(requestParams.get("phone"));
				sqlParams.add(requestParams.get("mobilePhone"));
				sqlParams.add(user.getUserName());
				try {
					List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
					user = UserUtilities.getUserInfo(true, parameters, executeSqls, messageSource, locale, logger);
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
				} catch (Exception e) {
					logger.error("Error:", e);
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}
			}
			
			model.addAttribute("user", user);
			model.addAttribute("message", message);
			model.addAttribute("messageColor", messageColor);
		} catch (Exception e) {
			logger.error("Error,",e);
		}	
	      return "user/my_profile";
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