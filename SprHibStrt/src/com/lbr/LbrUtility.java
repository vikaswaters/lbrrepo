package com.lbr;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.lbr.core.EventRecommendationVO;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.City;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.State;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.action.LbrAction;
import com.lbr.web.struts.form.EventsForm;
import com.lbr.web.struts.form.UserPreferenceForm;

public class LbrUtility {
private static final Logger logger = Logger.getLogger(LbrUtility.class);

	  public static ArrayList<EventLevel> createSubCatPrefList(){
		 ArrayList<EventLevel> eventLevelList = new ArrayList();
			eventLevelList = new ArrayList<EventLevel>();
			eventLevelList.add(new EventLevel(1, "Very Low"));
			eventLevelList.add(new EventLevel(2, "Low"));
			eventLevelList.add(new EventLevel(3, "Medium"));
			eventLevelList.add(new EventLevel(4, "High"));
			eventLevelList.add(new EventLevel(5, "Very High"));		
			return eventLevelList;
	}

/*		
		<option value="1">Very Low</option>
		<option value="2">Low</option>
		<option value="3" selected="selected">Medium</option>
		<option value="4">High</option>
		<option value="5">Very High</option></select>
*/
	  
/*	  public static String createPrefLeveldropdownString(String[] userprefLevels){
			 StringBuffer sb = new StringBuffer();
			 sb.append(" <select name=/""subcatLevels/"" onchange=/""/""> ");
		}*/
	  public static String createStringArrayToString(String[] selectedUserPref){
	    StringBuffer subCatID = new StringBuffer();
		if(selectedUserPref.length > 0) 
			subCatID.append(",");
		for (int i = 0; i < selectedUserPref.length; i++) {
			subCatID.append(selectedUserPref[i]+",");
		}
		return subCatID.toString();
	  }
	  
	  public static String[] createUserPrefSubCatIDsToStringArray(List<SubcategoryWrapper> currUserPreferencesWithLevels){
		  int count = 0 ;
		  String[] strArr =  new String[currUserPreferencesWithLevels.size()];
		  for (Iterator iterator = currUserPreferencesWithLevels.iterator(); iterator.hasNext();) {
			SubcategoryWrapper subcategoryWrapper = (SubcategoryWrapper) iterator.next();
			strArr[count++] = subcategoryWrapper.getUserPreference().getSubCatId().toString();
		  }
		  return strArr;
	  }

	  public static String[] createUserPrefLevelsToStringArray(List<SubcategoryWrapper> currUserPreferencesWithLevels){
		  int count = 0 ;
		  String[] strArr =  new String[currUserPreferencesWithLevels.size()];
		  for (Iterator iterator = currUserPreferencesWithLevels.iterator(); iterator.hasNext();) {
			SubcategoryWrapper subcategoryWrapper = (SubcategoryWrapper) iterator.next();
			strArr[count++] = subcategoryWrapper.getLevel()+"";
		  }
		  return strArr;
	  }
	  
	public static String getResourceBundleString(String key){
		ResourceBundle resource = ResourceBundle.getBundle("MessageResources");
		String val=resource.getString(key);
		return val;
	}

