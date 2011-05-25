package com.lbr.core;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.VICINITY_POLICY;
import com.lbr.dao.genericdao.GenericDao;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.utils.ApplicationContextProvider;
import com.lbr.web.struts.form.UserPreferenceForm;

public class RecommendationEngine implements Recommendable{
private static final Logger logger = Logger.getLogger(RecommendationEngine.class);
	public static Recommendations lbrRecommendations;
	private String userID;  // is redundant for now


	public RecommendationEngine(String userID) {
		super();
		this.userID = userID;
	}

	public boolean isUserInVicinity(int userLocID, int eventLocID) {
		return true;  //isUserLocationInVicinityOfEventLocation(userLocID, eventLocID);
	}

	public List<EventRecommendationVO> getRecosForInterestedUsersOfTheseEvents(List<Events> events) {
		return getRecosForInterestedUsers(events);
	}

	public List<EventRecommendationVO> filterRecosForInterestedUsersAndInVicinityOfTheseEvents(List<EventRecommendationVO> draftRecos) {
		return getInterestedUsersInVicinityOfTheseEvents(draftRecos);
	}


	public List recommend(Date startDate, Date endDate, int currentLocationID) {
		 List<Events> events = getValidEventsWithinTimeWindow(startDate,  endDate, currentLocationID);
		 List<EventRecommendationVO> draftRecommendations = getRecosForInterestedUsersOfTheseEvents(events);
		 List<EventRecommendationVO> finalRecommendations = filterRecosForInterestedUsersAndInVicinityOfTheseEvents(draftRecommendations);
		 return finalRecommendations;
	}

	//-------------- private area  -----------

	 public static List<EventRecommendationVO> getEventsRecommendation(String currUserID, UserPreferenceForm objForm){
		 /*		 int currentLocationID = -1;  // get from operator
		 		 int userID = null;   // for all users
		 */
		 		 List<Date> dates = null;
		 		 int currentLocationID = -1;  // get from operator
		 		 if(objForm != null)
		 			 dates = LbrUtility.initializeDateRange(objForm);
		 		 else
		 			 dates = LbrUtility.getDateRange(0);

		 		 Recommendable recoEngine = new RecommendationEngine(currUserID);
		 		 List<EventRecommendationVO> recos = recoEngine.recommend((Date)dates.get(0), (Date)dates.get(1), currentLocationID);
		 		 //recoEngine.recommend(userID, (Date)dates.get(0), (Date)dates.get(1), currentLocationID);
		 		 return recos;
	 }
	 public static List<EventRecommendationVO> filterUserSpecificRecommendation(List<EventRecommendationVO> recos, Users user){
		 List<EventRecommendationVO> subsetRecoForCurrentUserOnly = new ArrayList<EventRecommendationVO>();
		 for (Iterator iterator = recos.iterator(); iterator.hasNext();) {
			EventRecommendationVO eventRecommendationVO = (EventRecommendationVO) iterator.next();
			if(eventRecommendationVO.getUsersToBeInformed().get(user.getUserName())!=null){ // this user needs to be informed abt the event
				subsetRecoForCurrentUserOnly.add(eventRecommendationVO);
			}
		}
		return subsetRecoForCurrentUserOnly;
	 }
	 /**
	  * Get all the valid  events within the time window, irrespective of any user preferences
	 * @param startDate
	 * @param endDate
	 * @param currentLocationID  redundant for the time being
	 * @return
	 */
	private List<Events> getValidEventsWithinTimeWindow(Date startDate, Date endDate, int currentLocationID){
		    String HQL_QUERY = "from Events e where ((:windowEndDate < e.startDate) OR (e.startDate between :windowStartDate and :windowEndDate) OR (e.endDate between :windowStartDate and :windowEndDate) or (e.startDate< :windowStartDate and e.endDate >:windowEndDate))";
	    	Timestamp ts1 = new Timestamp(startDate.getTime());
	    	Timestamp ts2 = new Timestamp(endDate.getTime());
	    	List paramsValues = new ArrayList();
	    	List paramsNames = new ArrayList();
	    	paramsValues.add(ts1);
	    	paramsValues.add(ts2);

	    	paramsNames.add("windowStartDate");
	    	paramsNames.add("windowEndDate");

			GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
			//Events ev = (Events)daoEvents.read(5);
			//logger.debug("### EventName= "+ev.getName());
			List results = daoEvents.customHibernateQuery(HQL_QUERY, paramsNames, paramsValues);
			if(results!=null){
				logger.debug("\n######## Total valid Events in dateRange["+startDate+","+endDate+"] = "+results.size());
				LbrUtility.printEvents(results);
			}
			return results;
	 }

