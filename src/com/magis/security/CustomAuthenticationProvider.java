package com.magis.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.magis.constants.Sql;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.ResultSetModel;

@Component
public class CustomAuthenticationProvider   implements AuthenticationProvider {
	private ExecuteSqls executeSqls = null;
	protected final Log logger = LogFactory.getLog("webLogger");
	protected final Log loggerApp = LogFactory.getLog(getClass());

	
    @Override
    public Authentication authenticate(Authentication authentication)  throws NotFoundException {
    	//String user="a";
    	//String pwd = "a";
        String userName = null;
        String password = null;
        String ip = "";
		try {
	        userName = authentication.getName();
	        password = authentication.getCredentials().toString();
	        ip= ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
	        if (ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
		} catch (Exception e) {
			loggerApp.error("Error:", e);
		}
        
        if (userName==null || userName.length()==0 || password==null || password.length()==0) {
        	if (logger.isInfoEnabled())  logger.info("{\"command\":\"Login Request\",  \"username\":\"" + userName + "\", \"fromIP\":\"" + ip + "\", \"status\":\"FAILED\"}");
        	return null;
        }

		List <Object> sqlParams = new ArrayList<Object>();
		sqlParams.add(Sql.logins.SELECT_USER);
		sqlParams.add(userName);
		sqlParams.add(password);

		List<ResultSetModel> cmps = null;
		try {
			cmps = executeSqls.executeListRS(sqlParams, true,30000);
		} catch (Exception e) {
			loggerApp.error("Error", e);
		}
		if (cmps==null || cmps.size() ==0) {
	        if (logger.isInfoEnabled())  logger.info("{\"command\":\"Login Request\",  \"username\":\"" + userName + "\", \"fromIP\":\"" + ip + "\", \"status\":\"FAILED\"}");
	        return null;
        }else {
            if (logger.isInfoEnabled())  logger.info("{\"command\":\"Login Request\",  \"username\":\"" + userName + "\", \"fromIP\":\"" + ip + "\", \"status\":\"SUCCESS\"}");
    		sqlParams.clear();
    		sqlParams.add(Sql.logins.UPDATE_LOGIN_USER);
    		sqlParams.add(ip);
    		sqlParams.add(userName);
    		try {
				executeSqls.executeListRS(sqlParams, false,30000);
			} catch (Exception e) {
				loggerApp.error("Error", e);
				return null;
			}
        }
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		for (int i=0;i<cmps.size();i++) {
			grantedAuths.add(new SimpleGrantedAuthority("" + cmps.get(i).get("role_name")));
		}

        return new UsernamePasswordAuthenticationToken(userName, password,grantedAuths);		
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }

	public ExecuteSqls getExecuteSqls() {
		return executeSqls;
	}

	public void setExecuteSqls(ExecuteSqls executeSqls) {
		this.executeSqls = executeSqls;
	}
    
    
}