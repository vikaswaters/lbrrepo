package com.lbr.web.struts.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.dao.hibernate.domain.City;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.State;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.form.EventsForm;
import com.lbr.web.struts.form.UserLocationForm;
import com.lbr.web.struts.form.UserPreferenceForm;

public class EventsAction extends LbrAction {
	private static final Logger logger = Logger.getLogger(EventsAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionMessages errors = new ActionMessages();
        EventsForm objForm = (EventsForm) form;
        MessageResources messageResources = getResources(request);
        LbrAction.setThreadLocalErrosValue(errors);

        if(objForm.getFormAction()!=null && (objForm.getFormAction().equalsIgnoreCase(""))){
        	objForm.setUserEventEditWIP(false);
        	return mapping.findForward("success");
        }
        if(objForm.getFormAction()!=null && (objForm.getFormAction().equalsIgnoreCase("resetForm"))){
        	objForm.reset(mapping, request);
        	objForm.setFormAction("");
        	return mapping.findForward("success");
        }
        if(objForm.getFormAction()!=null && (objForm.getFormAction().equalsIgnoreCase("eventsCancel"))){
        	objForm.setSearchEvents(null);
        	return mapping.findForward("success");
        }
        if(objForm.getFormAction()!=null && (objForm.getFormAction().equalsIgnoreCase("clearEventLocation"))){
        	objForm.setUserSelectedEventLocID("");
        	objForm.setCurrentLocationStr("");
        	objForm.setAreaName("");
        	objForm.setCityName("");
        	objForm.setStateID(0);
        	objForm.setPincode("");
        	return mapping.findForward("success");
        }
        else if(objForm.getFormAction()!=null && (
							        		objForm.getFormAction().equalsIgnoreCase("saveEvent") ||
							        		objForm.getFormAction().equalsIgnoreCase("saveAddressCityAnyway") ||
							        		objForm.getFormAction().equalsIgnoreCase("retrieveEvent") ||
							        		objForm.getFormAction().equalsIgnoreCase("updateEvent") ||
							        		objForm.getFormAction().equalsIgnoreCase("deleteEvent")) ) {
        	boolean editSuccess =  false;
        	// needed for save, update, retrieve
        	String pincode = objForm.getPincode();
        	String areaName = objForm.getAreaName();
        	String cityName = objForm.getCityName();
        	int stateID = objForm.getStateID();
        	String catID = objForm.getCategory();
        	String subcatID = objForm.getSubcategory();
        	String contactName = objForm.getContactPerson();
        	String contactPhone = objForm.getContactPhone();
        	String startDateStr = objForm.getStartDate();
        	String endDateStr = objForm.getEndDate();
        	String evntName = objForm.getEventName();
        	String evntDetails = objForm.getEventDetails();
        	String addLine1 = objForm.getLine1();
        	String addLine2 = objForm.getLine2();
        	String userSelectedCurrLocID = objForm.getUserSelectedEventLocID();

        	Events event= null;

        	if(objForm.getFormAction().equalsIgnoreCase("saveEvent")){//
    			event = new Events();   // create a new Event ONLY for SAVE /new Event
/*    			populateEventData(event, objForm, errors);
	    		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			request.setAttribute("saveAddressCityAnyway", "true");  // so that the 'Shutup and save'  button is  displayed
	    			// Store the Event temporarily. Persist it if the user propts to save Anyway. Performance benefits, otherwise, unnecessary repeat of 'populateEventData'
	    			request.getSession().setAttribute("TEMPORARY_EVENT_TOBE _SAVED", event);
	    			return (new ActionForward(mapping.getInput()));
	    		}
	    		//errors.clear();
	        	editSuccess = DaoUtilities.createNewEvent(event);
	        	logger.debug("SAVE Event: "+editSuccess);
	        	objForm.setFormAction("");
	        	postEditOperation(editSuccess, request, errors, mapping, "Event saved successfully!!");
        		if(editSuccess)
        			return mapping.findForward("success");*/

    			return executeCommonEditSteps(event, objForm, request, errors, mapping, "Save");

        	}
        	if(objForm.getFormAction().equalsIgnoreCase("saveAddressCityAnyway")){// retreive the Event to be saved (that was stored temporarily in the previous operation)
        		if(request.getSession().getAttribute("TEMPORARY_EVENT_TOBE _SAVED")!=null){
        			event = (Events)request.getSession().getAttribute("TEMPORARY_EVENT_TOBE _SAVED");
        			editSuccess = DaoUtilities.updateEvent(event);  // it uses saveOrUpdate ...hence OK
        			objForm.setFormAction("");
        			return handleSuccessOrFailure(editSuccess, request, errors, mapping, "Save anyway", "Errors.EventsAction.save.error.security");
        		}
        	}
        	else if (objForm.getFormAction().equalsIgnoreCase("updateEvent")){
        		event = DaoUtilities.getEventByID(new Integer(objForm.getUserSelectedEventIDForEdit()));
        		return executeCommonEditSteps(event, objForm, request, errors, mapping, "Update");
        	}
        	else if (objForm.getFormAction().equalsIgnoreCase("retrieveEvent")){
        		List<Date> dateRange = LbrUtility.getDateRange(startDateStr, endDateStr);
        		if(contactPhone=="")  contactPhone="0";
        		objForm.setSearchEvents(DaoUtilities.getEventsForSearchCustom(dateRange.get(0), dateRange.get(1), userSelectedCurrLocID, evntName, new Integer(subcatID).intValue(), new Long(contactPhone)));
        		objForm.setFormAction("");
        		return mapping.findForward("retrieveEvent");
        	}
        	else if (objForm.getFormAction().equalsIgnoreCase("deleteEvent")){
        		event = DaoUtilities.getEventByID(new Integer(objForm.getUserSelectedEventIDForEdit()));
        		editSuccess = DaoUtilities.deleteEvent(event);
        		logger.debug("DELETE Event: "+editSuccess);
        		List eventsMessages = new LinkedList();
        		eventsMessages.add("Event deleted successfully");
        		request.setAttribute("eventsMessages", eventsMessages);
        	}

        	if(objForm.getFormAction().equalsIgnoreCase("deleteEvent") || objForm.getFormAction().equalsIgnoreCase("updateEvent") && editSuccess ){
        		objForm.setUserSelectedEventIDForEdit("");
        		objForm.setUserEventEditWIP(false);
        		objForm.setFormAction("");
        	}
        }
        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("Help Location")){ //TODO .. add code to save cat/subcat dropdown selectio
        	UserPreferenceForm userPrefForm =(UserPreferenceForm)request.getSession().getAttribute("UserPreferenceForm");
        	userPrefForm.setCategory(objForm.getCategory());
        	UserLocationForm locForm =(UserLocationForm)request.getSession().getAttribute("UserLocationForm");
        	locForm.setFormActionOriginatedFrom("EventsModule");
        	String[] strArr = new String[1];
        	strArr[0] = objForm.getSubcategory()+"";
        	userPrefForm.setSubcategory(strArr);
        	objForm.setFormAction("");
        	return mapping.findForward("helpLocation");
        }
        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("eventsConfirm")){
        	// populate the form with the user selected/confirmed event
        	String userSelectedEventIDForEdit = objForm.getUserSelectedEventIDForEdit();
        	List<Events> searchedEvents = objForm.getSearchEvents();
        	Events selectedEvent = null;
        	for (Iterator iterator = searchedEvents.iterator(); iterator.hasNext();) {
				Events events = (Events) iterator.next();
				if(userSelectedEventIDForEdit.equals(events.getEventId().toString())){
					selectedEvent = events;
					break;
				}
			}
        	int catID = selectedEvent.getSubcategory().getCategory().getCatId();
        	//DaoUtilities.categoryNSubCubcategoryCache.get(catID);
        	UserPreferenceForm userPrefForm =(UserPreferenceForm)request.getSession().getAttribute("UserPreferenceForm");
        	userPrefForm.populateSecondaryDropdown(catID+"");
        	objForm.setSubcategory(selectedEvent.getSubcategory().getSubCatId()+"");

        	objForm.setSubcategory(selectedEvent.getSubcategory().getSubCatId().toString());
        	objForm.setContactPerson(selectedEvent.getContactPerson());
        	objForm.setContactPhone(selectedEvent.getContactNo().toString());
        	objForm.setStartDate(LbrUtility.formatDateToString(selectedEvent.getStartDate()));
        	objForm.setEndDate(LbrUtility.formatDateToString(selectedEvent.getEndDate()));
        	objForm.setEventName(selectedEvent.getName());
        	objForm.setEventDetails(selectedEvent.getDetails());

        	//populate the loation details
        	int selectedEventLocId =0;
        	Locations selectedEventLoc =  null;
        	if(selectedEvent.getLocations()!=null){ // event Location was selected thru System help
        		selectedEventLocId = selectedEvent.getLocations().getLocationId();
        		selectedEventLoc = DaoUtilities.getLocationByID(selectedEventLocId);
            	List<String> addressComponents = LbrUtility.parseAddress(selectedEvent.getAddress());
            	if(addressComponents!=null && addressComponents.size()>0){
            		if(addressComponents.size()>0)
            			objForm.setLine1(addressComponents.get(0));
            		if(addressComponents.size()>1)
            			objForm.setLine2(addressComponents.get(1));
            	}
        	}
        	else{ // event Location was based on user raw inputs directly
        		selectedEventLoc = LbrUtility.createLocationObjectArtificially(selectedEvent.getAddress(), objForm);
        	}
        	objForm.setPincode(selectedEventLoc.getPincode()+"");
        	objForm.setAreaName(selectedEventLoc.getLocName());
        	objForm.setCityName(selectedEventLoc.getCity().getCityName());
        	objForm.setStateID(selectedEventLoc.getCity().getState().getStateId());
        	objForm.setFormAction("");
        }

		if (!errors.isEmpty())
		{
			saveErrors(request, errors);
			return (mapping.findForward("failure"));
		}
        return mapping.findForward("success");
	}

