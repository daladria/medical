package com.magis.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.magis.constants.Sql;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.ResultSetModel;
import com.magis.model.User;
import com.magis.schedulers.ApplicationParameters;


public class UserUtilities {

	public static User  getUserInfo(boolean clearPreviousInfo, ApplicationParameters parameters, ExecuteSqls executeSqls, MessageSource messageSource, Locale locale, Log logger) throws Exception {
		String userRole=messageSource.getMessage("myProfile.user.title", null, locale);
		User user=null;
		try {
			Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth1.getName();
			if (clearPreviousInfo) {
				try {
					parameters.getUsers().remove(userName);
				} catch (Exception e) {}
			}
			String roles = auth1.getAuthorities().toString();
			user = (User) parameters.getUsers().get(userName);
			if(user==null) {
				List <Object> sqlParams = new ArrayList<Object>();
				sqlParams.add(Sql.logins.SELECT_USER_BY_USERNAME);
				sqlParams.add(userName);
				List<ResultSetModel> cmps = null;
				cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());
				if(roles.indexOf("ROLE_ADMIN")>-1) userRole = messageSource.getMessage("myProfile.admin.title", null, locale);
				user= new User(userName, cmps.get(0).get("password"), cmps.get(0).get("name"), cmps.get(0).get("surname"), 
						userRole, roles, cmps.get(0).get("email"),  cmps.get(0).get("avatar"),
						cmps.get(0).get("company"), cmps.get(0).get("phone"),  cmps.get(0).get("mobile_phone"), cmps.get(0).get("is_deleted"));
				parameters.getUsers().put(userName, user);
			} 
		} catch (Exception e) {
			logger.error("Error,",e);
		}	
	    return user;
	}   
	
	public static Map<String,String> getRequestParams(HttpServletRequest request, Log logger) throws Exception {
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			Map<String,String> requestParameters = new HashMap<>();
		    for ( Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
		        String key = entry.getKey();
		        String[] value= entry.getValue();
		        requestParameters.put(key, value[0]);
		    }
		    return requestParameters;
		} catch (Exception e) {
			logger.error("Error", e);
		}
		return new HashMap<>();
	}
	
	public static void saveAvatarFile(ApplicationParameters parameters, CommonsMultipartFile file, User user, ExecuteSqls executeSqls, Log logger){
		if (file==null) return;
		List <Object> sqlParams = new ArrayList<Object>();
        List<Map<String, Object>> cmps = null;
        try {	
        	String filename=file.getOriginalFilename().trim();
	        if (filename.length()==0) return;
        	int pos1 = filename.lastIndexOf(".");
        	filename = user.getUserName() + filename.substring(pos1);
	        String path = parameters.getParameter("user.userImages.folder");
	        path = path + filename ;
	          
	        file.transferTo(new File(path));

	        sqlParams.add(Sql.logins.UPDATE_USER_AVATAR_BY_USERNAME);
			sqlParams.add(filename);
			sqlParams.add(user.getUserName());
			executeSqls.executeListRS(sqlParams, false,parameters.getSqlTimeout());

        } catch (Exception e) {
        	logger.error("Error:", e);
		}finally {
		}
	}

	public static User  getUserInfo(ApplicationParameters parameters, String userName) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(Sql.logins.SELECT_USER_BY_USERNAME);
		sqlParams.add(userName);

		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, true, parameters.getHttpTimeout());
		User usr= new User(cmps.get(0).get("username"), cmps.get(0).get("password"), cmps.get(0).get("name"), cmps.get(0).get("surname"), 
				"", cmps.get(0).get("role_name"), cmps.get(0).get("email"),  cmps.get(0).get("avatar"),
				cmps.get(0).get("company"), cmps.get(0).get("phone"),  cmps.get(0).get("mobile_phone"), cmps.get(0).get("is_deleted"));
		return usr;
	}   

	public static int  insertUserWithoutAvatar(ApplicationParameters parameters, User usr) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();

		sqlParams.add(Sql.user.DELETE_USER_ROLES);
		sqlParams.add(usr.getUserName());
		parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		
		sqlParams.clear();
		sqlParams.add(Sql.user.INSERT_USER_ROLE);
		sqlParams.add(usr.getUserName());
		sqlParams.add(usr.getRoles());

		parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());

		sqlParams.clear();
		sqlParams.add(Sql.user.INSERT_USER_WITHOUT_IMAGE);
		sqlParams.add(usr.getUserName());
		sqlParams.add(usr.getPassword());
		sqlParams.add(usr.getName());
		sqlParams.add(usr.getSurname());
		sqlParams.add(usr.getEmail());
		sqlParams.add(usr.getAvatar());
		sqlParams.add(usr.getCompany());
		sqlParams.add(usr.getPhone());
		sqlParams.add(usr.getMobilePhone());
		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
	}  
	
	public static int  updateUserWithoutAvatar(ApplicationParameters parameters, User usr) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();

		sqlParams.add(Sql.user.DELETE_USER_ROLES);
		sqlParams.add(usr.getUserName());
		parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		
		sqlParams.clear();
		sqlParams.add(Sql.user.INSERT_USER_ROLE);
		sqlParams.add(usr.getUserName());
		sqlParams.add(usr.getRoles());

		parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());

		sqlParams.clear();
		sqlParams.add(Sql.user.UPDATE_USER_WITHOUT_IMAGE);
		sqlParams.add(usr.getPassword());
		sqlParams.add(usr.getName());
		sqlParams.add(usr.getSurname());
		sqlParams.add(usr.getEmail());
		sqlParams.add(usr.getAvatar());
		sqlParams.add(usr.getCompany());
		sqlParams.add(usr.getPhone());
		sqlParams.add(usr.getMobilePhone());
		sqlParams.add(usr.getUserName());
		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
	} 
	
	public static int  updateUserIsDeleted(ApplicationParameters parameters, User usr, boolean status) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();

		sqlParams.add(Sql.user.UPDATE_USER_IS_DELETED);
		sqlParams.add(status);
		sqlParams.add(usr.getUserName());
		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
	} 
	
	public static int  updateUserPassword(ApplicationParameters parameters, User usr) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();

		sqlParams.add(Sql.user.UPDATE_USER_PASSWORD);
		sqlParams.add(usr.getPassword());
		sqlParams.add(usr.getUserName());
		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
	} 
}