	 /**
	  * Filters the events and returns a recoList based ONLY on events for which users are interested(userPreference)
	 * @param eventsList
	 * @return
	 */
	private List<EventRecommendationVO> getRecosForInterestedUsers(List<Events> eventsList){
		 logger.debug( "\n-----  executing getUsersWhoOptedForTheseEvents() ------");
		// List is ordered by locID
		 Set<Integer> set = new TreeSet();  // set of subCatIDs for all the events in the window
		 Map<Integer, List<Events>> map = new HashMap(); //
//		 List<Users> interestedUsers = new ArrayList();  // Not needed.Only for debug.  Comment it
		 List<EventRecommendationVO> recommendations = new ArrayList();
		 for (Iterator iterator = eventsList.iterator(); iterator.hasNext();) {
				Events event = (Events) iterator.next();
				int subCatID = event.getSubcategory().getSubCatId();
				set.add(subCatID);
				if(map.get(subCatID)==null){
					List ls = new ArrayList();
					ls.add(event);
					map.put(subCatID, ls);  // store all the events by subCatID
				}
				else{ //we already have an event in that subCat
					List ls = (List)map.get(subCatID);
					ls.add(event);
				}
		 }

		 //TODO ...  this needs to be heavily optimized
		 for (Iterator iterator = set.iterator(); iterator.hasNext();) { // for each subCatID find the users who have opted for it
			Integer subCatID = (Integer) iterator.next();
			   //String HQL_QUERY = "from Users u where preferences like '%"+object.intValue()+"%'";
			//String HQL_QUERY = "from Users u where preferences like ?";
			String HQL_QUERY = null;
			//if(this.userID==null)
			HQL_QUERY = "from Users u where preferences like '%,"+subCatID+",%'";
			//else
			//	HQL_QUERY = "from Users u where u.userId="+this.userID+" and preferences like '%,"+subCatID+",%'";
			//List paramsValues = new ArrayList();
			//paramsValues.add('%'+object+'%');
			GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
			//Events ev = (Events)daoEvents.read(5);
			//logger.debug("### EventName= "+ev.getName());
			List returnedUsers = daoUsers.customHibernateQueryByOrdinal(HQL_QUERY, null);
			logger.debug("\tNum interested Users (may be with errors) for subCatID["+subCatID+"]= "+returnedUsers.size());
			// run a second check  ...TODO ...find a better solution to store user preferences
			for (Iterator iterator2 = returnedUsers.iterator(); iterator2.hasNext();) {
				Users user = (Users) iterator2.next();
				if(user.getPreferences().indexOf(","+subCatID+",")==-1)
					returnedUsers.remove(user);
			}
			logger.debug("\tNum interested Users (post 2nd check) for subCatID["+subCatID+"]= "+returnedUsers.size());

			if(returnedUsers!=null && returnedUsers.size()>0){  // prepare interim recos based on users who have shown interest.. will check the vicinity later
	//			interestedUsers.addAll(returnedUsers);   // is it needed??
				List<Events> validEvents = (List)map.get(subCatID);

				for (Iterator iterator2 = validEvents.iterator(); iterator2.hasNext();) {
					Events validEvent = (Events) iterator2.next();
					Map<String, Users> usersToBeInformed = new HashMap<String, Users>(); // store userIds as key

					EventRecommendationVO recvo = new EventRecommendationVO();
					recvo.setFinalUserIDsToBeInformed(new ArrayList<String>());
					recvo.setInterminUsersToBeInformed(returnedUsers);
					recvo.setUsersToBeInformed(usersToBeInformed);  // just initialized ...will populate later
					recvo.setEvent(validEvent);
					recommendations.add(recvo);
				}
			}
			else{
				// remove the events that have no takers
				List ls = (List)map.get(subCatID);
				eventsList.removeAll(ls);
			}
		 }
		 logger.debug("\n######## Num Events(trimmed) that has interested users= "+eventsList.size());
		 LbrUtility.printEvents(eventsList);

/*		 if(interestedUsers!=null){
			logger.debug("\n####### Total interested users(duplicates allowed) = "+interestedUsers.size());
			LbrUtility.printUsers(interestedUsers);
		 }*/
		 logger.debug("\n######## Num recommendations(without vicinity check) that has interested users= "+recommendations.size());
		 LbrUtility.printRecommendations(recommendations);
		 return recommendations;
	 }

