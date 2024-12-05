package com.magis.utilities;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magis.schedulers.ApplicationParameters;


public class Http {
	private static Log logger = LogFactory.getLog("HttpUtilitiesV2");
	private static Log loggerApp = LogFactory.getLog("HttpClient");
	
	
	public static HashMap<String, Object> callHttp(String url, String username,String password,String data,String method, Map<String,String> headers, Map<String, Object> reqJson, String auth, ApplicationParameters params, ObjectMapper obm){
		HttpEntity dataEntity = null;
		Long startTime=System.currentTimeMillis(), responseTime = 0L, totalTime = 0L;
		String host ="",reason="",command ="",requester ="", userToken ="";
		CloseableHttpClient httpclient=null;
		SSLContext sslContext = null;		
		HttpRequestBase request =null;
		CloseableHttpResponse response =null;
		HashMap<String, String> rawHeader=null;
		HashMap<String, Object> result=new HashMap<>();
		CredentialsProvider credsProvider =null;		
		int timeout = 30000;
		if (auth ==null) auth ="";
		try {
			if (params!=null) {
				timeout = params.getHttpTimeout();
				//httpclient = params.https.poll(0, TimeUnit.MILLISECONDS);	
				
				//httpclient.getConnectionManager().
			}
		} catch (Exception e) {
			httpclient = null;
		}
		if (httpclient ==null) {
			try {
				RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout)
						.setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
	
				if (url.startsWith("https://")) {
					sslContext = SSLContextBuilder.create().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build();
				}	
				if (auth.length()>0) {
					credsProvider = new BasicCredentialsProvider();
					if (auth.equals("NTLM")) {
						String domain = "";
						if ((username!=null) & (username.length()>0)){
							int pos1 = username.indexOf("\\");
							if (pos1>0){
								domain =username.substring(0,pos1);
								username = username.substring(pos1 +1);
							}
						}              
						int pos1 = url.indexOf("/",url.indexOf("://") + 3);
						if (pos1>0) host  = url.substring(0,pos1 );
						credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new NTCredentials(username, password, host, domain));
					}else if (auth.equals("Plain")) {
						credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials(username, password));
					}
					httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).setDefaultRequestConfig(config).disableCookieManagement()
							.setConnectionManagerShared(true).build();
					
				}else {
					if (sslContext==null) {
						httpclient = HttpClients.custom().setDefaultRequestConfig(config).disableCookieManagement()
								//.setConnectionManager(Util.getConnectionManager()) // shared connection manager
					            .setConnectionManagerShared(true).build();
					}else {
						HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
						SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext,allowAllHosts);
						httpclient = HttpClients.custom().setSSLSocketFactory(connectionFactory).setDefaultRequestConfig(config).disableCookieManagement()
								 .setConnectionManagerShared(true).build();
					}
				}
			}catch (Exception e) {
				loggerApp.error("Error When Creating HttpClient:" ,e);
			}

		}
