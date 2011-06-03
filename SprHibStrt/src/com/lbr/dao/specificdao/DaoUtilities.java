package com.lbr.dao.specificdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;

import com.lbr.LbrUtility;
import com.lbr.SubcategoryWrapper;
import com.lbr.SubcategoryWrapperComparator;
import com.lbr.dao.genericdao.GenericDao;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.City;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Missinglocations;
import com.lbr.dao.hibernate.domain.MissinglocationsId;
import com.lbr.dao.hibernate.domain.State;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.hibernate.domain.Userpermissions;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.utils.ApplicationContextProvider;
import com.lbr.web.struts.action.LbrAction;
import com.lbr.web.struts.form.UserPreferenceForm;

public class DaoUtilities {
	private static final Logger logger = Logger.getLogger(DaoUtilities.class);
	/**
	 * Local generic cache to store the Category and SubCategory against their IDs. It may store other data as well.
	 */
	public static Map<String, Object> staticCache= null;
	/**
	 * Master cache to store List of subcatgories for a given categoryID
	 */
	public static Map<Integer, List<Subcategory>> categoryNSubCubcategoryCache= null;
	static{
		staticCache = new HashMap<String, Object>();
		List cats= DaoUtilities.getAllCategory();
		if(cats!=null){
			logger.info("****** Category list retrieved from DB ******");
			for (Iterator iterator = cats.iterator(); iterator.hasNext();) {
				Category cat = (Category) iterator.next();
				String key = "CAT_"+cat.getCatId();
				staticCache.put(key, cat);
			}
		}else{
			logger.error("***** Category list is NULL ....may be DB issue ******");
		}

		List subcats= DaoUtilities.getAllSubCategory();
		if(subcats!=null){
			logger.info("***** SubCategory list retrieved from DB *****");
			for (Iterator iterator = subcats.iterator(); iterator.hasNext();) {
				Subcategory subcat = (Subcategory) iterator.next();
				String key = "SUBCAT_"+subcat.getSubCatId();
				staticCache.put(key, subcat);
			}
		}else{
			logger.error("***** SubCategory list is NULL ....may be DB issue ******");
		}
	}

	 public static List executeCustomHQLQuery(String domainBeanId, String HQL_QUERY, List paramsNames, List paramsValues){
		//GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
		 GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean(domainBeanId);
		//Events ev = (Events)daoEvents.read(5);
		//logger.debug("### EventName= "+ev.getName());
		List results = daoEvents.customHibernateQuery(HQL_QUERY, paramsNames, paramsValues);
		return results;
	 }
	 public static List executeCustomSQLQuery(String domainBeanId, String SQL_QUERY,  String tableNameAlias, Class cls){
			//GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
			 GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean(domainBeanId);
			//Events ev = (Events)daoEvents.read(5);
			//logger.debug("### EventName= "+ev.getName());
			List results = daoEvents.customSQLQuery(SQL_QUERY,  tableNameAlias, cls);
			return results;
		 }

	// ================================  User Specific DB operations =============================

	 public static boolean[] saveUserPreference(String userId, UserPreferenceForm objForm, HttpServletRequest request){
			Users user = DaoUtilities.getUserByIDSmartCall(request, userId);
			String[] selectedUserPref = objForm.getSubcategory();
			List<SubcategoryWrapper> currUserPreferencesWithLevels = objForm.getUserPreferencesWithLevels();

			StringBuffer existingUserPref = new StringBuffer();
			if(user.getPreferences()!=null)
				existingUserPref.append(user.getPreferences());
			else
				existingUserPref.append(",");
			logger.debug("Old User Preference= "+existingUserPref);
			boolean [] results = new boolean[selectedUserPref.length];
			for (int i = 0; i < selectedUserPref.length; i++) {
				String subCatID = ","+selectedUserPref[i]+",";
				if(existingUserPref.indexOf(subCatID)== -1){ // user selected NEW SubCategory as preference
					String newsubCatID = selectedUserPref[i];
					Subcategory subcat = (Subcategory)DaoUtilities.staticCache.get("SUBCAT_"+newsubCatID);
					SubcategoryWrapper subcatWrap =  new SubcategoryWrapper(subcat);  // with default LEVEL
					if(currUserPreferencesWithLevels == null){
						currUserPreferencesWithLevels =  new ArrayList<SubcategoryWrapper>();
						objForm.setUserPreferencesWithLevels(currUserPreferencesWithLevels);
					}
					currUserPreferencesWithLevels.add(subcatWrap);
				}
				else{
					logger.debug("SAVE Preferences: User Preference "+subCatID+ " already exist. Ignoring SAVE!");
				}
			}
			saveUserPreferenceToDBAndUpdateForm(userId, objForm, existingUserPref.toString(), request);
			return results;
		 }

