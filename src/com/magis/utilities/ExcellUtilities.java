package com.magis.utilities;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.magis.schedulers.ApplicationParameters;

public class ExcellUtilities {
	protected final static Log logger = LogFactory.getLog(ExcellUtilities.class);
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
	
	@SuppressWarnings("resource")
	public static String createExcellDocument(ApplicationParameters parameters, Map<String, Object> request, List<Object> contents, List<String> titles){
		DateTimeUtilities du = new DateTimeUtilities();
		String filePath = "d:/"; // TODO: (Log Export)Application paramaters a atılacak 
		String fileName =  du.getCurrentDay("yyyy-MM-dd HH:mm:ss").replace(" ", "_").replace(":", ".");
		try {
			switch ((String)request.get("change_type")) {
				case "login.lgU&pvv":
						fileName = fileName + "_login_logs.xlsx";
						boolean result  = ExcellUtilities.loginLog(parameters, filePath + fileName, contents, titles);
						if (!result) fileName = "";
					break;
				case "diger_servisler":
					break;
					
				default:
					fileName = "Service not found.";
					break;
			}
		} catch (Exception e) {
			//logger.error("Error",e);
		} finally {

		}
		return fileName;
	}
	
	
	public static boolean loginLog(ApplicationParameters parameters, String fileName, List<Object> contents, List<String> titles) {
		boolean result = false;
		FileOutputStream fileOut = null;
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Sheet 1");  
			int cSize = 0;
			int rSize = 0;
			int maxCol = 0;
			XSSFRow rowData = null;
			// TODO: (Log Export)keylerin değerleri çekilecek
			if(titles != null) {
				rowData = sheet.createRow(rSize);
				rSize++;
				for (String title : titles) {
					 rowData.createCell(cSize).setCellValue(title);
					 cSize++;
				}
			}
			if (cSize>maxCol) maxCol = cSize;
			
			if(contents != null) {
				HashMap<String, Object> row = new HashMap<String, Object>();
				List<String> values = new ArrayList<String>();
				for (int i = 0; i < contents.size(); i++) {
					row = (HashMap<String, Object>) contents.get(i);
					values = (List<String>) row.get("change_data");
					cSize =0;
					rowData = sheet.createRow(rSize);
					rSize++;
					for (String value : values) {
						rowData.createCell(cSize).setCellValue(value);
						cSize++;
					}
					if (cSize>maxCol) maxCol = cSize;
				}
			}
			
			for (int i =0; i<maxCol; i++) sheet.autoSizeColumn(i);
			fileOut = new FileOutputStream(fileName);
	        workbook.write(fileOut);
			result = true;
		} catch (Exception e) {
			logger.error("Error", e);
		}finally {
			try {
				fileOut.close();
				workbook.close();
			} catch (Exception e2) {

			}
		}
		return result;
	}

}