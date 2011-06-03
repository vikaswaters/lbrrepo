package com.lbr.web.struts.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;
import org.apache.log4j.Logger;
import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.SubcategoryWrapper;
import com.lbr.UserVO;
import com.lbr.core.EventRecommendationVO;
import com.lbr.core.Recommendable;
import com.lbr.core.RecommendationEngine;
import com.lbr.core.Recommendations;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.services.WebServiceCall;
import com.lbr.utils.ApplicationContextProvider;
import com.lbr.web.struts.form.UserPreferenceForm;

// USer Preferences action class
public class UserPreferenceAction extends Action {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 ActionMessages errors = new ActionMessages();
		 		UserPreferenceForm objForm = (UserPreferenceForm) form;
		 		//Long currUserID = new Long("915648496385");
			    //request.getSession().setAttribute("USERVO", currUserID);
		 		//LbrUtility.populatePINCodeToDB();

				if(request.getSession().getAttribute("USERVO")==null){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.login.required"));
					saveErrors(request, errors);
					return mapping.findForward("userLoginJsp");
				}

				Users user = ((Users)request.getSession().getAttribute("USERVO"));
				String currUserID = ((Users)request.getSession().getAttribute("USERVO")).getUserName(); // MUST BE AVAIL  to proceed further

		        String primarySelection = request.getParameter("category");
		        if(primarySelection == null)
		        	primarySelection = "1";
		        logger.debug("User selected CatID="+primarySelection);

		        String ajax = request.getParameter("ajax");
		        if(ajax!=null && ajax.equals("yes")){  // =====================  AJAX call ONLY
		        	logger.debug("**** AJAX request received for Subcategory change **********");
		        	objForm.populateSecondaryDropdown(primarySelection);
			        LbrUtility.sendAjaxResponse(objForm.toXml(), request, response);
			        //logger.debug("AJAX response XML: "+objForm.toXml());
		        	return null;
		        }				
				
				if(!user.getUserpermissions().getBasicModulePermission().booleanValue())
					return mapping.findForward("userLoginJsp");
			  	//ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();

		        if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("hideRecommend")) {
		 			return mapping.findForward("userHomeJSP");
		 		 }

	        	//Users user = DaoUtilities.getUserByID(((Users)request.getSession().getAttribute("USERVO")).getUserName());
	        	if(user.getLocationsByCurrentLocationId()!= null){
		        	//int oldCurrLocID = user.getLocationsByCurrentLocationId().getLocationId();
		        	objForm.setCurrentLocationStr(LbrUtility.printLocationHTML(user.getLocationsByCurrentLocationId()));
	        	}
	        	else{
	        		UserVO uservo = (UserVO)request.getSession().getAttribute("USERVO_IPBASED");
	        		if(uservo!=null && uservo.getUserIPLocation()!=null){
	        			objForm.setCurrentLocationStr(LbrUtility.printLocationHTML(uservo.getUserIPLocation()));
	        		}
	        		else
	        			objForm.setCurrentLocationStr("Not specified");
	        	}

		        String[] userSubCategorySelection = objForm.getSubcategory();
		        LbrUtility.initializeDateRange(objForm);

		        //=============================  conditional code =====================
		     // Delete selected userpreferences
		        if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("delete")){
		        	if(objForm.getSubcategory()!=null && objForm.getSubcategory().length>0){
			        	DaoUtilities.deleteSelectedUserPrefsForCatID(currUserID, objForm, request);
			        	objForm.setFormAction(null);
		        	}
		        	else{
		        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("Errors.UserPreferenceAction.Subcategory.Delete.Unselected"));
		        	}
		        }
		     // Show selected userpreferences