	 public static void saveUserPreferenceToDBAndUpdateForm(String userId, UserPreferenceForm objForm, String currentUserPref, HttpServletRequest request){
		    List<SubcategoryWrapper> currUserPreferencesWithLevels = objForm.getUserPreferencesWithLevels();
		    Users user = DaoUtilities.getUserByIDSmartCall(request, userId);
			Collections.sort(currUserPreferencesWithLevels, new SubcategoryWrapperComparator());
			String[] strArrUserPrefSubCatIDs = LbrUtility.createUserPrefSubCatIDsToStringArray(currUserPreferencesWithLevels);
			String[] strArrUserPrefLevels = LbrUtility.createUserPrefLevelsToStringArray(currUserPreferencesWithLevels);
			String newUserPrefSubCatIDs = LbrUtility.createStringArrayToString(strArrUserPrefSubCatIDs);
			String newUserPrefLevels = LbrUtility.createStringArrayToString(strArrUserPrefLevels);

			if(!currentUserPref.equals(newUserPrefSubCatIDs)){
				user.setPreferences(newUserPrefSubCatIDs);
				user.setPreferencesLevels(newUserPrefLevels);
				//daoUsers.update(user);
				DaoUtilities.updateUserByIDSmartCall(request, user);
/*				if (LbrUtility.isAllTrue(results))
					logger.debug("ALL selected preferences saved successfully!!\t New UserPref= "+newUserPrefSubCatIDs);
				else
					logger.debug("SOME of the selected preferences saved successfully. Others pre-existed \t New UserPref= "+newUserPrefSubCatIDs);
*/			}
			else
				logger.debug("NO selected subcategories saved. Already existed. Ignored!! \t UserPref= "+currentUserPref);
			objForm.setUserPreferencesWithLevels(currUserPreferencesWithLevels);
	 }

	 public static void saveUserPreferenceLevelsONLY(String userId, UserPreferenceForm objForm, HttpServletRequest request){
		 	String[] selectedUserPrefLevel = objForm.getSubcatLevels();
		 	String currentUserPrefLevel = LbrUtility.createStringArrayToString(selectedUserPrefLevel);
			GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
			Users user = DaoUtilities.getUserByIDSmartCall(request, userId);
			if(!user.getPreferencesLevels().equals(currentUserPrefLevel)){
				//String newUserPref = LbrUtility.sortUserPreference(currentUserPref.toString());
				user.setPreferencesLevels(currentUserPrefLevel);
				DaoUtilities.updateUserByIDSmartCall(request, user);
				logger.debug("User pref LEVELS saved");
				//now  update the objForm.getUserPreferencesWithLevels
				List<SubcategoryWrapper> currUserPreferencesWithLevels = objForm.getUserPreferencesWithLevels();
				int count = 0;
				for (Iterator iterator = currUserPreferencesWithLevels.iterator(); iterator.hasNext();) {
					SubcategoryWrapper subcategoryWrapper = (SubcategoryWrapper) iterator.next();
					subcategoryWrapper.setLevel(new Integer(selectedUserPrefLevel[count++]));
				}
			}
			else
				logger.debug("User pref LEVELS NOT modified ... old and new are same!!");
		 }