	 /**
	  * Further filters the recos based on user current location. Only if the user is inthe vicinity of the event, we need to send the reco.
	 * @param recommendationsList
	 * @return
	 */
	private List<EventRecommendationVO> getInterestedUsersInVicinityOfTheseEvents(List<EventRecommendationVO> recommendationsList){
		 int totalSMSToBeSent = 0;
		 logger.debug( "\n-----  executing getInterestedUsersInVicinityOfTheseEvents() ------");
			// check if the user is in the vicinity of the event
			for (Iterator iterator2 = recommendationsList.iterator(); iterator2.hasNext();) {
				EventRecommendationVO recoVO = (EventRecommendationVO) iterator2.next();
				Events envtObject = recoVO.getEvent();
				int eventLevel = envtObject.getLevel();
				int subcatID = envtObject.getSubcategory().getSubCatId();
				List<Users> interminUsers = recoVO.getInterminUsersToBeInformed();

				List<String> userIDs = recoVO.getFinalUserIDsToBeInformed();
				Map<String, Users> usersToBeInformed = recoVO.getUsersToBeInformed();

				//List eventsListFromMap = (List)map.get(subCatID);
				System.out.print("\n**************** Calculating for --> EventID="+envtObject.getEventId()+"\tUsersListSize="+interminUsers.size()+"\tRecoListSize="+recommendationsList.size()+"\n");
				for (Iterator iterator3 = interminUsers.iterator(); iterator3.hasNext();) {
					Users interimUser = (Users) iterator3.next();

					if(!isInterestedUserInVicinityOfTheEvent(envtObject, interimUser)){ // user is not in the vicinity of event
						//iterator3.remove();
						System.out.print("\t\t\t---- NOTOK   EventID="+envtObject.getEventId()+" userName="+interimUser.getUserName()+ " User Removed since not in vicinity"+"\tUsersListSize="+interminUsers.size()+"\n");
					}
					else{ // USER is in vicinity
						boolean userPrefLevelFilter = true;
						if(LbrConstants.PREFERENCES_LEVELS_ENABLED){ // filter by LEVEL
							Map<Integer, Integer> map = LbrUtility.mapUserPrefToLevels(interimUser);
							int userPrefLevelForSubCat = map.get(subcatID);
							if(eventLevel < userPrefLevelForSubCat)
								userPrefLevelFilter = false;
						}
						if(userPrefLevelFilter){
							userIDs.add(interimUser.getUserName());
							usersToBeInformed.put(interimUser.getUserName(), interimUser);
						}
					}
				}
				totalSMSToBeSent+= usersToBeInformed.size();
				if(userIDs.size()==0){	 // no users in the vicinity for the event
					iterator2.remove();
					logger.debug("\t-- EventID="+envtObject.getEventId()+"\tReco/event removed since no users in the vicinity for the event"+"\tRecoListSize="+recommendationsList.size());
				}
			}
			Collections.sort(recommendationsList, new EventsComparator());
			logger.debug("\n################################ Final Results ########################################################");
			LbrUtility.printRecommendations(recommendationsList);
			logger.info("\n################################ Summary of Recommendation Engine Results ######################################################");
			logger.info("######## Num Events/EventRecommendations(FINAL) that has interested users and within vicinity= "+recommendationsList.size());
			logger.info("######## Total messages to be sent to diff users= "+totalSMSToBeSent);
			logger.info("############################################################################################################\n");
			return recommendationsList;
	 }

	 private boolean isInterestedUserInVicinityOfTheEvent(Events envtObject, Users user){
		 boolean decision = false;
		 int evntLocID =  0;
		 if(user.getLocationsByCurrentLocationId()!=null){
				int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
				if(envtObject.getLocations()!=null)
					evntLocID = envtObject.getLocations().getLocationId();
				else{ // try to create a Location object artificially from the address String
					envtObject.setLocations(LbrUtility.createLocationObjectArtificially(envtObject.getAddress(), null));
					logger.debug("Executed createLocationObjectArtificially()..EventID="+envtObject.getEventId()+"\t"+envtObject.getAddress() );
				}
				System.out.print("\t\tCalculating for UserName: "+user.getUserName() +" --> userCurrentLocID="+userCurrentLocID+"\tevntLocID="+evntLocID+"\tUserVicinityPref="+user.getVicinityPolicyPreference());
				if(isUserLocationInVicinityOfEventLocation(envtObject, user)){
					decision = true;
					//System.out.print("\t\t ++++ OK, "+user.getUserName() +" is interested in EventID= "+envtObject.getEventId()+"\n");
				}
		 }
		return decision;
	 }

