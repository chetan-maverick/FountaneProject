package com.fountane.amazon.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoggerUtil 
{

	  public static Logger getLogger() 
	  {
	        return LogManager.getLogger(LoggerUtil.class);
	    }

}