private String createAddressString(EventsForm form, Locations newloc){
	StringBuffer sb = new StringBuffer();
	sb.append(form.getLine1()+LbrConstants.LINE_SEPARATOR_WITH_SPACE_LEFT);
	sb.append(form.getLine2()+LbrConstants.LINE_SEPARATOR_WITH_SPACE_LEFT);
	if(newloc==null){ // if user has not selected the location thru the System help
		newloc = new Locations();
		newloc.setLocName(form.getAreaName());
		City city = new City();
		State state  = new State();
		state.setStateId(form.getStateID());
		state.setStateName(DaoUtilities.getStateByID(form.getStateID()).getStateName());
		city.setCityName(form.getCityName());
		city.setState(state);
		newloc.setCity(city);
		if(form.getPincode()!="")
			newloc.setPincode(new Integer(form.getPincode()));
	}
	sb.append(LbrUtility.printLocation(newloc));
	return sb.toString();
}

private void populateEventData(Events event, EventsForm objForm, ActionMessages errors, HttpServletRequest request){
	String pincode = objForm.getPincode();
	String areaName = objForm.getAreaName();
	String cityName = objForm.getCityName();
	int stateID = objForm.getStateID();
	String catID = objForm.getCategory();
	String subcatID = objForm.getSubcategory();
	String contactName = objForm.getContactPerson();
	String contactPhone = objForm.getContactPhone();
	if(contactPhone=="")  contactPhone = 0+"";
	String startDateStr = objForm.getStartDate();
	String endDateStr = objForm.getEndDate();
	String evntName = objForm.getEventName();
	String evntDetails = objForm.getEventDetails();
	String addLine1 = objForm.getLine1();
	String addLine2 = objForm.getLine2();
	String userSelectedCurrLocID = objForm.getUserSelectedEventLocID();

	Locations newloc = null;
	List<Date> dates = LbrUtility.getDateRange(startDateStr, endDateStr);
	event.setStartDate(dates.get(0));
	event.setEndDate(dates.get(1));
	event.setContactNo(new Long(contactPhone));
	event.setContactPerson(contactName);
	event.setDetails(evntDetails);
	event.setLevel(new Integer(objForm.getEventLevel()));
	event.setModifiedOn(Calendar.getInstance().getTime());
	event.setName(evntName);
	event.setOwnerId(((Users)request.getSession().getAttribute("USERVO")).getUserId());
	event.setSubcategory(DaoUtilities.getSubCategoryByID(new Integer(subcatID)));
	if(userSelectedCurrLocID!=null && userSelectedCurrLocID!=""){  // user has  selected a location thru the System locator
		newloc = DaoUtilities.getLocationByID(new Integer(userSelectedCurrLocID));
		event.setLocations(newloc);
	}

	/* IF the user inputs the location details manually, without the PIN, the query below may not return any results.i.e location ID may have to be null
	 * In that case, you may prompts the user to get the PIN, and re submit the form.  OR
	 * U may go ahead after the user confirmation, and save the event without locationID
	 * Make a check for the cityName. Either the cityName or the ZIP must match our records. Otherwise, ask the user to select a larger city.
	 * Otherwise reject the event.
	 */
	else{  //TODO ..think over how we want to save crude user inputs... try to search for location based on users Input
    	List<Locations> searchedLocations = UserLocationAction.searchLocations(pincode, cityName, areaName, stateID, request);
    	if(searchedLocations!=null && searchedLocations.size()==1){ // may prompt the user for confirmation, Yes/No/Unsure
    		newloc = searchedLocations.get(0);
    		event.setLocations(newloc);
    	}
		else if(searchedLocations!=null && searchedLocations.size()>1){ // may prompt the user for confirmation
			errors.add("multipleLocations", new ActionMessage("Errors.UserLocationAction.City.Name.Multiple"));
		}
		else if(searchedLocations==null || (searchedLocations!=null && searchedLocations.size()==0)){
			//errors.add(stateID+"", new ActionMessage("Errors.UserLocationAction.location.combination.absent"));
			errors.add(stateID+"", new ActionMessage("Errors.EventsAction.location.absent.event.reject"));
		}
	}
	event.setAddress(createAddressString(objForm, newloc));
}


