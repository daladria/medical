package com.magis.modelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import com.magis.model.ResultSetModel;

public  class ResultSetMapper implements RowMapper<ResultSetModel> {
    String objectName="";
    protected final Log logger = LogFactory.getLog(getClass()); 

	public ResultSetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetModel model = new ResultSetModel();
		try {
			for (int i = 1; i<=rs.getMetaData().getColumnCount();i++){
				//System.out.println(rs.getMetaData().getColumnTypeName(i));
				model.add(rs.getMetaData().getColumnName(i), rs.getObject(i));
			}		
		} catch (Exception e) {
			logger.error("Error Description:",e);
			try {
				throw e;
			} catch (Exception e1) {
				logger.error("Error Description:",e);
			}
		} 
		return model;

		
    }
    
    
}



/*
package com.magis.modelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import com.magis.model.ResultSetModel;

public  class ResultSetMapper implements RowMapper<ResultSetModel> {
    String objectName="";
    protected final Log logger = LogFactory.getLog(getClass()); 

	public ResultSetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetModel model = new ResultSetModel();
		try {
			for (int i = 1; i<=rs.getMetaData().getColumnCount();i++){
				//System.out.println(rs.getMetaData().getColumnTypeName(i));
				model.add(rs.getMetaData().getColumnName(i), rs.getString(i));
			}		
		} catch (Exception e) {
			logger.error("Error Description:",e);
			try {
				throw e;
			} catch (Exception e1) {
				logger.error("Error Description:",e);
			}
		} 
		return model;

		
    }
    
    
}

*/