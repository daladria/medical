package com.magis.restfull.web.dispatchers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphbuilder.struc.LinkedList;
import com.magis.constants.ReturnMessages;
import com.magis.constants.Sql;
import com.magis.model.ResultSetModel;
import com.magis.restfullAndWeb.utilities.ConstructResponse;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.DateTimeUtilities;
import com.magis.utilities.FileUtilities;

public class LogOperations {
	protected static final Log logger = LogFactory.getLog("restfull.dispatcher.UserOperations");
	private static final LinkedHashMap<String, Object> responseCommandNotFound = ConstructResponse.constructResponse(ReturnMessages.failed,ReturnMessages.command_not_found, ReturnMessages.command_not_found);
	
	public static LinkedHashMap<String, Object>  dispatch(ApplicationParameters parameters,LinkedHashMap<String, Object> request,  Map<String,Object> user, ObjectMapper obm) throws Exception{
		

		if (((String)request.get("command")).equals("logOperations.requestReport")) return requestReport(parameters, request, user, obm);
		
		if (((String)request.get("command")).equals("logOperations.deleteReport")) return deleteReport(parameters, request, user, obm);
		

		return responseCommandNotFound;
	}	


	
	public static LinkedHashMap<String, Object> deleteReport (ApplicationParameters parameters,LinkedHashMap<String, Object> request,  Map<String,Object> user, ObjectMapper obm) throws Exception{
		LinkedHashMap<String, Object> response = ConstructResponse.constructResponse(ReturnMessages.success,"", "");
		try {
			String dir = parameters.getParameter("logs.report.files");
			Files.deleteIfExists(Paths.get(dir + "/" + request.get("id")));
			} catch (Exception e) {
			logger.error("Error",e);
			return ConstructResponse.constructResponse(ReturnMessages.failed, ReturnMessages.request_unExpectedError, 
					e.getLocalizedMessage());
		}
		return response;
	}

	public static LinkedHashMap<String, Object> requestReport (ApplicationParameters parameters,LinkedHashMap<String, Object> request,  Map<String,Object> user, ObjectMapper obm) {
		List<Map<String,String>> reports = new ArrayList();
		LinkedHashMap<String, Object> response = ConstructResponse.constructResponse(ReturnMessages.success,"", "");

		try {
			String dir = parameters.getParameter("logs.report.files");
			dir = dir + "/requests/" + DateTimeUtilities.getCurrentDay("yyyy-MM-dd_HH_mm_sss") + ".txt";
			String data = obm.writeValueAsString(request);
			try {
				java.nio.file.Files.write(Paths.get(dir), 
		                  (data).getBytes(StandardCharsets.UTF_8),
		                  StandardOpenOption.APPEND);	  
			} catch (Exception e) {
				java.nio.file.Files.write(Paths.get(dir), 
		                  (data).getBytes(StandardCharsets.UTF_8),
		                  StandardOpenOption.APPEND,StandardOpenOption.CREATE);	  
			} 
		} catch (Exception e) {
			logger.error("Error",e);
		}
		return response;
	}

	
	public static List<Map<String,String>> getAllReports (ApplicationParameters parameters){
		List<Map<String,String>> reports = new ArrayList();

		try {
			String dir = parameters.getParameter("logs.report.files");
			List<File> files = FileUtilities.listFilesOldestFirst(dir);
			for (int i=0;i<files.size();i++) {
				if (files.get(files.size()-1-i).isFile()) { 
					Map<String,String> report = new HashMap<>();
					report.put("id", files.get(files.size()-1-i).getName());
					report.put("guid", UUID.randomUUID().toString());
					report.put("createDate", DateTimeUtilities.ms2Date(files.get(files.size()-1-i).lastModified(), "yyyy-MM-dd HH:mm:sss") );
					reports.add(report);
				}
			}

		} catch (Exception e) {
			logger.error("Error",e);
		}
		return reports;
	}

}