	 public static boolean createNewUser(Users user){
		   GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
		   try{
			   Object obj = daoUsers.create(user);
		   }catch (org.hibernate.exception.SQLGrammarException e) {
			   logger.error("Error creating User: "+e);
			return false;
		   }
		   catch (Exception e) {  //org.hibernate.exception.SQLGrammarException
			   logger.error("Error creating User: "+e);
			   return false;
		   }
		   return true;
	 }


	 public static boolean checkUserLogin(String userID, String passwd){
		   Users user = getUserByID(userID);
		   if(user!=null){
			   return (user.getPassword().equalsIgnoreCase(passwd) ? true : false);
		   }
		   return false;
	 }

	 private static Users getUserByID(String userID){
		   GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
		   Users user = (Users)daoUsers.read(userID);
		   return user;
	 }

	 public static Userpermissions getModuleUserTypeByID(int userTypeID){
		   GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("userpermissionsDao");
		   Userpermissions userType = (Userpermissions)daoUsers.read(userTypeID);
		   return userType;
	 }


/*	 private static void updateUserByID(Users user){
		 GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
		 daoUsers.update(user);
	 }*/


	 //TODO ....  hinernate JOIN syntax issues....get it right.
/*	 public static List getUserPreferencesForDisplayXXX(String userID){
		 String  userPref = DaoUtilities.getUserPreferences(userID);
		 List results = null;
		 String  userPrefCleaned = userPref.substring(1, userPref.length()-1);
		 String HQL_QUERY = "from category cat where cat.subcategories.subCatId IN ("+userPrefCleaned+")";
		 //String HQL_QUERY = "from category c inner join SubCategory s on c.catID=s.catID where s.subCatID IN ("+userPrefCleaned+")";
	    *//** Getting the Session Factory and session *//*
	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	   // Criteria crit = session.createCriteria(Category.class);
	    //crit.setProjection(Projections.distinct(Projections.property("subCatId")));
	    *//** Starting the Transaction *//*
	    Transaction tx = session.beginTransaction();
	    try{
		    Query query = session.createQuery(HQL_QUERY);
		    results = query.list();
		    logger.debug("======= NumResults= "+results.size());
		    tx.commit(); // flush occurs
	    }catch (Exception e) {
				e.printStackTrace();

		    }
	    finally{
		    *//** Closing Session *//*
		    session.close();
		    // TODO ..need to get this JOIN stuff right
		    LbrUtility.printCategories(results);
		    removeFuckinDuplicates(results, userPrefCleaned);
	    }
		 return results;
	 }*/

public static void removeFuckinDuplicates(List results, String  userPrefCleaned){
	StringBuffer sb = new StringBuffer("");
  for (Iterator iterator = results.iterator(); iterator.hasNext();) {
		Category cat = (Category) iterator.next();
		Set subcats = cat.getSubcategories();
		String str = cat.getCatId()+",";
		if(sb.toString().indexOf(str)==-1){
			sb.append(str);
			logger.debug("\tAdding to Sbuffer");
		}
		else{
			iterator.remove();
			logger.debug("\tRemoving....");
		}
		for (Iterator iterator2 = subcats.iterator(); iterator2.hasNext();) {
			Subcategory subcat = (Subcategory) iterator2.next();
			Integer subcatName = subcat.getSubCatId();
			logger.debug(str);
			if(userPrefCleaned.indexOf(subcatName.toString())== -1){
				iterator2.remove();
				logger.debug("\tRemoving ..filtering by subCatname...."+cat.getCatName()+"\t"+subcatName);
			}
			else{

			}
		}
	}
}


public static Users getUserByIDSmartCall(HttpServletRequest request, String userID){
	if(request.getSession().getAttribute("USERVO")!=null){
		return (Users)request.getSession().getAttribute("USERVO");
	}
	else{
		Users user = DaoUtilities.getUserByID(userID);
	    return user;
	}
}

public static boolean updateUserByIDSmartCall(HttpServletRequest request, Users user){
		 GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("usersDao");
		 boolean result = daoUsers.update(user);
		 if(result)
			 request.getSession().setAttribute("USERVO", user);
	    return result;
}

// ================================  Category Specific DB operations =============================

