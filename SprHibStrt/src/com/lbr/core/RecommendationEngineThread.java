package com.lbr.core;

import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Logger;

import com.lbr.LbrConstants;
import com.lbr.web.struts.action.UserPreferenceAction;

public class RecommendationEngineThread extends Thread {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	public RecommendationEngineThread(String name){
		super(name);
	}

	public void run() {
		try {
			java.lang.Thread.sleep(10000); // one time pause so that Spring loads  the beans etc.
			while (true) {
				long time1 = System.currentTimeMillis();
	        	logger.info("********* Running the Scheduled Recommendation Engine @ "+Calendar.getInstance().getTime()+" ************");
        		List<EventRecommendationVO> allRecosForAllUsers = RecommendationEngine.getEventsRecommendation("0", null); // All recos  for all users
				//logger.debug("ProducerThread==>"+ Thread.currentThread().getName()+" ObjID produced="+obj.getId()+" NumObjects in Q(after)="+Processor.queue.size());
				RecommendationEngine.lbrRecommendations = new Recommendations();
				RecommendationEngine.lbrRecommendations.setFinalRecommendations(allRecosForAllUsers);
				RecommendationEngine.lbrRecommendations.setGeneratedOn(Calendar.getInstance().getTime());
	        	long time2 = System.currentTimeMillis();
	        	logger.info("*********** Scheduled Recommendation generated in "+(time2-time1)+"(ms) **********");
        		java.lang.Thread.sleep(LbrConstants.RECCOMENDATION_THREAD_SLEEP_TIME_MINUTES*60000);
			}
	} catch (Exception e) { e.printStackTrace(); }
	}
}
