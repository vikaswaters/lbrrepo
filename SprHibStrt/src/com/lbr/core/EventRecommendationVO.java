package com.lbr.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Users;

/**
 * Represents an event and all the users who need to be informed
 * @author vranjan
 *
 */
public class EventRecommendationVO { 

	private List<String> finalUserIDsToBeInformed;
	private Events event;
	private List<Users> interminUsersToBeInformed;
	private Map<String, Users> usersToBeInformed ; // store userIds as key
	
	public Events getEvent() {
		return event;
	}



	public List<String> getFinalUserIDsToBeInformed() {
		return finalUserIDsToBeInformed;
	}



	public void setFinalUserIDsToBeInformed(List<String> finalUserIDsToBeInformed) {
		this.finalUserIDsToBeInformed = finalUserIDsToBeInformed;
	}



	public List<Users> getInterminUsersToBeInformed() {
		return interminUsersToBeInformed;
	}



	public void setInterminUsersToBeInformed(List<Users> interminUsersToBeInformed) {
		this.interminUsersToBeInformed = interminUsersToBeInformed;
	}



	public void setEvent(Events event) {
		this.event = event;
	}



	public Map<String, Users> getUsersToBeInformed() {
		return usersToBeInformed;
	}



	public void setUsersToBeInformed(Map<String, Users> usersToBeInformed) {
		this.usersToBeInformed = usersToBeInformed;
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("\n-----------EventRecommendationVO Begin------------\n");
		sb.append("\tEventID= ");
		sb.append(" "+this.event.getEventId());
		sb.append("\tEventSubcatID= ");
		sb.append(" "+this.event.getSubcategory().getSubCatId());
		sb.append("\tEvent LocID= ");
		sb.append(" "+this.event.getLocations().getLocationId()+"\n");
		sb.append("\tUserIDs= ");
		List<String> usersIDs = this.getFinalUserIDsToBeInformed();
		for (Iterator iterator = usersIDs.iterator(); iterator.hasNext();) {
			String userID = (String) iterator.next();
			Users object = (Users) this.getUsersToBeInformed().get(userID);
			sb.append("\n\t "+object.getMobileNo()+" "+object.getUserName());
			sb.append("\tPref "+object.getPreferences()+" LocID "+object.getLocationsByCurrentLocationId().getLocationId());
		}
		sb.append("\n-----------EventRecommendationVO End------------");
		return sb.toString();
	}




}
