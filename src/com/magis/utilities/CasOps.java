package com.magis.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

public class CasOps {
// Cassandra operations for update delete insert and log
	private final static Log dbLogger = LogFactory.getLog("cassandraDBLogger");
	
	public static List<Map<String, Object>> exec(String query, CassandraTemplate template) {
		List<Map<String, Object>> result = null;
		
		try {
			if (dbLogger.isDebugEnabled()) {
				long start = System.currentTimeMillis();
				result = template.getCqlOperations().queryForList(query);
				dbLogger.debug("Cassandra Complete Time: " + (System.currentTimeMillis() -start) + " Query: " + query);
			}else if (dbLogger.isInfoEnabled()) {
				long start = System.currentTimeMillis();
				result = template.getCqlOperations().queryForList(query);
				dbLogger.info("Cassandra Complete Time:" + (System.currentTimeMillis() -start));
			}else if (dbLogger.isErrorEnabled()) {
				result = template.getCqlOperations().queryForList(query);
			}
		} catch (Exception e) {
			dbLogger.error("Error, query:" + query,e);
		}
		return result;
	}
	
	public static List<Object> exec(String query, CassandraTemplate template,int pageSize, String lastPaging) {
		List<Object> result = new ArrayList<Object>();
// com.datastax.oss.driver.api.core.cql.Statement		
		try {
			long start = System.currentTimeMillis();
			Statement st = new SimpleStatement(query);
			//Statement st = SimpleStatement.
			st.setFetchSize(pageSize);
	//		PagingState pagingState;
			
			if (lastPaging!=null && lastPaging.trim().length()> 0 && !lastPaging.trim().equals("null"))
				st.setPagingState(PagingState.fromString(lastPaging));
			
			ResultSet rs = template.getCqlOperations().queryForResultSet(st);
			
//			pagingState  = rs.getExecutionInfo().getPagingState();
			
			int remaining = rs.getAvailableWithoutFetching();
		
			ColumnDefinitions cols = rs.getColumnDefinitions();

			lastPaging = "";
			try {lastPaging = rs.getExecutionInfo().getPagingState().toString();} catch (Exception e) {}
			
//			int cnt=0;
			result.add(lastPaging);
			while(rs.getAvailableWithoutFetching() > 0) {
			    Row row = rs.one();
	//			cnt++;
	//			System.out.println(cnt + "-" + row.getObject("id"));
				Map<String,Object> record = new HashMap<String, Object>();
				String name="";
				//record.put("id", row.getObject("id"));
				
				for (int i=0;i<cols.size();i++) {
					name = cols.getName(i);
					record.put(name, row.getObject(name));
				}
				result.add(record);	
			}
			
//			for (Row row : rs) {
//				i++;
//				System.out.println(i + "-" + row.getObject("id"));
				//result.add(row.getMap(1, String.class, Object.class));
//			    renderInResponse(row);
//			    if (--remaining == 0) {
//			        break;
//			    }
//			}
			
			if (dbLogger.isDebugEnabled()) {
				
//				result = template.getCqlOperations().queryForList(query);
				dbLogger.debug("Cassandra Complete Time: " + (System.currentTimeMillis() -start) + " Query: " + query);
			}else if (dbLogger.isInfoEnabled()) {
//				result = template.getCqlOperations().queryForList(query);
				dbLogger.info("Cassandra Complete Time:" + (System.currentTimeMillis() -start));
			}else if (dbLogger.isErrorEnabled()) {
//				result = template.getCqlOperations().queryForList(query);
			}
		} catch (Exception e) {
			dbLogger.error("Error, query:" + query,e);
		}
		return result;
	}

}
