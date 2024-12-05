package com.magis.session.controller;

import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.magis.dao.interfaces.ExecuteSqls;
import com.magis.model.User;
import com.magis.schedulers.ApplicationParameters;
import com.magis.utilities.FileUtilities;
import com.magis.utilities.UserUtilities;

@Controller
@SessionAttributes
public class GetBinaries {
	protected final Log logger = LogFactory.getLog(getClass()); 
	private ExecuteSqls executeSqls = null;
	private ApplicationParameters parameters;
	private MessageSource messageSource;


	//http://localhost:8080/etsmice/getDocument?id=80ce0ef8-f1df-4735-8b1c-2129dd92dasdff
	/**
	 * @param request
	 * @param session
	 * @param response
	 * @param cmd
	 * @param type
	 */
	
	
	
	//http://localhost:8080/etsmice/getUserImage?id=7b499d8c-4a80-4090-89d2-a44a81e2bbe7
		@RequestMapping(value = "/exportAll", method = {RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody void  exportAll( HttpServletRequest request ,HttpSession session,HttpServletResponse response,@RequestParam("id") String cmd,@RequestParam(value="type", required=false) String type) {	 
			response.setHeader("X-Powered-By", "XDXDXD"); 
			String userName="";
			//String photo="";	
			try {
				OutputStream out =response.getOutputStream();

				byte[] photo =null;
					//				userName = (String) user.get("profilPhoto");
					try {
						photo = FileUtilities.readFileAsBinary("d:/oceans.mp4");
					} catch (Exception e) {
						//logger.error("USer Photo For User Can not be Found. msisdn:" + userName);
					}
					if ((type==null) || (!type.equalsIgnoreCase("base64"))) {
						 response.setHeader("Content-disposition", "attachment; filename=" + cmd + ".mp4" );     
						 response.addHeader("Content-Transfer-Encoding", "binary");
						 out.write( photo);
						try {
							out.close();
						} catch (Exception e) {
						}
					}else {
						out.write( photo);
					}
					response.flushBuffer();				
			}catch (Exception ex) {
				throw new RuntimeException("IOError writing file to output stream");
			}finally{

			}
		}

	
	
	
	
	
	
	
	@RequestMapping(value = "/getDocument", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody void  getDocument( HttpServletRequest request ,HttpSession session,HttpServletResponse response,@RequestParam("id") String cmd,@RequestParam(value="type", required=false) String type) {	 
		response.setHeader("X-Powered-By", "XDXDXD"); 
		//String photo="";	
		try {
			
			OutputStream out =response.getOutputStream();
			byte[] document = null;
			try {
				document= FileUtilities.readFileAsBinary("C:/sts-bundle/conf/etsmice/excellDocument/" +cmd + ".xlsx");
			} catch (Exception e) {
				//logger.error("User Photo For User Can not be Found. msisdn:" + userName);
			}
			if ((type==null) || (!type.equalsIgnoreCase("base64"))) {
				response.setHeader("Content-disposition", "attachment; filename=" + cmd + ".xlsx");     
				response.addHeader("Content-Transfer-Encoding", "binary");
				
				out.write(document);
				//out.write( ((String) resp.get("DATA")).getBytes());
				try {
					out.close();
				} catch (Exception e) {
					//logger.error("Error description",e);
				}
			}else {
				out.write( document);
			}
			response.flushBuffer();				
		}catch (Exception ex) {
			//logger.error("Error:", ex);
			throw new RuntimeException("IOError writing file to output stream");
		}finally{
			
		}
	}
	
	
	//http://localhost:8080/etsmice/getCompanyLogo?id=80ce0ef8-f1df-4735-8b1c-8fa6a8d16e73
	/**
	 * @param request
	 * @param session
	 * @param response
	 * @param cmd
	 * @param type
	 */
	@RequestMapping(value = "/getCompanyLogo", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody void  getCompanyLogo( HttpServletRequest request ,HttpSession session,HttpServletResponse response,@RequestParam("id") String cmd, @RequestParam(value="type", required=false) String type) {	 
		response.setHeader("X-Powered-By", "XDXDXD"); 
		//String photo="";	
		try {

			OutputStream out =response.getOutputStream();
				byte[] photo = null;
				try {
					photo= FileUtilities.readFileAsBinary(parameters.getParameter("company.logoImages.save.folder") +cmd + ".jpg");
				} catch (Exception e) {
					//logger.error("User Photo For User Can not be Found. msisdn:" + userName);
				}
				if ((type==null) || (!type.equalsIgnoreCase("base64"))) {
					 response.setHeader("Content-disposition", "attachment; filename=" + cmd + ".jpg");     
					 response.addHeader("Content-Transfer-Encoding", "binary");

					out.write(photo);
					//out.write( ((String) resp.get("DATA")).getBytes());
					try {
						out.close();
					} catch (Exception e) {
						//logger.error("Error description",e);
					}
				}else {
					out.write( photo);
				}
				response.flushBuffer();				
		}catch (Exception ex) {
			//logger.error("Error:", ex);
			throw new RuntimeException("IOError writing file to output stream");
		}finally{

		}
	}

//http://localhost:8080/medical/getUserImage?id=5	
	@RequestMapping(value = "/getUserImage", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody void  getUserImage( HttpServletRequest request ,HttpSession session,  Locale locale,
			HttpServletResponse response, @RequestParam(value="id", required=false) String usrId) {	 
		response.setHeader("X-Powered-By", "XDXDXD"); 
		User user=null;
		String fileName="";
		try {
			 if (usrId!=null && usrId.trim().equals("bos")){
					fileName = "bos.png";
			}else if (usrId!=null && usrId.trim().length()>0 ) {
					user = UserUtilities.getUserInfo(parameters, usrId);
					if(user==null) fileName = "bos.png";
					else fileName = user.getAvatar();
			}else {
				user = UserUtilities.getUserInfo(true, parameters, executeSqls, messageSource,locale, logger);
				if(user==null) fileName = "bos.png";
				else fileName = user.getAvatar();
			}

			OutputStream out =response.getOutputStream();
			byte[] photo =null;
			//				userName = (String) user.get("profilPhoto");
			try {
				photo = FileUtilities.readFileAsBinary(parameters.getParameter("user.userImages.folder") + fileName);
			} catch (Exception e) {
				try {
					photo = FileUtilities.readFileAsBinary(parameters.getParameter("user.userImages.folder") + "bos.png");
					fileName = "bos.png";
				} catch (Exception ex) {
					logger.error("Error:", e);
				}
			}
			 response.setHeader("Content-disposition", "attachment; filename=" + fileName );     
			 response.addHeader("Content-Transfer-Encoding", "binary");
			 out.write( photo);
			 try {
				out.close();
			 } catch (Exception e) {
				logger.error("Error description",e);
			 }

			
		}catch (Exception ex) {
			//logger.error("Error:", ex);
			throw new RuntimeException("IOError writing file to output stream");
		}finally{

		}
	}

	public ApplicationParameters getParameters() {
		return parameters;
	}

	public void setParameters(ApplicationParameters parameters) {
		this.parameters = parameters;
	}

	public ExecuteSqls getExecuteSqls() {
		return executeSqls;
	}


	public void setExecuteSqls(ExecuteSqls executeSqls) {
		this.executeSqls = executeSqls;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