/*
		if (data.length()>0) {
			HttpEntity entity = new ByteArrayEntity(data.getBytes("UTF-8"));
			((HttpRequestBase) request).setEntity(entity);
		}
*/

		
		try {
			if (data.length()>0)  dataEntity = new ByteArrayEntity(data.getBytes("UTF-8"));
			
			if (method.equalsIgnoreCase("POST")) {
				request= new HttpPost(url);
				if (dataEntity !=null) ((HttpPost)request).setEntity(dataEntity);
			}else if (method.equalsIgnoreCase("GET")) {
				if (dataEntity ==null) {request= new HttpGet(url);}
				else {
					request= new HttpGetWithEntity(url);
					((HttpGetWithEntity)request).setEntity(dataEntity);
				}
			}else if (method.equalsIgnoreCase("PUT")) {
				request= new HttpPut(url);
				if (dataEntity !=null) ((HttpPut)request).setEntity(dataEntity);
			}else if (method.equalsIgnoreCase("DELETE")) {
				request= new HttpDelete(url);
			}else if (method.equalsIgnoreCase("HEAD")) {
				request= new HttpHead(url);
			}
			if (headers!=null) {
				for(Entry<String, String> entry: headers.entrySet()) {
					request.setHeader(entry.getKey(),entry.getValue());
				}
			}
			request.setHeader("Connection", "close");
			if (username.length()>0) {
				request.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()) );
			}
				
			responseTime=System.currentTimeMillis();
			response = httpclient.execute(request);
			responseTime= System.currentTimeMillis() -responseTime;

			rawHeader=getRawResponse(response,loggerApp);
			result.put("header", rawHeader);
			result.put("responseText", "");
			
			try {
				HttpEntity entity = response.getEntity();
					
				if ((entity!=null) && (entity.getContentLength()!=0)){
					result.put("responseText", EntityUtils.toString(entity, "UTF-8"));
				}
			} catch (Exception e) {
				loggerApp.error("Error:", e);
			}
			
			return result; 
		}catch(Exception e){
			reason = e.toString();
			return null;
		}finally {
			String resp="";
			if ((result==null) || (result.size()==0)){
				resp="";
			}else if (result.get("responseText").getClass().equals(String.class)) {
				resp=result.get("responseText").toString();
			}else {
				try {
					resp ="" +response.getStatusLine().getStatusCode();
				} catch (Exception e2) {
					resp ="ERROR in HTTP CALL";
				}
			}
			
			try {
				command=(String) reqJson.get("command");
				if (command ==null) command="undefined-Command";					
			} catch (Exception e) {
				command ="";
			}

		    try {
		    	requester = (String) ((Map)reqJson.get("user")).get("userId");
/*
		    	if (reqJson.get("user") !=null && ((Map)reqJson.get("user")).get("mobile")!=null)
		    		requester = (String) ((Map)reqJson.get("user")).get("mobile");
		    	else if (reqJson.get("user") !=null && ((Map)reqJson.get("user")).get("email")!=null)
		    		requester = (String) ((Map)reqJson.get("user")).get("email");
		    	else if (reqJson.get("mobile") !=null)
		    		requester = (String) reqJson.get("mobile");
		    	else if (reqJson.get("email") !=null)
		    		requester = (String) reqJson.get("email");
		    	else requester = (String) reqJson.get("un");
	*/
			} catch (Exception e) {
					requester ="";
			}
						
			try {
				userToken=(String) reqJson.get("id");
				if (userToken ==null) userToken="";
			} catch (Exception e) {
				userToken="";
			}

			int statusCode =-1;
			try {
				statusCode= response.getStatusLine().getStatusCode();
			} catch (Exception e2) {}

			totalTime = System.currentTimeMillis() -startTime;
			String sid = "";
			try {
				sid = "" + (String) reqJson.get("sessionId") ;				
			} catch (Exception e2) {}


			try {
				Map<String, Object> log = new LinkedHashMap<String,Object>();
				log.put("session", sid );
				log.put("total_time", totalTime);
				log.put("execute_time", responseTime);
				log.put("command", command);
				log.put("statusCode", statusCode);
				log.put("requester", requester);
				log.put("reason", reason);
				log.put("url",url);
				log.put("method",method);
				log.put("clientId", reqJson.get("id"));
				if (logger.isDebugEnabled()) {
					log.put("requestData", data);
					log.put("responseData", resp);
					log.put("rawRequestHeader", rawHeader);
				}
				if (reason.length()==0) {
					logger.info(obm.writeValueAsString(log));
				}else {
					logger.error(obm.writeValueAsString(log));
				}
			} catch (Exception e2) {	
				loggerApp.error("Error:" ,e2 );
			}
			try {
				/*
				String query =CassandraSQL.logs.INSERT_HTTP_CALL_LOGS.replace("<id>", UUID.randomUUID().toString());
				try {query = query.replace("<url>",url);} catch (Exception e2) {query = query.replace("<url>","");}
				try {query = query.replace("<requester>",requester);} catch (Exception e2) {query = query.replace("<requester>","");}
				try {query = query.replace("<statusCode>","" + statusCode);} catch (Exception e2) {query = query.replace("<statusCode>","-1");}
				try {query = query.replace("<total_time>","" + totalTime);} catch (Exception e2) {query = query.replace("<total_time>","-1");}
				try {query = query.replace("<execute_time>","" + responseTime);} catch (Exception e2) {query = query.replace("<execute_time>","-1");}
				try {query = query.replace("<reason>",reason);} catch (Exception e2) {query = query.replace("<reason>","");}
				try {query = query.replace("<method>",method);} catch (Exception e2) {query = query.replace("<method>","");}
				try {query = query.replace("<command>",command);} catch (Exception e2) {query = query.replace("<command>","");}
				try {query = query.replace("<request>",data);} catch (Exception e2) {query = query.replace("<request>","");}
				try {query = query.replace("<response>",resp.replace("'", "''"));} catch (Exception e2) {query = query.replace("<response>","");}
				try {query = query.replace("<session>",sid);} catch (Exception e2) {query = query.replace("<session>","");}
				try {query = query.replace("<date_time>", DateTimeUtilities.getCurrentDay("yyyy-MM-dd HH:mm:ss.SSS"));} catch (Exception e2) {query = query.replace("<date_time>","");}
				*/
				//CasOps.exec(query, params.getTemplate());// Cassandra için açılacak
			} catch (Exception e) {
				loggerApp.error("Error:", e);
			}

			try {response.close();} catch (Exception e2) {}
			try {request.releaseConnection();} catch (Exception e2) {}
			try {httpclient.close();} catch (Exception e2) {}
			try {
//				params.https.put(httpclient);	
			} catch (Exception e2) {
				loggerApp.error("Error:" , e2);
			}
			
		}

	}



	public String getMsisdnFromCookie(HttpServletRequest request,
			boolean msisdnHex) {
		String msisdn = "";

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i] != null) {
					if ("User-Identity-Forward-msisdn"
							.equalsIgnoreCase(cookies[i].getName())) {
						msisdn = msisdnHex ? hex2ascii(cookies[i].getValue())
								: cookies[i].getValue();
						break;
					}
				}
			}
		}else{
			msisdn=request.getHeader("x-msisdn");
		}

		return msisdn;
		//return "905552554545";

	}

	private String hex2ascii(String s) {
		StringBuilder ascii = new StringBuilder();
		for (int i = 0; i < s.length(); i += 2) {
			int c = Integer.parseInt(
					s.substring(i, Math.min(i + 2, s.length())), 16);
			ascii.append(c == 0 ? '?' : (char) c);
		}
		return ascii.toString();
	}
                  
	public static byte[] readAsByte(BufferedInputStream input) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = input.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}
	
	public static HashMap<String,String>  getRawResponse(CloseableHttpResponse response, Log logger){
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
			loggerApp.error( "Error:", e);
		}
		return header;
	}
	
	public static void main(String[] args) {
		HashMap<String, Object> result =null;
		long start = System.currentTimeMillis();
		for (int i=0;i<10;i++) {
			//result = Http.callHttp("http://192.168.100.112:8080/kdelis", "", "", "", "GET", null, null, null, 30000);
			result = Http.callHttp("https://google.com", "", "", "", "GET", null, null, null, null, new ObjectMapper());
			System.out.println("Result:" + result.get("responseText"));
		}
		System.out.println("Complete Time:" + (System.currentTimeMillis()-start));
		
	}
}