	 //TODO .... use Maps APIs to make the decision???
	 private boolean isUserLocationInVicinityOfEventLocation(Events envtObject, Users user){
		 VICINITY_POLICY currentVicinityPolicy = LbrConstants.currentVicinityPolicy;
		 if(user.getVicinityPolicyPreference()!=null){
			 currentVicinityPolicy = VICINITY_POLICY.getVICINITY_POLICYForOrdinal(user.getVicinityPolicyPreference());
		 }

	    boolean result = false;
		 switch (currentVicinityPolicy) {
			case LOCATION:
				result =  isUserLocationInVicinityOfEventLocationByLocation(envtObject, user);
				break;
			case PINCODE:
				result =  isUserLocationInVicinityOfEventLocationByPincode(envtObject, user);
				break;
			case MULTIPLE_PINCODES:
				result = isUserLocationInVicinityOfEventLocationByMultiplePincodes(envtObject, user);
				break;
			case CITY:
				result =  isUserLocationInVicinityOfEventLocationByCity(envtObject, user);
				break;
			case DISTRICT:
				result =  isUserLocationInVicinityOfEventLocationByDistrict(envtObject, user);
				break;
			case STATE:
				result =  isUserLocationInVicinityOfEventLocationByState(envtObject, user);
				break;
			default:
				break;
		}
		 return result;
	 }

	 private boolean isUserLocationInVicinityOfEventLocationByLocation(Events envtObject, Users user){
			int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
			int evntCityID = envtObject.getLocations().getLocationId();
		    return (userCurrentLocID==evntCityID);
	 }

	 private boolean isUserLocationInVicinityOfEventLocationByPincode(Events envtObject, Users user){
			int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
			int currUserLocationCityID = user.getLocationsByCurrentLocationId().getCity().getCityId();
			int currUserLocationPINCode = user.getLocationsByCurrentLocationId().getPincode();

			int evntPincode = envtObject.getLocations().getPincode();
			int evntOrigLocID = envtObject.getLocations().getLocationId();
			if(evntPincode==currUserLocationPINCode){
				logger.debug("\t\t\t++++++ OK. User ["+user.getUserName()+"] in the PINCODE of the event: EventOrigLocID="+evntOrigLocID+"\tEventPincode="+envtObject.getLocations().getPincode()+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tUserCurrLocID="+userCurrentLocID);
				return true;
			}
/*			if(evntOrigLocID==userCurrentLocID)
				return true;*/
			// get all locationIDs that lie in the city of the currentUserLocation
			//select * from locations where cityID IN(select cityID from city where cityName like '%Bangalore%' and stateID=(select stateID from state where stateName like '%Karnatak%'));
/*            List<Locations> listofAllLocationsInTheCityOfEvent = DaoUtilities.getAllLocationIDsForGivenPincode(evntPincode, false, false);
            for (Iterator iterator = listofAllLocationsInTheCityOfEvent.iterator(); iterator.hasNext();) {
            	Locations location = (Locations) iterator.next();
				if(location.getLocationId() == userCurrentLocID){   // user is in the city(one of the locationIDs within the city) of the event
					logger.debug("\t\t\t++++++ OK. User ["+user.getUserName()+"] in the PINCODE of the event: EventOrigLocID="+evntOrigLocID+"\tEventPincode="+envtObject.getLocations().getPincode()+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tEventCityVicinityLocID="+location.getLocationId()+"\tUserCurrLocID="+userCurrentLocID);
					return true;
				}
			}*/
		 return false;
	 }

	 private boolean isUserLocationInVicinityOfEventLocationByMultiplePincodes(Events envtObject, Users user){
		 return true;
	 }

	 /**
	  *
	 * @param envtObject
	 * @param user
	 * @return
	 */
	private boolean isUserLocationInVicinityOfEventLocationByCity(Events envtObject, Users user){
			int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
			int currUserLocationCityID = user.getLocationsByCurrentLocationId().getCity().getCityId();
			int evntCityID= 0;
			String cityName =null;
			int evntOrigLocID = 0;
			if(envtObject.getLocations()!= null){
				evntCityID = envtObject.getLocations().getCity().getCityId();
				cityName = envtObject.getLocations().getCity().getCityName();
				evntOrigLocID = envtObject.getLocations().getLocationId(); // Even if an event does not have locationId (i.e.null), this method shud work fine
			}
			if(evntCityID==currUserLocationCityID){
				logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the CITY of the event: EventOrigLocID="+evntOrigLocID+"\tEventCity="+cityName+"\tUserCurrLocID="+userCurrentLocID);
				return true;
			}
			// get all locationIDs that lie in the city of the currentUserLocation
			//select * from locations where cityID IN(select cityID from city where cityName like '%Bangalore%' and stateID=(select stateID from state where stateName like '%Karnatak%'));
/*            List<Locations> listofAllLocationsInTheCityOfEvent = DaoUtilities.getAllLocationIDsForGivenCityID(evntCityID);
            for (Iterator iterator = listofAllLocationsInTheCityOfEvent.iterator(); iterator.hasNext();) {
            	Locations location = (Locations) iterator.next();
				if(location.getLocationId() == userCurrentLocID){   // user is in the city(one of the locationIDs within the city) of the event
					logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the CITY of the event: EventOrigLocID="+evntOrigLocID+"\tEventCity="+cityName+"\tEventCityVicinityLocID="+location.getLocationId()+"\tUserCurrLocID="+userCurrentLocID);
					return true;
				}
			}*/
		 return false;
	 }

	 private boolean isUserLocationInVicinityOfEventLocationByDistrict(Events envtObject, Users user){
			int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
			int currUserLocationCityID = user.getLocationsByCurrentLocationId().getCity().getCityId();
			int currUserLocationDistrictID = user.getLocationsByCurrentLocationId().getCity().getDistrict().getDistrictId();

			int evntCityID = envtObject.getLocations().getCity().getCityId();
			int evntDistID = envtObject.getLocations().getCity().getDistrict().getDistrictId();
			int evntOrigLocID = envtObject.getLocations().getLocationId();
			if(evntDistID==currUserLocationDistrictID){
				logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the DISTRICT of the event: EventOrigLocID="+evntOrigLocID+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tEventDist="+envtObject.getLocations().getCity().getDistrict().getDistrictName()+"\tUserCurrLocID="+userCurrentLocID);
				return true;
			}
			// get all locationIDs that lie in the city of the currentUserLocation
			//select * from locations where cityID IN(select cityID from city where cityName like '%Bangalore%' and stateID=(select stateID from state where stateName like '%Karnatak%'));
 /*        List<Locations> listofAllLocationsInTheDistrictOfEvent = DaoUtilities.getAllLocationIDsForGivenDistrictID(evntDistID);
         for (Iterator iterator = listofAllLocationsInTheDistrictOfEvent.iterator(); iterator.hasNext();) {
         	Locations location = (Locations) iterator.next();
				if(location.getLocationId() == userCurrentLocID){   // user is in the city(one of the locationIDs within the city) of the event
					logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the DISTRICT of the event: EventOrigLocID="+evntOrigLocID+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tEventDist="+envtObject.getLocations().getCity().getDistrict().getDistrictName()+"\tEventDistVicinityLocID="+location.getLocationId()+"\tUserCurrLocID="+userCurrentLocID);
					return true;
				}
         }*/
		 return false;
	 }