	 public static List getAllCategory(){
		   String HQL_QUERY = "from Category e";
		   return executeCustomHQLQuery("categoryDao", HQL_QUERY, null, null);
	 }

	 public static List getAllSubCategory(){
		   String HQL_QUERY = "from Subcategory e";
		   return executeCustomHQLQuery("subcategoryDao", HQL_QUERY, null, null);
	 }
	 public static Subcategory getSubCategoryByID(int subcatID){
		   GenericDao subcatDao = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("subcategoryDao");
		   Subcategory subcat = (Subcategory)subcatDao.read(subcatID);
		   return subcat;
	 }

	 public static List<Subcategory> getSubCategoryByName(String subcatName){
		   GenericDao subcatDao = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("subcategoryDao");
		   String HQL_QUERY = "from Subcategory e where e.subCatName="+subcatName;
		   return executeCustomHQLQuery("subcategoryDao", HQL_QUERY, null, null);
	 }

	 public static List<Subcategory> getAllSubCategoryForCatID(int catID){
		   String HQL_QUERY = "from Subcategory e where e.category="+catID;
		   return executeCustomHQLQuery("subcategoryDao", HQL_QUERY, null, null);
	 }

	 public static List getAllStates(){
		   String HQL_QUERY = "from State e";
		   return executeCustomHQLQuery("stateDao", HQL_QUERY, null, null);
	 }

	 public static State getStateByID(int stateID){
		   GenericDao daoState = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("stateDao");
		   State loc = (State)daoState.read(new Integer(stateID));
		   return loc;
	 }
	 public static State getStateByName(String stateName){
		   String HQL_QUERY = "from State e where e.stateName='"+stateName+"'";
		   List<State> states = executeCustomHQLQuery("stateDao", HQL_QUERY, null, null);
		   if(states!=null && states.size()>0)
			   return states.get(0);
		   else
			   return null;
	 }

// ================================  Locations Specific DB operations =============================

	 public static Locations getLocationByID(int locID){
		   GenericDao daoLocation = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("locationsDao");
		   Locations loc = (Locations)daoLocation.read(new Integer(locID));
		   return loc;
	 }

	 public static List<Locations> getAllLocationIDsForGivenCityID(int cityID){
		   String HQL_QUERY = "from Locations e where e.city="+cityID;
		   return executeCustomHQLQuery("locationsDao", HQL_QUERY, null, null);
	 }


