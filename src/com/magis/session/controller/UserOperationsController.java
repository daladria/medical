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
public class UserOperationsController {  

	
	protected final Log logger = LogFactory.getLog("webLogger");
	private ApplicationParameters parameters =null;
	private ExecuteSqls executeSqls = null;
    public UserOperationsController() {
	}

	private MessageSource messageSource;
	
	@RequestMapping(value="/createUser", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String createUser(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userName"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userName"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			if (requestParams.get("operation")!=null && requestParams.get("operation").equals("save")) {
				if (usr!=null) {
					message = messageSource.getMessage("userOperations.save.dublicateRecord", null, locale);
					messageColor = "red";
				}
				else {
					if (UserUtilities.insertUserWithoutAvatar(parameters, newUser)>0) {
						message = messageSource.getMessage("myProfile.info.save.success", null, locale);
						messageColor="green";
						newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
					}else {
						message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
						messageColor = "red";
					}
					
				}
				

			}else if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("update")) {
				if (UserUtilities.updateUserWithoutAvatar(parameters, newUser)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}	
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("delete")) {
				if (UserUtilities.updateUserIsDeleted(parameters, newUser, true)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}	
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("undelete")) {
				if (UserUtilities.updateUserIsDeleted(parameters, newUser, false)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}				
			}


			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_ACTIVE_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/userOperations";
	}


	@RequestMapping(value="/userPassword", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String userPassword(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userNameSelect"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userNameSelect"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			
			if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("update")) {
				if (UserUtilities.updateUserPassword(parameters, newUser)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}	
			}
			
			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_ACTIVE_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/userPassword";
	}

	
	@RequestMapping(value="/changeUserInfo", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String changeUserInfo(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userNameSelect"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userNameSelect"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			
			if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("update")) {
				if (UserUtilities.updateUserWithoutAvatar(parameters, newUser)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}	
			}
			
			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_ACTIVE_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/changeUserInfo";
	}
	
	
	@RequestMapping(value="/deleteUser", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String deleteUser(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userNameSelect"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userNameSelect"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			
			if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("delete")) {
				if (UserUtilities.updateUserIsDeleted(parameters, newUser, true)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}	
			}
			
			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_ACTIVE_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/deleteUser";
	}
	
	@RequestMapping(value="/unDeleteUser", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String unDeleteUser(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userNameSelect"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userNameSelect"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			
			if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("undelete")) {
				if (UserUtilities.updateUserIsDeleted(parameters, newUser, false)>0) {
					message = messageSource.getMessage("myProfile.info.save.success", null, locale);
					messageColor="green";
					newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
				}else {
					message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
					messageColor = "red";
				}				
			}
			
			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_DELETED_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/unDeleteUser";
	}
	
	
	@RequestMapping(value="/userAvatar", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String userAvatar(Model model, Locale locale,
			@RequestParam(required = false) CommonsMultipartFile avatar,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null,
				usr=new User("", "", "", "", "", "", "", "", "", "", "",false) 
				,newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
		String message="",messageColor="";
		List<String> userNames = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);
			if (requestParams.get("operation")!=null) {
				try {
					usr = UserUtilities.getUserInfo(parameters, requestParams.get("userNameSelect"));	
				} catch (Exception e) {
					usr=null;
				}
				newUser = new User(requestParams.get("userNameSelect"), requestParams.get("password"),requestParams.get("name"), requestParams.get("surname"), 
						"", requestParams.get("role"), requestParams.get("email"), "bos.png",
						requestParams.get("company"), requestParams.get("phone"),  requestParams.get("mobilePhone"), false);
			}	
			
			if (usr==null) {
				message = messageSource.getMessage("userOperations.save.recordNotExists", null, locale);
				messageColor = "red";
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("update")) {
				UserUtilities.saveAvatarFile(parameters, avatar, newUser, executeSqls, logger);
				message = messageSource.getMessage("myProfile.info.save.success", null, locale);
				messageColor="green";
				newUser=new User("", "", "", "", "", "", "", "", "", "", "",false);
			}
			
			sqlParams.clear();
			sqlParams.add(Sql.logins.SELECT_ACTIVE_USER_NAMES);
			try {
				List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
				for (int i=0;i<cmps.size();i++) userNames.add("" + cmps.get(i).get("userName"));
			} catch (Exception e) {
				logger.error("Error:", e);
			}

		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("userOperationsActive","active open");
		model.addAttribute("newUser", newUser);
		model.addAttribute("userNames", userNames);
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "admin/userAvatar";
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