	public static void sendAjaxResponse(String lbrXml, HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().write(lbrXml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//logger.debug(lbrXml);
	}

	public static String generaPasswordXX(String rawString) {
		byte[] password = { 00 };
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(rawString.getBytes());
			password = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		logger.debug("MD5 str: "+password.toString());
		return password.toString();
	}
	public static String generaPassword(String pass) {
	MessageDigest m  = null;
	try {
		m = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	byte[] data = pass.getBytes();
	m.update(data,0,data.length);
	BigInteger i = new BigInteger(1,m.digest());
	logger.debug("MD5 str: "+String.format("%1$032X", i));
	return String.format("%1$032X", i);
	}

	 			/*********************************** Date related Utility methods *********************/

	 public static List<Date> getDateRange(int numDays){
		    Calendar c1 = Calendar.getInstance();
		    c1.add(Calendar.DATE, LbrConstants.WINDOW_OFFSET_FROM_TODAY);
		    c1.set(Calendar.HOUR_OF_DAY, 10);
		    c1.set(Calendar.MINUTE, 0);
		    Date dt1 = c1.getTime();

		    if(numDays!=0)
		    	c1.add(Calendar.DATE, numDays);
		    else
		    	c1.add(Calendar.DATE, LbrConstants.VALID_EVENTS_TIME_WINDOW);
		    c1.set(Calendar.HOUR_OF_DAY, 20);
		    c1.set(Calendar.MINUTE, 0);
		    Date dt2 = c1.getTime();

		    // Fri Jan 28 10:00:00 IST 2011,Fri Feb 04 20:00:00 IST 2011
		    if(LbrConstants.LBR_TESTING){  // if u want specific daterange  for  testing  purpose
			    String windowStartDate = "2011-01-28 10:00:00";
			    String windowEndDate = "2011-02-04 20:00:00";
				try {
					dt1 = LbrConstants.timestampFormatter.parse(windowStartDate);
					dt2 = LbrConstants.timestampFormatter.parse(windowEndDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    }

		    List<Date> ls = new ArrayList<Date>();
		    ls.add(dt1);
		    ls.add(dt2);
		    return ls;
	 }

	 public static String formatDateToString(Date date){
		 return LbrConstants.timestampFormatter.format(date);
	 }

	 public static List<Date> getDateRange(String windowStartDate, String windowEndDate){

		    Calendar c1 = Calendar.getInstance();
		    Date dt1 = c1.getTime();
		    Date dt2 = c1.getTime();
			try {
				dt1 = LbrConstants.timestampFormatter.parse(windowStartDate);
				dt2 = LbrConstants.timestampFormatter.parse(windowEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		    List<Date> ls = new ArrayList<Date>();
		    ls.add(dt1);
		    ls.add(dt2);
		    return ls;
	 }

	 public static List<Date> initializeDateRange(UserPreferenceForm objForm){
	 		List<Date> dates =  null;
	 	    String windowStartDate = objForm.getStartDate(); //"2011-01-28 10:00:00";
	 	    String windowEndDate = objForm.getEndDate();   // "2011-02-04 20:00:00";
	 	    if(objForm.getStartDate()!=null && objForm.getEndDate()!=null && objForm.getStartDate()!="" && objForm.getEndDate()!=""){
	 	    	dates = LbrUtility.getDateRange(windowStartDate, windowEndDate);
	 	    }
	 		 if(dates==null){
	 			 dates = LbrUtility.getDateRange(0);
		 		 objForm.setStartDate(LbrConstants.timestampFormatter.format((Date)dates.get(0)));
		 		 objForm.setEndDate(LbrConstants.timestampFormatter.format((Date)dates.get(1)));
	 		 }
	 		 return dates;
	 }

	 public static Date validateDate(String dateString){
		    Calendar c1 = Calendar.getInstance();
		    Date dt1 = c1.getTime();
			try {
				dt1 = LbrConstants.timestampFormatter.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			return dt1;
	 }

	 public static void validateDateString(ActionErrors actionErrors, String dateString, String variable){
		if (dateString==null  || dateString=="") {
			actionErrors.add(variable, new ActionMessage("Errors.event.startDate.blank"));
		}else{
			Date dt = LbrUtility.validateDate(dateString);
			if(dt==null)
				actionErrors.add(variable, new ActionMessage("Errors.event."+variable+".format"));
		}
	 }

	 /**
	  * Level1 : Only format of the string
	  * Level2 : Level1 +  before /after
	  * Level3 :  Level2 + date range
	 * @param actionErrors
	 * @param startdateString
	 * @param endDateString
	 */
	public static void validateDateRange(ActionErrors actionErrors, String startdateString, String endDateString, int level){
		 ActionErrors actionErrorsLocal = new ActionErrors();
		 List<Date> dates= null;
		 // Level 1
		 if(level>0){
			LbrUtility.validateDateString(actionErrorsLocal, startdateString, "startDate");
			LbrUtility.validateDateString(actionErrorsLocal, endDateString, "endDate");
		 }
		if (actionErrorsLocal.isEmpty()){
			// Level 2
			if(level>1){
				Date now = Calendar.getInstance().getTime();
				dates= LbrUtility.getDateRange(startdateString, endDateString);
				if(dates.get(0).after(dates.get(1)))  // startDate shud be less than endDate
					actionErrors.add(startdateString, new ActionMessage("Errors.event.daterange.early"));
				if(now.after(dates.get(1)))  // endDate shud be after now
					actionErrors.add(startdateString, new ActionMessage("Errors.event.endDate.now"));
			}
			// Level 3
			if(level>2){
			int dateRange = (int)((dates.get(1).getTime() - dates.get(0).getTime())/(24*60*60*1000));
			if(dateRange> LbrConstants.ALLOWED_DATE_RANGE)
				actionErrors.add(startdateString, new ActionMessage("Errors.event.dateRange.exceeded", LbrConstants.ALLOWED_DATE_RANGE+""));
			}

		}
		else{
			for (Iterator iterator = actionErrorsLocal.get(); iterator.hasNext();) {
				ActionMessage message = (ActionMessage) iterator.next();
				actionErrors.add(message.getKey(), message);
			}
		}
	 }
	 /**
	  * If an event locatin was not  selected by the help location link, it is possible that it does not have a locationID.
	  * In that situation, we need to create a location base don the address string.
	 * @param address
	 * @return
	 */
	public static Locations createLocationObjectArtificially(String address, EventsForm form){
			Locations newloc = null;
			City city = null;
			State state= null;
        	List<String> addressComponents = LbrUtility.parseAddress(address);
        	String line1 = "";
        	String line2 = "";
        	String areaName = "";
        	String cityName = "";
        	String pincode = "";
        	String stateName = "";

        	if(addressComponents!=null && addressComponents.size()>0){
	        	if(addressComponents.get(0)!=null)	line1= addressComponents.get(0);
	        	if(form!=null) form.setLine1(line1);
	        	if(addressComponents.get(1)!=null)	line2= addressComponents.get(1);
	        	if(form!=null) form.setLine2(line2);
	        	if(addressComponents.get(2)!=null)	areaName= addressComponents.get(2);
	        	if(addressComponents.get(3)!=null)	cityName= addressComponents.get(3).trim();
	        	if(addressComponents.get(4)!=null)	pincode= addressComponents.get(4);
	        	if(addressComponents.get(5)!=null)	stateName= addressComponents.get(5);
        	}
        	state = DaoUtilities.getStateByName(addressComponents.get(5));  // state is mandatory
        	if(addressComponents.size()>2){
        		newloc = new Locations();
        		newloc.setLocName(areaName);  //this areaName is user provided. Not verified.
        		List<City> cities = DaoUtilities.getCityIDsForGivenCityName(cityName, state.getStateId());
        		if(cities!=null && cities.size()==1){  // probably exact match
        			city = cities.get(0);
        			newloc.setCity(city);
        		}
        		else if(cities!=null && cities.size()>1){  //  more than 1 city returned(e.g Bangalore, Bangalore North, etc) .. need to check the exact name
        			for (Iterator iterator = cities.iterator(); iterator.hasNext();) {
						City city2 = (City) iterator.next();
						if(city2.getCityName().equalsIgnoreCase(cityName)){
							city = city2;
							break;
						}
					}
        		}
        		else{ // null .. city name does not exist in System

        			if(!pincode.equals("0")){   // see if u can still find the city if it has the PINCode
        				city = DaoUtilities.getCityForGivenPincode(pincode);
        			}
        			else{ // u r fucked
	        			city = new City();  // create a new one based on address string
	        			city.setCityName(cityName);
	        			city.setCityId(0);
        			}
        		}
        		city.setState(state);
        		newloc.setCity(city);
        		newloc.setPincode(new Integer(addressComponents.get(4).trim()));
        		newloc.setLocationId(0);
        	}
        	return newloc;
	 }

	 public static List<String> parseAddress(String address){
		 List<String> addressComponents = new ArrayList<String>();
		 StringTokenizer strtkn = new StringTokenizer(address, "|");
		 while(strtkn.hasMoreElements()){
			 String str = strtkn.nextToken();
			 addressComponents.add(str);
		 }
		 return addressComponents;
	 }

	 public static void printEvents(List results){
		 System.out.print("\tEventIDs= ");
		 for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				Events event = (Events) iterator.next();
				System.out.print(" "+event.getEventId());
		}
		 //logger.debug("");
	 }

	 public static void printUsers(List results){
		 System.out.print("\tUserIDs= ");
		 for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				Users user = (Users) iterator.next();
				System.out.print("\n\t "+user.getMobileNo()+" "+user.getUserName());
		}
		 //ogger.debug("");
	 }

	 public static void printRecommendations(List recovos){
		 for (Iterator iterator = recovos.iterator(); iterator.hasNext();) {
			 EventRecommendationVO reco = (EventRecommendationVO) iterator.next();
			 if(reco.getUsersToBeInformed().size()>0)
				 logger.debug(reco.toString());
		}
	 }

	 public static String printLocationHTML(Locations loc){
		 StringBuffer sb = new StringBuffer();
		 if(loc!=null){
			 String pincode = "Not specified";
			 if(loc.getPincode()!=0)
				 pincode = loc.getPincode()+"";
			 sb.append(loc.getLocName()+"<br/>"+loc.getCity().getCityName()+"<br/>"+pincode+"<br/>"+loc.getCity().getState().getStateName());
		 }

		 return sb.toString();
	 }

	 public static String printLocation(Locations loc){
		 StringBuffer sb = new StringBuffer();
		 if(loc!=null)
			 sb.append(loc.getLocName()+LbrConstants.LINE_SEPARATOR_WITH_SPACE_LEFT+loc.getCity().getCityName()+LbrConstants.LINE_SEPARATOR_NO_SPACE+loc.getPincode()+LbrConstants.LINE_SEPARATOR_NO_SPACE+loc.getCity().getState().getStateName());
		 return sb.toString();
	 }



	 public static String sortUserPreference(String origStrRaw){
		 StringBuffer newStr = new StringBuffer(",");
		 List<Integer> arr = convertUserPrefStringToList(origStrRaw, true);
		 Collections.sort(arr);
		 for (Iterator iterator = arr.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			newStr.append(integer+",");
		}
		 return newStr.toString();
	 }

	 public static List<Integer> convertUserPrefStringToList(String origStrRaw, boolean cleanup){
		 List<Integer> arr = new ArrayList<Integer>();
		 if(origStrRaw!=null && !origStrRaw.equals("")){
			 String origStrCleaned = origStrRaw;
			 if(cleanup)
				 origStrCleaned = cleanUserPrefences(origStrRaw);
			 
			 StringTokenizer strtkn = new StringTokenizer(origStrCleaned);
			 while(strtkn.hasMoreElements()){
				 String str = strtkn.nextToken(",");
				 arr.add(new Integer(str));
			 }
		 }
		return arr;
	 }
	 
	 public static List<Integer> populateUserPrefLevels(String userId, List<Integer>  userPrefs, HttpServletRequest request){
		 List<Integer> userPrefLevels = new ArrayList<Integer>();
		 for (Iterator iterator = userPrefs.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			userPrefLevels.add(LbrConstants.DEFAULT_PREFERENCES_LEVEL);
		}
		String strArrUserPrefLevels = LbrUtility.convertUserPrefListToString(userPrefLevels);
		Users user = DaoUtilities.getUserByIDSmartCall(request, userId);
		user.setPreferencesLevels(strArrUserPrefLevels);
		//daoUsers.update(user);
		DaoUtilities.updateUserByIDSmartCall(request, user);
    	 return userPrefLevels;
	 }

	 public static String convertUserPrefListToString(List<Integer> userPrefList){
		StringBuffer userPrefStr = new StringBuffer(",");
		for (Iterator iterator = userPrefList.iterator(); iterator.hasNext();) {
			Integer userPref = (Integer) iterator.next();
			userPrefStr.append(userPref);
			userPrefStr.append(",");

		}
		return userPrefStr.toString();
	 }

	 public static Map<Integer, Integer> mapUserPrefToLevels(Users user){	 
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer> userPrefs = LbrUtility.convertUserPrefStringToList(user.getPreferences(), true);
		List<Integer> userPrefsLevels = LbrUtility.convertUserPrefStringToList(user.getPreferencesLevels(), true);
		for (int i = 0; i < userPrefs.size(); i++) {
			try{
				map.put(userPrefs.get(i), userPrefsLevels.get(i));
			}catch (Exception e) {
				map.put(userPrefs.get(i), LbrConstants.DEFAULT_PREFERENCES_LEVEL);
			}
		}
		return map;
	 }
	 
	 /**
	  * Cleans user preference of the format ",2,3,7,8,9,"  by removing the firat and last comma
	 * @param userPref
	 * @return
	 */
	public static String cleanUserPrefences(String userPref){
		if(userPref != null && userPref.length() >2)
			return  userPref.substring(1, userPref.length()-1);
		else 
			return null;
	 }

	 public static boolean isAllTrue(boolean [] results){
		 for (int i = 0; i < results.length; i++) {
			boolean val = results[i];
			if (!val)
				return false;
		}
		return true;
	 }

	 public static boolean isAnyTrue(boolean [] results){
		 for (int i = 0; i < results.length; i++) {
			boolean val = results[i];
			if (val)
				return true;
		}
		return false;
	 }

	 public static boolean isNumber(String str){
		try{
			Float.isNaN(new Float(str));
		}catch (Exception e) {
			return false;
		}
		return true;
	 }
	 public static void printCategories(List currUserPreferences){
		 logger.debug("Printing Categories...");
		for (Iterator iterator = currUserPreferences.iterator(); iterator.hasNext();) {
			Category cat = (Category) iterator.next();
			String catName = cat.getCatName();
			String subcatName = "";
			Set subcats = cat.getSubcategories();
			logger.debug("-- CatID:"+cat.getCatId()+"\tCatName: "+catName);
			for (Iterator iterator2 = subcats.iterator(); iterator2.hasNext();) {
				Subcategory subcat = (Subcategory) iterator2.next();
				subcatName = subcat.getSubCatName();
				logger.debug("\tSubCatID:"+subcat.getSubCatId()+"\tSubCatName: "+subcatName);
			}
		}
 }

	 public static void printSubCategories(List<Subcategory> currUserPreferences){
		 if(currUserPreferences!=null){
		 logger.debug("Printing SubCategories...");
			for (Iterator iterator2 = currUserPreferences.iterator(); iterator2.hasNext();) {
				Subcategory subcat = (Subcategory) iterator2.next();
				String subcatName = subcat.getSubCatName();
				logger.debug("\tSubCatID:"+subcat.getSubCatId()+"\tSubCatName: "+subcatName);
			}
		 }
	 }

	 public static void printSubCategoriesWithLevels(List<SubcategoryWrapper> currUserPreferences){
		 if(currUserPreferences!=null){
		 logger.debug("Printing SubCategories...");
			for (Iterator iterator2 = currUserPreferences.iterator(); iterator2.hasNext();) {
				SubcategoryWrapper subcatwrap = (SubcategoryWrapper)iterator2.next();
				Subcategory subcat = (Subcategory)subcatwrap.getUserPreference();
				String subcatName = subcat.getSubCatName();
				logger.debug("\tSubCatID:"+subcat.getSubCatId()+"\tSubCatName: "+subcatName+"\tLevel: "+subcatwrap.getLevel());
			}
		 }
	 }
	 
	 public static String printEvent(Events event){
		 StringBuffer sb = new StringBuffer();
		 sb.append("EVENT ID= ");
		 sb.append(event.getEventId());
		 sb.append("\nSubCat: ");
		 sb.append(event.getSubcategory().getSubCatName()+" ["+event.getSubcategory().getSubCatId()+"]");
		 sb.append("\nName: ");
		 sb.append(event.getName());
		 sb.append("\nStartDate: ");
		 sb.append(event.getStartDate());
		 sb.append("\nEndDate: ");
		 sb.append(event.getEndDate());
		 sb.append("\nLocation: ");
		 sb.append(event.getLocations().getLocName()+" ["+event.getLocations().getLocationId()+"] PIN:"+event.getLocations().getPincode()+"  Dist: "+event.getLocations().getCity().getDistrict().getDistrictName()+" City: "+event.getLocations().getCity().getCityName()+" State: "+event.getLocations().getCity().getState().getStateName());
		 sb.append("\nContact No.: ");
		 sb.append(event.getContactNo());
		 sb.append("\nContact name: ");
		 sb.append(event.getContactPerson());
		 sb.append("\nAddress: ");
		 sb.append(event.getAddress());
		 return sb.toString();
	 }

	 public static String printEventForHTML(Events event){
		 boolean fullDebugON = LbrConstants.LBR_DEBUG && (LbrAction.getThreadLocalUserValue().getUserpermissions().getUserTypeId() == LbrConstants.ADMIN_USERTYPE_ID);
		 StringBuffer sb = new StringBuffer();
		 if(fullDebugON){
			 sb.append("<b>EVENT ID= </b>");
			 sb.append(event.getEventId()+"<br/>");
		 }
		 sb.append("<b>Category: </b>");
		 sb.append(event.getSubcategory().getCategory().getCatName());
		 if(fullDebugON)
			 sb.append(" ["+event.getSubcategory().getCategory().getCatId()+"]");
		 sb.append("<b> SubCategory: </b>");
		 sb.append(event.getSubcategory().getSubCatName());
		 if(fullDebugON)
			 sb.append(" ["+event.getSubcategory().getSubCatId()+"]");
		 sb.append("<br/><b>Name: </b>");
		 sb.append(event.getName());
		 sb.append("<br/><b>StartDate: </b>");
		 sb.append(LbrUtility.formatDateToString(event.getStartDate()));
		 sb.append("<br/><b>EndDate: </b>");
		 sb.append(LbrUtility.formatDateToString(event.getEndDate()));
		 sb.append("<br/><b>Level: </b>");
		 sb.append(event.getLevel());		 
		 if(fullDebugON){
			 sb.append("<br/><b>Location: </b>");
			 String pincode = "Not specified";
			 if(event.getLocations().getPincode()!=0)
				 pincode = event.getLocations().getPincode()+"";
			 sb.append(event.getLocations().getLocName());
			 sb.append(" [LocID:"+event.getLocations().getLocationId()+"]");
			 sb.append(" PIN:"+pincode+"  Dist: "+event.getLocations().getCity().getDistrict().getDistrictName()+" City: "+event.getLocations().getCity().getCityName()+" State: "+event.getLocations().getCity().getState().getStateName());
		 }
		 sb.append("<br/><b>Contact No.: </b>");
		 sb.append(event.getContactNo());
		 sb.append("<b>  Contact name: </b>");
		 sb.append(event.getContactPerson());
		 sb.append("<br/><b>Address: </b>");
		 //String temp1 = event.getAddress().replaceAll("[|\\s]", ", ");
		 //String temp2 = temp1.getAddress().replaceAll("[, ,\\s]", ", ");
		 sb.append(event.getAddress());
		 sb.append("<br/><b>Details: </b>");
		 sb.append(event.getDetails());
		 return sb.toString();
	 }

	 /**
	  * Run the 5 commands in ORDER.
	  * Run 1, then load the 'pincodes_of_India_Database3.sql'  in the DB  before running command 2
	  * Then run the following 3 SQL commands first and copy paste the results in the respective worksheets of 'pincodes_of_India_Database.xls' , before running command 2
		  * select DISTINCT state from zipdb order by state into outfile 'c:/states.txt';
	 	  * select DISTINCT dist as DISTRICT, state as STATE from zipdb order by state,dist into outfile 'c:/districts.txt';
		  * select DISTINCT city as CITY, dist as DISTRICT, state as STATE from zipdb order by state,dist,city into outfile 'c:/cities.txt';
	  * Run 2, then populate the DB with 'states.sql' , before running command 3
	  * Run 3, then populate the DB with 'districts.sql' , before running command 4
	  * Run 4, then populate the DB with 'cities.sql' , before running command 5
	  * Run 5, then populate the DB with 'locations.sql'
	  * The entire geographic data is populated. Do the sanity with the results in seeddata.sql
	 *
	 */
	public static void populatePINCodeToDB(){
		 try {
			// 1. Convert master XLS into SQL .... after the SQl is generated, load the SQL data in the DB before
			 //ExcelUtilities.readXLSAndCreateMasterSQL(new FileInputStream("C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.xls"), "C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.sql");
			 // 2. SQL to populate STATE
			//ExcelUtilities.createStateSQL(new FileInputStream("C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.xls"), "C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\states.sql");

			 // 3. SQL to populate DISTRICTS
			//ExcelUtilities.createDistrictSQL(new FileInputStream("C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.xls"), "C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\districts.sql");

				// 4. SQL to populate CITY
			//ExcelUtilities.createCitySQL(new FileInputStream("C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.xls"), "C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\cities.sql");

			// 5. SQL to populate LOCATIONS
			//ExcelUtilities.createLocationsSQL(new FileInputStream("C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\pincodes_of_India_Database.xls"), "C:\\Users\\vikas\\fund\\per\\locationBasedRecos\\locations.sql");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
