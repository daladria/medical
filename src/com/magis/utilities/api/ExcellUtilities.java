package com.magis.utilities.api;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellUtilities {
	protected final static Log logger = LogFactory.getLog(ExcellUtilities.class);
	
	public static boolean createExcelFile(String fileName, LinkedHashMap<String,ArrayList<ArrayList<String>>> data) {
		boolean result = false;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet  = null;
		ArrayList<ArrayList<String>> columns = null;
		ArrayList<String> column = null;
		Row rowData = null;
		int cSize = 0;
		int rSize = 0;
		FileOutputStream fileOut = null;
		try {
			int maxCol = 0;
			workbook = new XSSFWorkbook();
			sheet  = null;
			Set<String> keys = data.keySet();
			for(String key: keys){
				sheet = workbook.createSheet(key);  
				columns = data.get(key);
				rSize = columns.size();
				maxCol = 0;
				for (int i = 0;i<rSize;i++) {
					rowData  = sheet.createRow(i);
					column = columns.get(i);
					cSize = column.size();
					if (cSize>maxCol) maxCol = cSize;
					for (int j=0;j<cSize;j++) {
						 rowData.createCell(j).setCellValue(column.get(j));
					}
				}
				
				for (int i =0; i<maxCol; i++) 
					sheet.autoSizeColumn(i);
			}

			
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
	
	public static void main(String[] args) {
        try {
        	
        	LinkedHashMap<String,ArrayList<ArrayList<String>>> data = new LinkedHashMap<>();
        	
        	ArrayList<ArrayList<String>> columns = new ArrayList<>();
    		
        	ArrayList<String> column = new ArrayList<>();
    		column.add("Numara");
    		column.add("Ad");
    		column.add("Soyad");
    		columns.add(column);

    		column = new ArrayList<>();
    		column.add("1");
    		column.add("Mehmet");
    		column.add("Çelik");
    		columns.add(column);
    		

    		column = new ArrayList<>();
    		column.add("2");
    		column.add("Çç Ğğ Iı");
    		column.add("ddddddddddddddddddddddddddddddddddddddddddddddddddddd");
    		columns.add(column);

    		column = new ArrayList<>();
    		column.add("3");
    		column.add("İi Öö Şş Üü");
    		column.add("İi Öö Şş Üü");
    		columns.add(column);
    		
    		data.put("ilkSheet", columns) ;
    		data.put("ikinciSheet", columns) ;
    		data.put("ucuncuSheet", columns) ;
    		
    		boolean result = ExcellUtilities.createExcelFile("D:/Excell.xlsx", data);
/*    		
            String filename = "D:/NewExcelFile.xlsx" ;
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("FirstSheet");  

            Row rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("No.");
            rowhead.createCell(1).setCellValue("Name Çç Ğğ Iı İi Öö Şş Üü");
            rowhead.createCell(2).setCellValue("Address");
            rowhead.createCell(3).setCellValue("Email");

            Row row = sheet.createRow((short)1);
            row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue("Sankumarsingh");
            row.createCell(2).setCellValue("India");
            row.createCell(3).setCellValue("sankumarsingh@gmail.com");
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            */
            System.out.println("Your excel file has been generated. Result : " + result);

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
	}

}
