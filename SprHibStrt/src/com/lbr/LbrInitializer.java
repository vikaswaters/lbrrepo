package com.lbr;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.lbr.core.RecommendationEngineThread;

public class LbrInitializer implements ServletContextListener{
	private static final Logger logger = Logger.getLogger(LbrInitializer.class);
	public void contextInitialized(ServletContextEvent event)
	{
		ServletContext sc = event.getServletContext();
		logger.info("==== LBRInitializer called  ====");
		//java.util.ResourceBundle appEnvBundle =	java.util.ResourceBundle.getBundle("Environment");

		// The Application Environment Resource Bundle
		// goes in the application context so accessible from elsewhere.
		//sc.setAttribute(ApplicationConstants.APP_ENV_RESOURCE_BUNDLE, appEnvBundle );

		/* start the scheduler service */
		if(!LbrConstants.RECCOMENDATION_ENGINE_LIVE_ON_REQUEST){
			logger.info("==== RECCOMENDATION_ENGINE: AUTO-SCHEDULED every"+LbrConstants.RECCOMENDATION_THREAD_SLEEP_TIME_MINUTES+" Mins ====");
			try
			{
				new RecommendationEngineThread("LBR Recommmedation thread").start();
			}
			catch( Exception se )
			{
			}
		}
		else{
			logger.info("==== RECCOMENDATION_ENGINE: ON-DEMAND  ====");
		}
	}

	/**
	 * Web application shut down includes destroying listener context.
	 */
	public void contextDestroyed(ServletContextEvent event)
	{

		ServletContext sc = event.getServletContext();

		// Remove Application Environment Resource Bundle from the servletContext
	//	sc.removeAttribute(ApplicationConstants.APP_ENV_RESOURCE_BUNDLE);

	}

}
