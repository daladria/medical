package com.magis.utilities;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class ApplicationOperations {
	protected final Log logger = LogFactory.getLog(getClass());

	public String  getApplicationPath(HttpServletRequest request) throws NumberFormatException, Exception{
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("").replace("\\", "/") ;
		return appPath;
	}


	public String  getRawRequest(HttpServletRequest request){
		String result ="";	
		try {
			result = request.getMethod();
			String headerName ="";
			Enumeration<?> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				headerName = (String)headerNames.nextElement();
				result = result + "\r\n" + headerName + " - " + request.getHeader(headerName);
			}
			String paramName  = "";
			Enumeration<?> params = request.getParameterNames(); 
			while(params.hasMoreElements()){
				paramName = (String)params.nextElement();
				result = result + "\r\n" + paramName+" - "+request.getParameter(paramName);
			}
		} catch (Exception e) {
			logger.error( "Error:", e);
		}
		return result;
	}

	public String  getRawRequest(HttpUriRequest request){
		String result ="";	
		try {

			if (request==null) return "";	
			result = request.toString() + "\r\n";
			String headerName ="";
			Header[] headerNames = request.getAllHeaders();
			for (int i=0;i<headerNames.length;i++){
				headerName = headerNames[i].getName();
				result = result + "\r\n" + headerName + " - " + headerNames[i].getValue();
			}

		} catch (Exception e) {
			logger.error( "Error:", e);
		}
		return result;
	}


	public String  getRawResponse(HttpServletResponse response){
		String result ="";	
		try {
			result = "Status:" + response.getStatus() + "";
			String headerName ="";
			Collection<String> headerNames =  response.getHeaderNames();
			Iterator<String> hn = headerNames.iterator();
			while(hn.hasNext()) {
				headerName = (String)hn.next();
				result = result + "\r\n" + headerName + " - " + response.getHeader(headerName);
			}
		} catch (Exception e) {
			logger.error( "Error:", e);
		}
		return result;
	}


	public HashMap<String,String>  getRawResponse(CloseableHttpResponse response){
		String result ="";	
		HashMap<String, String> header=new HashMap<>();
		try {
			result = "Status:" + response.getStatusLine().getStatusCode()+ "";
			header.put("status", response.getStatusLine().getStatusCode()+"");
			Header[] headerNames =  response.getAllHeaders();
			for (int i=0;i<headerNames.length;i++){
				result = result + "\r\n" + headerNames[i].getName() + " - " + headerNames[i].getValue();
				header.put(headerNames[i].getName(), headerNames[i].getValue());
			}
			header.put("logText", result);
		} catch (Exception e) {
			logger.error( "Error:", e);
		}
		return header;
	}

}