	public static City getCityForGivenPincode(String pincode){
	 if(pincode!=null && pincode!=""){
		   String HQL_QUERY = "from Locations c where c.pincode="+pincode;
		   List<Locations> locations =  executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
		   if(locations!=null && locations.size()>0){
			   Locations loc = locations.get(0);
			   return loc.getCity();
		   }
	 }
	 return null;
 }

/*	 public static List<Locations> getAllLocationIDsForGivenPincodeAndOthersXXX(String pincode, String cityName, String areaName, int stateID){
		 if(pincode!=null && pincode!=""){
			 boolean preWildcard = false;
			 boolean postWildcard = false;
			 if(pincode.charAt(0) =='*')  preWildcard = true;
			 if(pincode.charAt(pincode.length()-1) =='*')  postWildcard = true;
			 String purePin = pincode.replace('*', ' ').trim();
			 return getAllLocationIDsForGivenPincodeAndOthers(new Integer(purePin), cityName, areaName, stateID, preWildcard, postWildcard);

		 }
		 else return null;
	 }*/
	 //public static List<Locations> getAllLocationIDsForGivenPincodeAndOthers( int pincode, String cityName, String areaName, int stateID, boolean useLikePre, boolean useLikePost){
	 public static List<Locations> getAllLocationIDsForGivenParameters(String pincode, String cityName, String areaName, int stateID){
		 List<Locations> locations;
		 boolean isPINProvided = false;
		 boolean isCityNameProvided = false;
		 boolean isAreaNameProvided = false;
		 String HQL_QUERY = "select * from locations loc where ";

		 if(pincode!=null && pincode!="" && !pincode.equals("-")){ // if PIN is provided
			 isPINProvided = true;
			 HQL_QUERY+="loc.pincode";
			 boolean useLikePre = false;
			 boolean useLikePost = false;
			 if(pincode.charAt(0) =='*')  useLikePre = true;
			 if(pincode.charAt(pincode.length()-1) =='*')  useLikePost = true;
			 String purePin = pincode.replace('*', ' ').trim();
			 //return getAllLocationIDsForGivenPincodeAndOthers(new Integer(purePin), cityName, areaName, stateID, preWildcard, postWildcard);
			  // if(areaName!=null && areaName!="" && useLikePre || useLikePost){
			 if(useLikePre || useLikePost){
				   if(useLikePre && useLikePost)
					   HQL_QUERY+=" like '%"+purePin+"%'";
				   else if(useLikePre && !useLikePost)
					   HQL_QUERY+=" like '%"+purePin+"'";
				   else if(!useLikePre && useLikePost)
					   HQL_QUERY+=" like '"+purePin+"%'";
			   }
			   else
				   HQL_QUERY+="="+purePin;
		 }
	     if(cityName!=null && cityName!=""){ //  if applicable, add city to the query also
			   isCityNameProvided = true;
			   List<City> cityIDs = getCityIDsForGivenCityName(cityName, stateID);
			   if(cityIDs!=null && cityIDs.size() > 0){ // city exists (1+)
				   String cityIDsStr = "";
				   cityIDsStr = convertCityIDsToString(cityIDs);
				   if(isPINProvided)
					   HQL_QUERY+=" and loc.cityID IN("+cityIDsStr+")";
				   else
					   HQL_QUERY+=" loc.cityID IN("+cityIDsStr+")";
			   }
			   else if(!isPINProvided){ //if city does not exist in our DB
				   //createNewMissingLocation(pincode, cityName, areaName, stateID);
				   LbrAction.getThreadLocalErrorsValue().add("LocationErrorCity", new ActionMessage("Errors.UserLocationAction.City.absent"));
					   return null;
			   }
	     }
		   // if applicable, add areaName to the query also
		 if(areaName!=null && areaName!=""){
			   isAreaNameProvided = true;
			   HQL_QUERY+= " and MATCH(loc.locName) AGAINST('+"+areaName+"' IN BOOLEAN MODE)";
		 }

		   locations = executeCustomSQLQuery("locationsDao", HQL_QUERY, "loc", Locations.class);
		   if(locations==null || locations.size() ==0){ // Nothing returned ... check the reason
			   // need to log/store this potential new location, so that we can add the locations(PIN is must) later in our locations DB.
			   if(LbrUtility.isNumber(pincode))
			   //if(pincode!=null && pincode!= ""&& pincode.indexOf("*")==-1)
				   createNewMissingLocation(pincode, cityName, areaName, stateID);

			   if(!isAreaNameProvided && !isCityNameProvided){  // PIN only
				   LbrAction.getThreadLocalErrorsValue().add("LocationErrorPIN", new ActionMessage("Errors.UserLocationAction.PIN.absent"));
			   }
			   else
				   LbrAction.getThreadLocalErrorsValue().add("LocationErrorCombination", new ActionMessage("Errors.UserLocationAction.location.areaCity.combination.absent"));
		   }
		   else{

		   }
		   return locations;
	 }

/*	 public static List<Locations> getAllLocationIDsForGivenAreaAndCityName(String areaName, String cityName, int stateID){
		   String SQL_QUERY = "";
		   List<Locations> locations;
		   List<City> cityIDs = getCityIDsForGivenCityName(cityName, stateID);
		   if(cityIDs!=null && cityIDs.size() > 0){ // city exists (1+)
			   String cityIDsStr = "";
			   if(areaName!=null && areaName!="")
				   SQL_QUERY = "select * from locations loc where MATCH(loc.locName) AGAINST('"+areaName+"' IN BOOLEAN MODE) and ";
			   else
				   SQL_QUERY = "select * from locations loc where ";
			   //String HQL_QUERY = "from Locations e where e.locName like '%"+areaName+"%' ";  // COSTLY
			   cityIDsStr = convertCityIDsToString(cityIDs);
			   SQL_QUERY+=" loc.cityID IN("+cityIDsStr+")";
			   locations = executeCustomSQLQuery("locationsDao", SQL_QUERY, "loc", Locations.class);
			   if(locations!=null && locations.size()== 0){
				   LbrAction.getThreadLocalValue().add("LocationError", new ActionMessage("Errors.UserLocationAction.location.areaCity.combination.absent"));
			   }
			   return locations;
		   }
		   else{
			   LbrAction.getThreadLocalValue().add("LocationError", new ActionMessage("Errors.UserLocationAction.City.absent"));
			   return null;
		   }
	 }*/
/*	 public static List<Locations> getAllLocationIDsForGivenAreaAndCityNameXXXXXX(String areaName, String cityName, int stateID){
		   List<City> cityIDs = getCityIDsForGivenCityName(cityName, stateID);
		   if(cityIDs!=null && cityIDs.size() > 0){
			   String cityIDsStr = "";
			   String HQL_QUERY = "from locations e where e.locName like '%"+areaName+"%' ";
			   if(stateID>0){
				   cityIDsStr = convertCityIDsToString(cityIDs);
				   HQL_QUERY+="and e.city IN("+cityIDsStr+")";
			   }
			   return executeCustomHQLQuery("locationsDao", HQL_QUERY, null, null);
		   }
		   else
			   return null;
	 }*/
	 public static List<City> getCityIDsForGivenCityName(String cityName, int stateID){
			 List<City> listCities;
			 //First make a search with EXACT city name, and see if we  have a unique city
			 String HQL_QUERY = "from City c where c.cityName = '"+cityName+"' and c.state="+stateID;
			 listCities = executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
			 // perfect match. For size>0 we cannot filter now. We have to use filtering mechanism using locName in getAllLocationIDsForGivenAreaAndCityName
			 if(listCities!=null && listCities.size()==1){
				 return listCities;
			 }
			 if(listCities!=null && listCities.size()==0){ // broaden the search ...use FULL TEXT search (for partial cityname that ends with *)
				 	String SQL_QUERY_FULLTEXT = "select * from city c where MATCH(c.cityName) AGAINST('+"+cityName+"' IN BOOLEAN MODE) and c.stateID="+stateID;
				   //return executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
				 	listCities =  executeCustomSQLQuery("cityDao", SQL_QUERY_FULLTEXT,  "c", City.class);
				 	logger.info("getCityIDsForGivenCityName : executing "+SQL_QUERY_FULLTEXT);
			 }
			 if(listCities!=null && listCities.size()==0){ // broaden the search ...use FULL TEXT search (for partial cityname that does NOT end with *)
				 	String SQL_QUERY_FULLTEXT = "select * from city c where MATCH(c.cityName) AGAINST('+"+cityName+"*' IN BOOLEAN MODE) and c.stateID="+stateID;
				   //return executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
				 	listCities =  executeCustomSQLQuery("cityDao", SQL_QUERY_FULLTEXT,  "c", City.class);
				 	logger.info("getCityIDsForGivenCityName : executing "+SQL_QUERY_FULLTEXT);
			 }
			 if(listCities!=null && listCities.size()==0){  //if TEXT search is not supported/or user has provided....use LIKE ...but at performance cost
				 	String SQL_QUERY_FULLTEXT = "select * from city c where c.cityName LIKE '"+cityName+"%' and c.stateID="+stateID;
				 	listCities =  executeCustomSQLQuery("cityDao", SQL_QUERY_FULLTEXT,  "c", City.class);
				 	logger.info("getCityIDsForGivenCityName : executing "+SQL_QUERY_FULLTEXT);
			 }
			 return listCities;
	    	//    results = session.createSQLQuery("select * from city c where MATCH(c.cityName) AGAINST('+Bangalore' IN BOOLEAN MODE)").addEntity("c", City.class).list();
	 }

/*	 public static List<City> getCityIDsForGivenCityNameVVVVVV(String cityName, int stateID){
		 if(stateID!=0){
		   String HQL_QUERY = "from City c where c.cityName like '%"+cityName+"%' and c.state="+stateID;
		   return executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
		 }
		 else
			 return null;
	 }*/
	 public static List<City> getCityIDsForGivenDistrictID(int distID){
		   String HQL_QUERY = "from City c where c.district="+distID;
		   return executeCustomHQLQuery("cityDao", HQL_QUERY, null, null);
	 }
	 public static List<Locations> getAllLocationIDsForGivenDistrictID(int distID){
		   List<City> listCityIDs = getCityIDsForGivenDistrictID(distID);
		   String cityIDsStr = convertCityIDsToString(listCityIDs);
		   String HQL_QUERY = "from Locations e where e.city IN("+cityIDsStr+")";
		   return executeCustomHQLQuery("locationsDao", HQL_QUERY, null, null);
	 }


