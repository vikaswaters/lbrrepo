package com.lbr.web.struts.action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.LocationSearchResult;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.form.EventsForm;
import com.lbr.web.struts.form.UserLocationForm;
import com.lbr.web.struts.form.UserPreferenceForm;

public class UserLocationAction extends LbrAction {
	private static final Logger logger = Logger.getLogger(UserLocationAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionMessages errors = new ActionMessages();
        UserLocationForm objForm = (UserLocationForm) form;
        LbrAction.setThreadLocalErrosValue(errors);

        if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("locationSearch")){
        	String pincode = objForm.getPincode();
        	String areaName = objForm.getAreaName();
        	String cityName = objForm.getCityName();
        	String districtName = objForm.getDistrictName();
        	int stateID = objForm.getStateID();
        	objForm.setSuggestedLocations(null);
        	List<Locations> searchedLocations = UserLocationAction.searchLocations(pincode, cityName, areaName, stateID, request);
        	if(searchedLocations!=null)
        		objForm.setSuggestedLocations(searchedLocations);
    		else{
    			//errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("Errors.UserLocationAction.location.combination.absent"));
    		}

/*        	else
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("Errors.UserLocationAction.City.Name.error"));
*/
        	objForm.setFormAction(null);
        }
        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("locationPreferenceConfirm")){ //Non-Events module
        	if(request.getSession().getAttribute("USERVO")!=null && (objForm.getFormActionOriginatedFrom()!=null && objForm.getFormActionOriginatedFrom().equals("UserPreferenceModule"))){
	        	Users user = DaoUtilities.getUserByIDSmartCall(request,((Users)request.getSession().getAttribute("USERVO")).getUserName());
	        	//int oldCurrLocID = 0;
/*	        	if(user.getLocationsByCurrentLocationId()!=null){
	        		oldCurrLocID = user.getLocationsByCurrentLocationId().getLocationId();
	        	}*/

	        	int newCurrLocID = new Integer(objForm.getUserSelectedCurrLocation());
	        	Locations newLoc = DaoUtilities.getLocationByID(newCurrLocID);
	        	user.setLocationsByCurrentLocationId(newLoc);
	        	DaoUtilities.updateUserByIDSmartCall(request, user);
	        	user = DaoUtilities.getUserByIDSmartCall(request, ((Users)request.getSession().getAttribute("USERVO")).getUserName());
	        	logger.debug(LbrUtility.printLocationHTML(user.getLocationsByCurrentLocationId()));
	        	objForm.setFormAction(null);
	        	((UserPreferenceForm)request.getSession().getAttribute("UserPreferenceForm")).setCurrentLocationStr(LbrUtility.printLocationHTML(user.getLocationsByCurrentLocationId()));
	        	// previous recomendations are nullified since the user currLoc has changed
	        	((UserPreferenceForm)request.getSession().getAttribute("UserPreferenceForm")).setRecommendations(null);
	        	return mapping.findForward("userHome");   // back to the originating page
        	}
        	// Request coming from events module .Just need to return the location ID for the new Event to be  entered
        	else {
        		EventsForm eventForm =(EventsForm)request.getSession().getAttribute("EventsForm");
        		int newCurrLocID = new Integer(objForm.getUserSelectedCurrLocation());
        		Locations newLoc = DaoUtilities.getLocationByID(newCurrLocID);
        		eventForm.setUserSelectedEventLocID(objForm.getUserSelectedCurrLocation());
        		eventForm.setCurrentLocationStr(LbrUtility.printLocationHTML(newLoc));
        		eventForm.setAreaName(newLoc.getLocName());
        		eventForm.setCityName(newLoc.getCity().getCityName());
        		eventForm.setStateID(newLoc.getCity().getState().getStateId());
        		eventForm.setPincode(newLoc.getPincode()+"");
        		objForm.setFormAction(null);
        		return mapping.findForward("helpSearch");
        	}
        }
        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("locationPreferenceCancel")){
        	String fwd = "";
        	if(objForm.getFormActionOriginatedFrom()!= null && objForm.getFormActionOriginatedFrom().equalsIgnoreCase("EventsModule"))
        		fwd = "eventsHome";
        	else
        		fwd = "userHomeJSP";

        	objForm.setFormAction(null);
        	objForm.setFormActionOriginatedFrom(null);
        	return mapping.findForward(fwd);
        }
        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("eventLocationSelectionCancel")){
        	objForm.setFormAction(null);
	        return mapping.findForward("helpSearch");
        }

        if (!errors.isEmpty())
		{
			saveErrors(request, errors);
			return (mapping.findForward("failure"));
		}


        return mapping.findForward("success");
	}

	/**
	 * Atleast either of PINcode and City name is mandatory
	 * @param pincode
	 * @param areaName
	 * @param cityName
	 * @param stateID
	 * @return
	 */
	public static List<Locations>  searchLocations(String pincode, String cityName, String areaName, int stateID, HttpServletRequest request){
		LocationSearchResult locSearchResult = new LocationSearchResult(stateID, areaName, cityName, pincode);

		List<Locations> allLocations =  null;

		allLocations = DaoUtilities.getAllLocationIDsForGivenParameters(pincode, cityName, areaName, stateID);
/*    	if(pincode!=""){ // allLocationsForPincode
    		allLocations = DaoUtilities.getAllLocationIDsForGivenPincodeAndOthers(pincode, cityName, areaName, stateID); // should return 1+ locations
    		if(allLocations==null || allLocations.size()==0){
    			if(cityName!=""){ // see if u can locate with the cityName and areaName
    				allLocations = DaoUtilities.getAllLocationIDsForGivenAreaAndCityName(areaName, cityName, stateID);
    				if(allLocations!=null && allLocations.size()>0){ // found some locations based on areaName, city , state
    					List messages = new LinkedList();
    					messages.add(LbrUtility.getResourceBundleString("Errors.UserLocationAction.PIN.absent.AreaNCity.located"));
    					request.setAttribute("eventsMessages", messages);
    				}
    			}
    		}
    		//objForm.setSuggestedLocations(allLocationsForPincode);
    	}
    	else if(cityName!=""){ // allLocationsForGIvenAreaAndCityName
    		allLocations = DaoUtilities.getAllLocationIDsForGivenAreaAndCityName(areaName, cityName, stateID);
    		//if(allLocations==null || allLocations.size()==0)
    			//LbrAction.getThreadLocalValue().add("LocationError", new ActionMessage("Errors.UserLocationAction.City.absent"));

    	}*/
    	return allLocations;
	}

}
