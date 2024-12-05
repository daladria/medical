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
import com.magis.model.Hospital;
import com.magis.model.LoginModel;
import com.magis.model.ResultSetModel;
import com.magis.model.SystemParams;
import com.magis.model.User;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.DateTimeUtilities;
import com.magis.utilities.UserUtilities;

 
 
@Controller
public class HospitalController {  

	
	protected final Log logger = LogFactory.getLog("webLogger");
	private ApplicationParameters parameters =null;
	private ExecuteSqls executeSqls = null;
    public HospitalController() {
	}

	private MessageSource messageSource;
	
	@RequestMapping(value="/hospital", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
	public String createUser(Model model, Locale locale,
			HttpSession session, HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		User user=null;
		Hospital item = new Hospital("", "", "", "", "", false) 
				,newItem = null;
		String message="",messageColor="";
		
		try {
			user = UserUtilities.getUserInfo(false, parameters, executeSqls, messageSource, locale, logger);
			if(user==null || user.getRoles().indexOf("ROLE_ADMIN")<0) return "cms/login";
			Map<String, String> requestParams = UserUtilities.getRequestParams(request, logger);

			if (requestParams.get("operation")!=null) {
				item = getItemById(requestParams.get("itemNameSelect"));	
				newItem = new Hospital(requestParams.get("itemNameSelect"), requestParams.get("name"),requestParams.get("address"), requestParams.get("phone"), 
						requestParams.get("email"), requestParams.get("isDeleted"));
			}
			
			if (requestParams.get("operation")!=null && requestParams.get("operation").equals("save")) {
				if (item!=null) {
					message = messageSource.getMessage("systemParameters.save.dublicateRecord", null, locale);
					messageColor = "red";
				}
				else {
					if (insertItem( newItem)>0) {
						message = messageSource.getMessage("systemParameters.save.success", null, locale);
						messageColor="green";
						newItem=new Hospital("", "", "", "", "", false); 
					}else {
						message = messageSource.getMessage("systemParameters.save.failed", null, locale);
						messageColor = "red";
					}
				}
			}else if (requestParams.get("operation")!=null && requestParams.get("operation").equals("update")) {
				if (item==null) {
					message = messageSource.getMessage("systemParameters.save.recordNotExists", null, locale);
					messageColor = "red";
				}				
				else if (updateItem(newItem)>0) {
					message = messageSource.getMessage("systemParameters.save.success", null, locale);
					messageColor="green";
					newItem=new Hospital("", "", "", "", "", false); 
				}else {
					message = messageSource.getMessage("systemParameters.save.failed", null, locale);
					messageColor = "red";
				}	
			}
		} catch (Exception e) {
			message = messageSource.getMessage("myProfile.info.save.failed", null, locale);
			messageColor = "red";
			logger.error("Error,",e);
		}	
		
		model.addAttribute("hospitalActive","active open");
		model.addAttribute("newItem", newItem);
		model.addAttribute("items", getHospitalNames());
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
	    return "systemParameters/hospital";
	}

	private List<SystemParams> getHospitalNames(){
		List<SystemParams> items = new ArrayList<>();List <Object> sqlParams = new ArrayList<Object>(); 
		sqlParams.clear();
		sqlParams.add(Sql.systemParameters.SELECT_HOSPITAL_NAMES);
		try {
			List<ResultSetModel> cmps = executeSqls.executeListRS(sqlParams, true,parameters.getSqlTimeout());	
			for (int i=0;i<cmps.size();i++) items.add( new SystemParams(cmps.get(i).get("hospital_id"), cmps.get(i).get("hospital_name")));
		} catch (Exception e) {
			logger.error("Error:", e);
		}
		return items;
	}
	
	private Hospital getItemById(Object itemId){
		Hospital item=null;
		try {
			List <Object> sqlParams = new ArrayList<Object>();
			sqlParams.add(Sql.systemParameters.SELECT_HOSPITAL_BY_ID);
			sqlParams.add(itemId);
			List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, true, parameters.getHttpTimeout());
			item= new Hospital(cmps.get(0).get("hospital_id"), cmps.get(0).get("hospital_name"), cmps.get(0).get("hospital_address"), 
					cmps.get(0).get("hospital_phone"), cmps.get(0).get("hospital_email"), cmps.get(0).get("is_deleted"));
			
		} catch (Exception e) {
			item=null;
		}
		return item;
	}
	
	public int  insertItem(Hospital item) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(Sql.systemParameters.INSERT_HOSPITAL);
		sqlParams.add(item.getHospitalName());
		sqlParams.add(item.getHospitalAddress());
		sqlParams.add(item.getHospitalPhone());
		sqlParams.add(item.getHospitalEmail());
		sqlParams.add(item.getIsDeleted());
		
		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
	}  
	
	public int  updateItem(Hospital item) throws Exception {
		List <Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(Sql.systemParameters.UPDATE_HOSPITAL);
		sqlParams.add(item.getHospitalName());
		sqlParams.add(item.getHospitalAddress());
		sqlParams.add(item.getHospitalPhone());
		sqlParams.add(item.getHospitalEmail());
		sqlParams.add(item.getIsDeleted());
		sqlParams.add(item.getHospitalId());

		List<ResultSetModel> cmps = parameters.getExecuteSqls().executeListRS(sqlParams, false, parameters.getHttpTimeout());
		if (cmps==null || cmps.get(0).get("count").equals("0")) return 0;
		return 1;
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