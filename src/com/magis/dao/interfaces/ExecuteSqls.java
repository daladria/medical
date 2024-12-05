package com.magis.dao.interfaces;
/*
 * 1- in memory Cache Time
 * 2- max record count (0 inifinitive)
 * 3- if max set LILO or FIFO implemented
 * 
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.StoredProcedure;

import com.magis.model.ResultSetModel;
import com.magis.modelMapper.ResultSetMapper;




public class ExecuteSqls extends  JdbcDaoSupport{ 

	public List<ResultSetModel> executeListRSPrev(List<String> params, boolean returnList, int queryTimeout) throws Exception {
		List<ResultSetModel> result = new ArrayList<ResultSetModel>();
		ResultSetModel affectedResult = new ResultSetModel();
		if (queryTimeout > 0) getJdbcTemplate().setQueryTimeout(queryTimeout);

		Object[] objs = new Object[params.size() - 1];
		for (int i = 1; i < params.size(); i++) {
			objs[i - 1] = params.get(i);
		}
		if (returnList) {
			result = getJdbcTemplate().query(params.get(0), objs, new ResultSetMapper());
		} else {
			affectedResult.add("count", ""+getJdbcTemplate().update(params.get(0), objs));
			result.add(affectedResult);
		}
		return result;
	}

	 public List<ResultSetModel> executeListRS(List<Object> params, boolean returnList, int queryTimeout) throws Exception{
		 
         List<ResultSetModel> result = new ArrayList<ResultSetModel>();
         JdbcTemplate template = getJdbcTemplate();
         template.setQueryTimeout(queryTimeout);
         String varType = "";
         Object[] objs = new Object[params.size() - 1];
         Connection conn = null;
         try {
        	 
             for (int i = 1; i < params.size(); i++) {
                 if (params.get(i)!=null && params.get(i).getClass().isArray()) {
                       varType = params.get(i).getClass().getSimpleName().replace("[]", "");
                       if (varType.equals("String")) varType = "Text";
                       else if (varType.equals("Double")) varType = "Numeric";
                       else if (varType.equals("Long")) varType = "Bigint";
                       //varType = varType.replaceFirst("I", "i");
                       if (conn==null) conn = getConnection();
                       objs[i - 1] = conn.createArrayOf(varType , (Object[]) params.get(i));    
                 } else objs[i - 1] = params.get(i);
                 //System.out.println(params.get(i).getClass().getTypeName());
          }
          if (returnList) {
                 result = template.query((String)params.get(0), objs, new ResultSetMapper());
          } else {
                 ResultSetModel affectedResult = new ResultSetModel();
                 affectedResult.add("count", ""+template.update((String)params.get(0), objs));
                 result.add(affectedResult);
          }
          return result;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (conn!=null) conn.close();
			} catch (Exception e2) {
				
			}
		}
        
  }

	
	
	public Map<String, Object>  executeStoredProcedure(LinkedHashMap<String,Object> params,SqlParameter[] paramArray, String functionName, int queryTimeout) throws Exception {

		
		//Pass jdbcTemplate and name of the stored Procedure.
		StoredProcedure myStoredProcedure = new StoredProcedure(getJdbcTemplate(), functionName) {};

		myStoredProcedure.setParameters(paramArray);
		myStoredProcedure.compile();


		//Call stored procedure
		Map<String, Object> rs = myStoredProcedure.execute(true);
		return rs;
       	}
	

}

/*
List<ResultSetModel> result = new ArrayList<ResultSetModel>();
ResultSetModel affectedResult = new ResultSetModel();
if (queryTimeout > 0) getJdbcTemplate().setQueryTimeout(queryTimeout);

SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
            .withProcedureName("show_users")
            .declareParameters(
                   //new SqlParameter("pwd", Types.NVARCHAR),
            		new SqlParameter("pwd", Types.BOOLEAN),
                    new SqlOutParameter("ref", Types.REF_CURSOR));

Map<String, Object> rs = call.execute(new MapSqlParameterSource("pwd", true));

return rs;
*/