/*
		        System.out.print("User selected SubCatIDs: ");
		        for (int i = 0; i < userSubCategorySelection.length; i++) {
		        	 System.out.print(userSubCategorySelection[i]+"  ");
				}
		        logger.debug();*/

		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("save")) {
			        if(userSubCategorySelection!=null && userSubCategorySelection.length>0){ //save user preference to DB
			        	boolean[] result =DaoUtilities.saveUserPreference(currUserID, objForm, request);
			        	objForm.setFormAction(null);
	/*		        	if(result)
			        		logger.debug("User Preference updated successfully;");
			        	else
			        		logger.debug("User Preference already existed. Hence ignored;");*/
			        }
		        }
			    else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("recommend")) {
			    	checkRecomendationPrerequisites(user, errors, mapping, request);
		     		if (!errors.isEmpty())
		    		{
		    			saveErrors(request, errors);
		    			return (mapping.findForward("failure"));
		    		}
			    	// update the userVicinityPolicy if needed,  before recommendation
		        	int vicinitypolicyID = objForm.getVicinitypolicyID();
		        	Integer currentVicinityPolicy = user.getVicinityPolicyPreference();
		        	if(currentVicinityPolicy==null || (vicinitypolicyID != currentVicinityPolicy)){
			        	user.setVicinityPolicyPreference(vicinitypolicyID);
			        	DaoUtilities.updateUserByIDSmartCall(request, user);
		        	}
		        	long time1 = System.currentTimeMillis();
		        	if(LbrConstants.RECCOMENDATION_ENGINE_LIVE_ON_REQUEST){  // Run recommendation engine live on user request. .....performance impact. Shud be off in production
			        	logger.debug("======== Running the Recommendation Engine On request========");
		        		List<EventRecommendationVO> allRecosForAllUsers = RecommendationEngine.getEventsRecommendation(currUserID, objForm); // All recos  for all users
		        		List<EventRecommendationVO> allRecosForcurrentUserOnly = RecommendationEngine.filterUserSpecificRecommendation(allRecosForAllUsers, user);  // All recos  for current User only
		        		objForm.setRecommendations(allRecosForcurrentUserOnly);
						RecommendationEngine.lbrRecommendations = new Recommendations();
						RecommendationEngine.lbrRecommendations.setFinalRecommendations(allRecosForAllUsers);
						RecommendationEngine.lbrRecommendations.setGeneratedOn(Calendar.getInstance().getTime());
		        	}
		        	else{  // fetch the reccomendations for the current user, from the scheduled recommendation results

		        		List<EventRecommendationVO> allRecosForAllUsers =  RecommendationEngine.lbrRecommendations.getFinalRecommendations();
		        		List<EventRecommendationVO> allRecosForcurrentUserOnly = RecommendationEngine.filterUserSpecificRecommendation(allRecosForAllUsers, user);  // All recos  for current User only
		        		objForm.setRecommendations(allRecosForcurrentUserOnly);
		        	}
		        	long time2 = System.currentTimeMillis();
		        	logger.debug("**** Recommendation generated in "+(time2-time1)+"(ms) **********");
		        	objForm.setFormAction(null);
		        }
			    else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("location")){
			    	return mapping.findForward("location");
		        }
			    else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("saveLevels")){
			    	DaoUtilities.saveUserPreferenceLevelsONLY(user.getUserId()+"", objForm, request);
		        }		        

		        //============================================ common code =======================
		        // Load current user preferences in the form
		        //List<Category> currUserPreferences = DaoUtilities.getUserPreferencesForDisplay(new Long("915648496385"));
		        //TODO ...  IS  IT NEEDED???
		        if(objForm.getUserPreferencesWithLevels()== null){
			        List<SubcategoryWrapper> currUserPreferencesWithLevels = getUserPreferencesForDisplay(currUserID, request);
			        objForm.setUserPreferencesWithLevels(currUserPreferencesWithLevels);
		        }
		        //String[] userPrefLevels = getUserPreferenceLevelsForDisplay(currUserID, request);
		        //objForm.setSubcatLevels(userPrefLevels);
		       
		        // Add the User levels for the subcategories (Amateur ...expert)
		    //    int numUserPrefs = objForm.getUserPreferences().size();
