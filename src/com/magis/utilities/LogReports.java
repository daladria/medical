package com.magis.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogReports {
//	protected static Log logger = LogFactory.getLog(LogReports.class);
	
	public static synchronized String readFile(String fileName) throws IOException{
		Path path = Paths.get(fileName);
		String result ="";
		List<String> contents = Files.readAllLines(path);
		for(String content:contents)
			result = result +content;
		return result;
	}

	public static synchronized void writeFile(String fileName, String data) {
		boolean write=false;
		while (!write) {
			try {
				Path path = Paths.get(fileName);
				if (Files.exists(path)) 
					Files.write(path,  data.getBytes(), StandardOpenOption.APPEND);
				else Files.write(path, data.getBytes());
				write =true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void writeFile(String fileName, String msisdn, String language, String page) {
		boolean write=false;
		while (!write) {
			try {
				Path path = Paths.get(fileName);
				if (Files.exists(path)) 
					Files.write(path,  (msisdn + "><" + language + "><" + page + "><" + DateUtility.getCurrentDay("yyyy-MM-dd HH:mm:ss") + "," ).getBytes(), StandardOpenOption.APPEND);
				else Files.write(path, (msisdn + "><" + language + "><" + page + "><" + DateUtility.getCurrentDay("yyyy-MM-dd HH:mm:ss") + "," ).getBytes());
				write =true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Set<String> listFilesUsingJavaIO(String dir) {
	    return Stream.of(new File(dir).listFiles())
	      .filter(file -> !file.isDirectory())
	      .map(File::getName)
	      .collect(Collectors.toSet());
	}
	
	public static void printReports(String startDate, String endDate, String inputDir, String logFileName, String command, String outputFile, String errorLogFile) throws IOException {
		//cid ="*" means ever campaign
		String[] tmpList =null;
		try {
			if (startDate.trim().length() !=endDate.trim().length()) {
				System.out.println("Date mismatch Error: date lengths are not same");
				return;
			}
			Path path_error = Paths.get(errorLogFile);
			Files.write(path_error,  "Error Logs:".getBytes());

			int dateLength = startDate.trim().length();
			
			long sDate = Long.parseLong(startDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
			long eDate = Long.parseLong(endDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));

			Map<String, Long> result = new HashMap<>();
			List<String> processedFiles = new ArrayList<>();
			
			
			File folder = new File(inputDir);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  File file = listOfFiles[i];
			  if (file.isFile()) {
				  String fileName = file.getName();
			    if (fileName.toLowerCase().startsWith(logFileName.toLowerCase() )) {
			    	tmpList = fileName.split("\\.");
//			    	String date = tmpList[1];
			    	long cFileDate = Long.parseLong(tmpList[1].trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").replaceAll("_", "") + "00");

			    	if (cFileDate>=sDate && cFileDate<=eDate) {
			    		System.out.println("Processing file: " + fileName);
			    		processedFiles.add(fileName);
			    		
			    		//String data = LogReports.readFile(inputDir + fileName);
			    		//String[] dataList = data.split("\r\n");
			    		Path path = Paths.get(inputDir + fileName);
			    		List<String> contents = Files.readAllLines(path);
			    		int pos1=0;
			    		String status = "";
			    		long count = 0;
			    		for(String content:contents) {
			    			
			    			try {
				    			if (content.indexOf(command)>=0) {
				    				pos1 = content.indexOf("statusCode:");
				    				status = content.substring(pos1+ 11, pos1+ 14);
				    				if (result.get(status) ==null) count = 0;
				    				else count = result.get(status);
				    				count++;
				    				result.put(status, count);
				    				if (!status.startsWith("2")) {
				    					Files.write(path_error,  content.getBytes(), StandardOpenOption.APPEND);
				    					Files.write(path_error,  "\r\n".getBytes(), StandardOpenOption.APPEND);
				    				}
				    			}

							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("Error: "  + content + "\r\n  "  +  e.getStackTrace().toString());
							}
			    		}
			    		
			    	}
			    }
			  } 
			}
			
			Path path = Paths.get(outputFile);
			Files.write(path,  "Summary\r\n".getBytes());
			System.out.println("Summary");
			String output = "";
			for ( Entry<String, Long> entry : result.entrySet()) {
			    String key = entry.getKey();
			    Long val = entry.getValue();
			    output = key+ ":" + val+ "\r\n";
			    System.out.print(output);
			    Files.write(path,  output.getBytes(), StandardOpenOption.APPEND);
			}

			Files.write(path,  "\r\nProcessed Files: \r\n".getBytes(), StandardOpenOption.APPEND);
			
			java.util.Collections.sort(processedFiles);
			for (int i = 0;i<processedFiles.size();i++) {
				Files.write(path,  (processedFiles.get(i) + "\r\n" ).getBytes(), StandardOpenOption.APPEND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*
 * 
D:\>java -cp logReports.jar com.magis.utilities.LogReports "2019-04-28 12:00" "2019-06-28 12:00" "httpCallLogs." "command:carts.getCart," "D:\\conf\\carrefoursa\\reports\\sample\\" "D:\\conf\\carrefoursa\\reports\\summary.txt" "D:\\conf\carrefoursa\\reports\\errorLogs.txt"
 */
	
// java LogReports startDate endDate fileFilter command	 inputDir outputFile errorLogFile
	
// java LogReports "2019-04-28 12:00" "2019-06-28 12:00" "httpCallLogs." "command:carts.getCart," "D:\conf\carrefoursa\reports\sample\" "D:\conf\carrefoursa\reports\summary.txt" "D:\conf\carrefoursa\reports\errorLogs.txt"	
//D:\reports>java -cp logReports.jar com.magis.utilities.LogReports "2019-04-21 00:00" "2019-06-28 23:00" "httpCallLogs." "command:carts.getCart," "D:\\reports\\data\\" "D:\\reports\\summary.txt" "D:\\reports\\errorLogs.txt""

	public static void main(String[] args) throws IOException {
/*
		String outputFile = "D:\\conf\\carrefoursa\\reports\\summary.txt";
		String errorLogFile = "D:\\conf\\carrefoursa\\reports\\errorLogs.txt";
		String inputDir = "D:\\conf\\carrefoursa\\reports\\sample\\";
		String startDate = "2019-04-28 12:00"; // record start date it can be "2019-05-14"
		String endDate = "2019-06-28 14:00";   //record End date
		String fileFilter="httpCallLogs.";
		String command = "command:carts.getCart,";
*/	
		System.out.println("Program Started....");
		try {
			String startDate = args[0]; 
			String endDate = args[1];   
			String fileFilter= args[2];
			String command = args[3];
			String inputDir = args[4];
			String outputFile = args[5];
			String errorLogFile = args[6];
		
		
			String started =  "Report Started at : " + DateUtility.getCurrentDay("yyyy-MM-dd HH:mm:ss");
		
			printReports(startDate, endDate, inputDir,fileFilter, command, outputFile,errorLogFile);
			String ended =  "Report End at : " + DateUtility.getCurrentDay("yyyy-MM-dd HH:mm:ss");
			
			System.out.println( started);
			System.out.println( ended);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



/*

public static void printReports(String startDate, String endDate, String inputFile,String outputFile) throws IOException {
	
	if (startDate.trim().length() !=endDate.trim().length()) {
		System.out.println("Date mismatch Error: date lengths are not same");
		return;
	}
	int dateLength = startDate.trim().length();
	long sDate = Long.parseLong(startDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
	long eDate = Long.parseLong(endDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
	
	long  totalCount = 0;
	Map<String, Long> totalUniqueCount = new HashMap<>();
	String data = CampaignReports.readFile(inputFile);
	String[] dataList = data.split(",");
	String dateStr = "";
	String uniqueStr = "";
	for (int i=0;i<dataList.length;i++) {
		if (dataList[i]!=null && dataList[i].trim().length()>0 ) {
			String[] tmpList = dataList[i].split("><");
			dateStr = tmpList[2].trim().substring(0, dateLength).trim();
			uniqueStr = tmpList[0].trim();
			long cDate = Long.parseLong(dateStr.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
			if (cDate>=sDate && cDate<=eDate) {
				totalCount++;
				totalUniqueCount.put(uniqueStr, 1L);
			}
		}
	}
	
	System.out.println("Total Counts:" + totalCount + "\r\n");
	System.out.println("Total Unique Counts:" + totalUniqueCount.size());
}

	public static void printReportsByDate(String dateFormat, String inputFile,String outputFile) throws IOException {
		
		int dateLength = dateFormat.trim().length();
		
		//LinkedList<String> keys = new LinkedList<>();
		LinkedHashMap<String, Long> totalCount = new LinkedHashMap<>();
		LinkedHashMap<String, Long> totalUniqueCount = new LinkedHashMap<>();
		String data = CampaignReports.readFile(inputFile);
		String[] dataList = data.split(",");
		String dateStr = "";
		String uniqueStr = "";
		for (int i=0;i<dataList.length;i++) {
			String[] tmpList = dataList[i].split("><");
			dateStr = tmpList[2].trim().substring(0, dateLength).trim();
			uniqueStr = tmpList[0].trim() + "><" + tmpList[2].trim().substring(0, dateLength).trim();
			
			if (totalCount.get(dateStr) ==null) totalCount.put(dateStr, 1L);
			else totalCount.put(dateStr, totalCount.get(dateStr) + 1 );

			if (totalUniqueCount.get(uniqueStr) ==null) totalUniqueCount.put(uniqueStr, 1L);
			else totalUniqueCount.put(uniqueStr, totalUniqueCount.get(uniqueStr) + 1 );
		}
		
		System.out.println("Total Counts:");
		for ( Map.Entry<String, Long> entry : totalCount.entrySet()) {
		    String key = entry.getKey();
		    System.out.println(key + "\t\t\t" + entry.getValue());
		}
		
		System.out.println("Total Unique Counts:");
		for ( Map.Entry<String, Long> entry : totalUniqueCount.entrySet()) {
		    String key = entry.getKey();
		    System.out.println(key + "\t\t\t" + entry.getValue());
		}
	}


	public static void printReportsWithoutNio(String startDate, String endDate, String inputFile,String outputFile) throws IOException {
		
		if (startDate.trim().length() !=endDate.trim().length()) {
			System.out.println("Date mismatch Error: date lengths are not same");
			return;
		}
		int dateLength = startDate.trim().length();
		long sDate = Long.parseLong(startDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
		long eDate = Long.parseLong(endDate.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
		
		long  totalCount = 0;
		Map<String, Long> totalUniqueCount = new HashMap<>();

		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(inputFile);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		    	String[] dataList = line.split(",");
					String dateStr = "";
					String uniqueStr = "";
					for (int i=0;i<dataList.length;i++) {
						if (dataList[i]!=null && dataList[i].trim().length()>0 ) {
							String[] tmpList = dataList[i].split("><");
							dateStr = tmpList[2].trim().substring(0, dateLength).trim();
							uniqueStr = tmpList[0].trim();
							long cDate = Long.parseLong(dateStr.trim().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
							if (cDate>=sDate && cDate<=eDate) {
								totalCount++;
								totalUniqueCount.put(uniqueStr, 1L);
							}
						}
					}
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (Exception e) {
			logger.error("Error:",e);
		}finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		System.out.println("Total Counts:" + totalCount + "\r\n");
		System.out.println("Total Unique Counts:" + totalUniqueCount.size());
	}
*/


/*

Scanner reader = new Scanner(System.in);  // Reading from System.in

System.out.println("Enter a Start Date: ");
String startDate = reader.next(); 
System.out.println("Enter a End Date: ");
String endDate = reader.next();   
System.out.println("Enter a File Filter: ");
String fileFilter= reader.next();
System.out.println("Enter a Command: ");
String command = reader.next();
System.out.println("Enter a Input Directory: ");
String inputDir = reader.next();
System.out.println("Enter a Summary File: ");
String outputFile = reader.next();
System.out.println("Enter a Error Logs File: ");
String errorLogFile = reader.next();
reader.close();

*/