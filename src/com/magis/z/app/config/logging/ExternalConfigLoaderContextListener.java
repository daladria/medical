package com.magis.z.app.config.logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple utility listener to load certain properties before Spring Starts up.
 * 
 * Add this entry to your web.xml:
 * <pre><listener>
    <listener-class>com.example.app.config.logging.ExternalConfigLoaderContextListener</listener-class>
  </listener></pre>
 * 
 * @author daniel
 *
 */
public class ExternalConfigLoaderContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ExternalConfigLoaderContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String configLocation = sce.getServletContext().getInitParameter("logbackConfigurationDirectory");
		if(configLocation == null){
			configLocation = System.getenv("logbackConfigurationDirectory"); 
		}
		
		try{
			new LogBackConfigLoader(configLocation + "logback.xml");
		}catch(Exception e){
			logger.error("Unable to read config file", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {		
	}
}