	 public static List<Integer> deleteSelectedUserPrefsForCatID(String userID, UserPreferenceForm objForm, HttpServletRequest request){
		   Users user = getUserByIDSmartCall(request,userID);
		   String currentUserPref = user.getPreferences();
		   List<SubcategoryWrapper> currUserPreferencesWithLevels = objForm.getUserPreferencesWithLevels();
		   String[] selectedUserPrefForDeletion = objForm.getSubcategory();
		   int count = 0;
		   for (int i = 0; i < selectedUserPrefForDeletion.length; i++) {
			   String userPrefForDeletion = selectedUserPrefForDeletion[i];
			   if(currentUserPref.indexOf(","+userPrefForDeletion+",") != -1) {
				   boolean delete = false;
				   SubcategoryWrapper subcategoryWrapper =  null;
				   for (Iterator iterator = currUserPreferencesWithLevels.iterator(); iterator.hasNext();) {
						subcategoryWrapper = (SubcategoryWrapper) iterator.next();
						if(subcategoryWrapper.getUserPreference().getSubCatId().equals(new Integer(userPrefForDeletion))){
							delete = true;
							break;
						}
				   }
				   if(delete==true){
					   currUserPreferencesWithLevels.remove(subcategoryWrapper);
					   delete = false;
				   }
			   }
			   else{
				   logger.debug("Subcategory "+userPrefForDeletion+" is currently not in user preference. Ignored for deletion");
			   }
		   }
		   List<Integer> currUserPrefAsList = LbrUtility.convertUserPrefStringToList(currentUserPref, true);
		   saveUserPreferenceToDBAndUpdateForm(userID, objForm, currentUserPref, request);
		   return currUserPrefAsList;
	 }