	 private boolean isUserLocationInVicinityOfEventLocationByState(Events envtObject, Users user){
			int userCurrentLocID = user.getLocationsByCurrentLocationId().getLocationId();
			int currUserLocationStateID = user.getLocationsByCurrentLocationId().getCity().getState().getStateId();

			int evntStateID = envtObject.getLocations().getCity().getState().getStateId();
			int eventLocId = envtObject.getLocations().getLocationId();
			if(currUserLocationStateID==evntStateID){
				logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the STATE of the event: EventOrigLocID="+eventLocId+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tEventDist="+envtObject.getLocations().getCity().getDistrict().getDistrictName()+"\tUserCurrLocID="+userCurrentLocID);
				return true;
			}
			// get all locationIDs that lie in the city of the currentUserLocation
			//select * from locations where cityID IN(select cityID from city where cityName like '%Bangalore%' and stateID=(select stateID from state where stateName like '%Karnatak%'));
/*         List<Locations> listofAllLocationsInTheDistrictOfEvent = DaoUtilities.getAllLocationIDsForGivenDistrictID(evntDistID);
         for (Iterator iterator = listofAllLocationsInTheDistrictOfEvent.iterator(); iterator.hasNext();) {
         	Locations location = (Locations) iterator.next();
				if(location.getLocationId() == userCurrentLocID){   // user is in the city(one of the locationIDs within the city) of the event
					logger.debug("\t\t\t+++++ OK. User ["+user.getUserName()+"] in the DISTRICT of the event: EventOrigLocID="+evntOrigLocID+"\tEventCity="+envtObject.getLocations().getCity().getCityName()+"\tEventDist="+envtObject.getLocations().getCity().getDistrict().getDistrictName()+"\tEventDistVicinityLocID="+location.getLocationId()+"\tUserCurrLocID="+userCurrentLocID);
					return true;
				}
         }*/
		 return false;
	 }
}
