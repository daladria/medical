package com.magis.z.app.config.logging;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Simple Utility class for loading an external config file for logback
 * @author daniel
 */
public class LogBackConfigLoader {

	private Logger logger = LoggerFactory.getLogger(LogBackConfigLoader.class);
	
	public LogBackConfigLoader(String externalConfigFileLocation) throws IOException, JoranException{
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		File externalConfigFile = new File(externalConfigFileLocation);
		if(!externalConfigFile.exists()){
			throw new IOException("Logback External Config File Parameter does not reference a file that exists");
		}else{
			if(!externalConfigFile.isFile()){
				throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
			}else{
				if(!externalConfigFile.canRead()){
					throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
				}else{
					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					configurator.doConfigure(externalConfigFileLocation);
					
					logger.info("Configured Logback with config file from: " + externalConfigFileLocation);
				}
			}	
		}
	}
	
}


//Assumes a context.xml with the following contents:
/*<?xml version="1.0" encoding="UTF-8"?>
<Context>
	<Parameter name="CONFIGDIR" value="/usr/local/etc/exampleApp/"
		override="false" />
</Context>*/