	 public static boolean createNewMissingLocation(String pincode, String cityName, String areaName, int stateID){
		   GenericDao daoUsers = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("missinglocationsDao");
		   Missinglocations missingLoc = new Missinglocations();
		   int pincodeInt =  0;
		   if(pincode=="")
			   pincode="0";
		   pincodeInt = new Integer(pincode);
		   MissinglocationsId  missingLocID = new MissinglocationsId(pincodeInt, areaName, cityName);
		   missingLoc.setId(missingLocID);
		   missingLoc.setAddedOn(Calendar.getInstance().getTime());
		   try{
			   Object obj = daoUsers.create(missingLoc);
		   }catch (org.hibernate.exception.SQLGrammarException e) {
			   logger.error("Error creating MissingLocation: "+e);
			return false;
		   }
		   catch (Exception e) {  //org.hibernate.exception.SQLGrammarException
			   logger.error("Error creating MissingLocation: "+e);
			   return false;
		   }
		   return true;
	 }


// ===============  Events specific DB  operations
 public static Events getEventByID(int eventID){
	 GenericDao<Events, Integer> daoEvents = (GenericDao<Events, Integer>)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
	 try{
		 return daoEvents.read(eventID);
	 }catch (Exception e) {
		 logger.error("Error reading Event: "+e);
		return null;
	}
 }
 public static boolean createNewEvent(Events event){
	 GenericDao<Events, Integer> daoEvents = (GenericDao<Events, Integer>)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
	 try{
		 Object obj = daoEvents.create(event);
	 }catch (Exception e) {
		 logger.error("Error creating Event: "+e);
		return false;
	}
	 return true;
 }
 public static boolean updateEvent(Events event){
	 GenericDao<Events, Integer> daoEvents = (GenericDao<Events, Integer>)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
	 try{
		 return daoEvents.update(event);
	 }catch (Exception e) {
		 logger.error("Error updating Event: "+e);
		return false;
	}
 }

