package com.magis.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApiUserInsert {
    //final static char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();
    final static char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();

	public static void main(String[] args) throws Exception {
		String fileInputPath = "E:\\projects\\belbim\\ApiUsersInsert\\JavaBooks.xlsx";
		String fileOutputPath = "E:\\projects\\belbim\\ApiUsersInsert\\JavaBooks1.xlsx";
		String sheetName="Java Books";
		int pwdDigit = 16;
		String SQL = "INSERT INTO api_users(id, description, password, token, token_expire_date, refresh_token, refresh_token_expire_date, is_deleted, create_date, last_login_date) VALUES ('<userName>','<name>','<password>', null, null, null, null, false, now(), null);";
		List<List<String>> ret = new ArrayList();
		try {
            FileInputStream fInputStream = new FileInputStream(fileInputPath.trim());
            /* Create the workbook object. */
            Workbook excelWookBook = new XSSFWorkbook(fInputStream);
            Sheet employeeSheet = excelWookBook.getSheet(sheetName);
            
            int firstRowNum = employeeSheet.getFirstRowNum();
            int lastRowNum = employeeSheet.getLastRowNum();
            lastRowNum = 950;
            /* Because first row is header row, so we read data from second row. */
            String mustNo="",temNo="",unvan="",username="",pwd="",sql="";
            for(int i=firstRowNum +1 ; i<lastRowNum+1; i++)
            {
                Row row = employeeSheet.getRow(i);
                
//                int firstCellNum = row.getFirstCellNum();
 //               int lastCellNum = row.getLastCellNum();
/*
                for(int j = firstCellNum ; j < lastCellNum; j++)
                {
                	cellValue = "";
                    try {
                    	cellValue = row.getCell(j).getStringCellValue();
					} catch (Exception e) {
						cellValue = "" + row.getCell(j).getNumericCellValue();
					}
//                    System.out.println(i + "-" + j + "=" + cellValue);
                }
  */              
                mustNo="";temNo="";unvan="";username="";pwd="";sql="";                
                boolean cont = false;	
                mustNo="";temNo="";unvan="";
                try {
                    try {mustNo = row.getCell(0).getStringCellValue();} catch (Exception e) {mustNo = "" + (int) row.getCell(0).getNumericCellValue();}
				} catch (Exception e) {}
                try {
                    try {temNo = row.getCell(1).getStringCellValue();} catch (Exception e) {temNo = "" + (int) row.getCell(1).getNumericCellValue();}
				} catch (Exception e) {}
                try {
                    try {unvan = row.getCell(2).getStringCellValue();} catch (Exception e) {unvan = "" + (int) row.getCell(2).getNumericCellValue();}
				} catch (Exception e) {}
                
                
               if (cont || (mustNo.trim().length()==0 && temNo.trim().length()==0 &&unvan.trim().length() ==0 )) break;
//                username = mustNo.replaceAll(".00", "").replaceAll(".0", "") + "-" + temNo.replaceAll(".00", "").replaceAll(".0", "");
                username = mustNo + "-" + temNo ;
                pwd = PasswordGenerator.generateOTP(pwdDigit);
                sql = SQL.replace("<userName>",username).replace("<name>", unvan).replace("<password>", pwd);
                System.out.println(username + "-" + pwd+ "-" + unvan + "-" + sql);
                
                Cell cell = row.createCell(3);
                cell.setCellValue(username);
                cell = row.createCell(4);
                cell.setCellValue(pwd);
                cell = row.createCell(5);
                cell.setCellValue(sql);
            }
            
            try (FileOutputStream outputStream = new FileOutputStream(fileOutputPath)) {
            	excelWookBook.write(outputStream);
            }	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
 
/*		
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
         
        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };
 
        int rowCount = 0;
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream("E:\\projects\\belbim\\ApiUsersInsert\\JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }	
        */
//		for (int i=0;i<100;i++)
//			System.out.println(generateOTP(16));
		System.out.println("Finished...");
	}
	public static String generateOTP(int digit) {
		StringBuilder password = new StringBuilder(); 		
		Random random = new Random();
		for (int i=0;i<digit;i++)
			 password.append(allAllowed[random.nextInt(allAllowed.length)]);
		return password.toString();
	}

}
