package com.lbr.core;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Recommendations {
	
	private Date generatedOn = Calendar.getInstance().getTime();
	private List<EventRecommendationVO> finalRecommendations;
	public Date getGeneratedOn() {
		return generatedOn;
	}
	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}
	public List<EventRecommendationVO> getFinalRecommendations() {
		return finalRecommendations;
	}
	public void setFinalRecommendations(
			List<EventRecommendationVO> finalRecommendations) {
		this.finalRecommendations = finalRecommendations;
	}
	
	
	

}