 public static boolean deleteEvent(Events event){
	 GenericDao<Events, Integer> daoEvents = (GenericDao<Events, Integer>)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
	 try{
		 return daoEvents.delete(event);
	 }catch (Exception e) {
		 logger.error("Error deleting Event: "+e);
		return false;
	}
 }

 public static List<Events> findEvents(Events tempEvent){
	   String HQL_QUERY = "from Events c where c.name like '%"+tempEvent.getName()+"%' and c.contactNo="+tempEvent.getContactNo()+" and c.";
	   return executeCustomHQLQuery("eventsDao", HQL_QUERY, null, null);
	}

	public static List<Events> getEventsForSearchCustom(Date startDate, Date endDate, String userSelectedCurrLocID, String evntName, int subCatID, long contactNo){

		StringBuffer buffer = new StringBuffer("from Events e where (((:windowEndDate < e.startDate) OR (e.startDate between :windowStartDate and :windowEndDate) OR (e.endDate between :windowStartDate and :windowEndDate) or (e.startDate< :windowStartDate and e.endDate >:windowEndDate))");
	    if(evntName!=null && !evntName.equals("")){
	    	buffer.append(" and e.name like '%"+evntName+"%'");
	    }
	    if(userSelectedCurrLocID!=null && userSelectedCurrLocID!=""){
	    	buffer.append(" and e.locations="+userSelectedCurrLocID);
	    }
	    if(subCatID!=0){
	    	Subcategory subcat = DaoUtilities.getSubCategoryByID(subCatID);
	    	buffer.append(" and e.subcategory="+subcat.getSubCatId());
	    }
	    if(contactNo!=0L){
	    	buffer.append(" and e.contactNo like '%"+contactNo+"%'");
	    }

	    buffer.append(")");
		String HQL_QUERY = buffer.toString();
    	Timestamp ts1 = new Timestamp(startDate.getTime());
    	Timestamp ts2 = new Timestamp(endDate.getTime());
    	List paramsValues = new ArrayList();
    	List paramsNames = new ArrayList();
    	paramsValues.add(ts1);
    	paramsValues.add(ts2);

    	paramsNames.add("windowStartDate");
    	paramsNames.add("windowEndDate");

		GenericDao daoEvents = (GenericDao)ApplicationContextProvider.getApplicationContext().getBean("eventsDao");
		List results = daoEvents.customHibernateQuery(HQL_QUERY, paramsNames, paramsValues);
		if(results!=null){
			logger.debug("\n######## Total Events(custome search) in dateRange["+startDate+","+endDate+"] = "+results.size());
			LbrUtility.printEvents(results);
		}
		return results;
 }

public static String convertCityIDsToString(List<City> listCityIDs){
	StringBuffer sb =null;
	if(listCityIDs!=null){
		sb = new StringBuffer();
		for (Iterator iterator = listCityIDs.iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			sb.append(city.getCityId());
			if(iterator.hasNext())
				sb.append(",");
		}
	}
	else
		return null;
	return sb.toString();
}
}
