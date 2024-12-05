package com.magis.schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magis.constants.Sql;
import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.ResultSetModel;
import com.magis.model.User;


public class ApplicationParameters {
	
	CassandraTemplate template = null;
	
	private LinkedHashMap<String, Object> messages  =  new LinkedHashMap<String, Object>();

	private Map<String, String> parameters  =  Collections.synchronizedMap(new HashMap<String, String>());
	private Map<String, User> users =  Collections.synchronizedMap(new HashMap<String, User>());
	private LinkedHashMap<String, Object> clientParameters = null;
	private String selectDateForParameters ="1900/01/01 00:00:00";
	int httpTimeout=30000;
	int sqlTimeout = 30000;
	protected final Log logger = LogFactory.getLog(getClass());
	private ExecuteSqls executeSqls = null;
	
	public ApplicationParameters() {
		if (logger.isInfoEnabled())  logger.info("Application Parameters Initiliazed...." );
	}


 	public void refreshCache() {
		try {
			if (parameters.get("cache.parameters.refreshInterval.minutes") !=null)
				Thread.sleep(( Integer.parseInt(parameters.get("cache.parameters.refreshInterval.minutes"))) *60000);
			if (logger.isInfoEnabled())  logger.info("Refresh Application Cache Cron Started...");
			refreshParameters();
		} catch(InterruptedException i) {
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (logger.isInfoEnabled())  logger.info("Refresh Application Cache Cron Finished...");
		}	
 	}
 	
	
	public void refreshParameters() {
		try {
			if (logger.isInfoEnabled())  logger.info("Refresh Parameters Cron Started...");
			List <Object> sqlParams = new ArrayList<Object>();
			sqlParams.add(Sql.crons.GET_APPLICATION_PARAMETERS);
			sqlParams.add(selectDateForParameters);
			List<ResultSetModel> cmps= executeSqls.executeListRS(sqlParams, true, this.getSqlTimeout());
			if (cmps.size()>0) {
				if (logger.isInfoEnabled())  logger.info("Refresh Parameters New Change Found...");
				for (int i = 0;i<cmps.size();i++) {
					parameters.put((String) cmps.get(i).get("parameter_name"), (String) cmps.get(i).get("parameter_value"));
				}
				sqlParams.clear();
				sqlParams.add(Sql.crons.GET_APPLICATION_PARAMETERS_MAX_DATE);
				cmps= executeSqls.executeListRS(sqlParams, true, this.getSqlTimeout());
				parameters.put("last_update_date", (String) cmps.get(0).get("dt"));
				selectDateForParameters = (String) cmps.get(0).get("dt");
				httpTimeout = Integer.parseInt(parameters.get("http.timeout"));
				sqlTimeout = Integer.parseInt(parameters.get("sql.query.Timeout"));
			}
			
		}catch(InterruptedException i) {
			
		} catch (Exception e) {
			logger.error("Error:",e);
		}finally {
			if (logger.isInfoEnabled())  logger.info("Refresh Parameters Cron Finished...");
		}
	}
	
	public String getParameter(String key) {
		return parameters.get(key);
	}
	

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public ExecuteSqls getExecuteSqls() {
		return executeSqls;
	}

	public void setExecuteSqls(ExecuteSqls executeSqls) {
		this.executeSqls = executeSqls;
		refreshCache();
	}


	public int getHttpTimeout() {
		return httpTimeout;
	}


	public int getSqlTimeout() {
		return sqlTimeout;
	}

	public LinkedHashMap<String, Object> getClientParameters() {
		return clientParameters;
	}


	public Map<String, User> getUsers() {
		return users;
	}

	
}