/*		        for (int i = 0; i < numUserPrefs; i++) {
					objForm.getSubcatLevelsList().add(LbrUtility.createSubCatPrefList());
				}*/
		        
		        //LbrUtility.printSubCategoriesWithLevels(currUserPreferencesWithLevels);
		        if(user.getVicinityPolicyPreference()!=null)
		        	objForm.setVicinitypolicyID(user.getVicinityPolicyPreference());

	      	    // errors.add("login",new ActionMessage("error.login.invalid"));
	             saveErrors(request,errors);
	      	     request.setAttribute("PrefSaved", true);

	     		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			return (mapping.findForward("failure"));
	    		}
	             return mapping.findForward("userHomeJSP");  // success
	 }
	 private void checkRecomendationPrerequisites(Users user, ActionMessages errors, ActionMapping mapping, HttpServletRequest request){
	    	//check if the user has any preferences set
	    	if(user.getPreferences()==null || user.getPreferences().equals("")){
	    		errors.add("RecommendationErrors", new ActionMessage("Errors.UserPreferenceAction.Recommend.Preferences.unselected"));
	    	}
	    	// check if the current Location is set
	    	//if(user.getLocationsByCurrentLocationId()==null || (user.getLocationsByCurrentLocationId().getLocationId() == LbrConstants.UNSPECIFIED_LOCATION_ID)){
		    if(user.getLocationsByCurrentLocationId()==null){
	    		errors.add("RecommendationErrors", new ActionMessage("Errors.UserPreferenceAction.Recommend.Location.unspecified"));
	    	}
	    	saveErrors(request, errors);

	    	if(LbrConstants.PREFERENCES_LEVELS_ENABLED)
		    	if(user.getPreferencesLevels()==null || (user.getPreferencesLevels().equals(""))){
		    		List eventsMessages = new LinkedList();
		    		eventsMessages.add("To fine tune(filter) the recommendations, set the levels[1-10] of your selected preferences.");  // TODO ... i18N
		    		eventsMessages.add("Setting a higher level will narow down the number of recommendations.");
		    		request.setAttribute("messages", eventsMessages);
		    	}
	 }

	 private static List<SubcategoryWrapper> getUserPreferencesForDisplay(String userID, HttpServletRequest request){
		 String  userPrefRaw = ((Users)DaoUtilities.getUserByIDSmartCall(request, userID)).getPreferences();
		 String  userPrefLevels = ((Users)DaoUtilities.getUserByIDSmartCall(request, userID)).getPreferencesLevels();
		 List<SubcategoryWrapper> results = null;
		 int count = 0;
		 if(userPrefRaw!=null){
			 results = new ArrayList();
			 List<Integer> userPrefAsList = LbrUtility.convertUserPrefStringToList(userPrefRaw, true);
			 List<Integer> userPrefLevelsAsList  = null;
			 if(LbrConstants.PREFERENCES_LEVELS_ENABLED && (userPrefLevels==null || userPrefLevels.equals("")))
				 userPrefLevelsAsList = LbrUtility.populateUserPrefLevels(userID, userPrefAsList, request);
			 else
				 userPrefLevelsAsList = LbrUtility.convertUserPrefStringToList(userPrefLevels, true);
			 for (Iterator iterator = userPrefAsList.iterator(); iterator.hasNext();) {
				Integer subcatID = (Integer) iterator.next();
				Subcategory subcat = (Subcategory)DaoUtilities.staticCache.get("SUBCAT_"+subcatID);
				SubcategoryWrapper subcatWrap =  new SubcategoryWrapper(subcat);
				subcatWrap.setLevel(userPrefLevelsAsList.get(count++));
				results.add(subcatWrap);
			}
		 }
		 return results;
	 }
	 
	 private static String[] getUserPreferenceLevelsForDisplay(String userID, HttpServletRequest request){
		 String  userPrefRaw = ((Users)DaoUtilities.getUserByIDSmartCall(request, userID)).getPreferencesLevels();
		 String[] subCatLevels = null;
		 if(userPrefRaw!=null){
			 List<Integer> userPrefAsList = LbrUtility.convertUserPrefStringToList(userPrefRaw, true);
			 subCatLevels = new String[userPrefAsList.size()];
			 int i = 0;
			 for (Iterator iterator = userPrefAsList.iterator(); iterator.hasNext();) {
				 subCatLevels[i++] = ((Integer)iterator.next()).toString();
			}
		 }
		 return subCatLevels;
	 }

}