private ActionForward executeCommonEditSteps(Events event, EventsForm objForm,  HttpServletRequest request, ActionMessages errors, ActionMapping mapping, String operation){
		populateEventData(event, objForm, errors, request);
		if (!errors.isEmpty()) {
			boolean cityError = hasCityError();
			if(!cityError || (cityError && objForm.getPincode()!="")){
				request.setAttribute("saveAddressCityAnyway", "true");  // so that the 'Shutup and save'  button is  displayed
				 LbrAction.getThreadLocalErrorsValue().add("LocationErrorCity", new ActionMessage("Errors.EventsAction.location.shutupNsave"));
			}
				// Store the Event temporarily. Persist it if the user propts to save Anyway. Performance benefits, otherwise, unnecessary repeat of 'populateEventData'
			request.getSession().setAttribute("TEMPORARY_EVENT_TOBE _SAVED", event);
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		boolean editSuccess = false;
		if(operation.equalsIgnoreCase("save"))
			editSuccess = DaoUtilities.createNewEvent(event);
		else if(operation.equalsIgnoreCase("update"))
			editSuccess = DaoUtilities.updateEvent(event);

    	logger.debug("SAVE Event: "+editSuccess);
    	objForm.setFormAction("");
    	return handleSuccessOrFailure(editSuccess, request, errors, mapping, operation, "Errors.EventsAction.save.error.security");

	}

private boolean hasCityError(){
	Iterator it = LbrAction.getThreadLocalErrorsValue().get();
	boolean res = false;
	for (Iterator iterator = it; it.hasNext();) {
		ActionMessage type = (ActionMessage) iterator.next();
		res = type.getKey().equals("Errors.UserLocationAction.City.absent");
		if(res) break;
	}
	return res;
}
}
