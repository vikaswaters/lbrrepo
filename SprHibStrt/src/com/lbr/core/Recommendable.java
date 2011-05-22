package com.lbr.core;

import java.util.Date;
import java.util.List;

import com.lbr.dao.hibernate.domain.Events;

public interface Recommendable {
	
	public boolean isUserInVicinity(int userLocID, int eventLocID);
	
	public List<EventRecommendationVO> getRecosForInterestedUsersOfTheseEvents(List<Events> events);
	
	public List<EventRecommendationVO> filterRecosForInterestedUsersAndInVicinityOfTheseEvents(List<EventRecommendationVO> draftRecos);
	
	public List<EventRecommendationVO> recommend(Date startDate, Date endDate, int currentLocationID);

}
