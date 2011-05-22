package com.lbr.web.struts.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.lbr.EventLevel;
import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;

public class EventsForm extends ActionForm{
	private static final Logger logger = Logger.getLogger(EventsForm.class);

	private String eventName;
	private String startDate;
	private String endDate;
	private String contactPerson;
	private String contactPhone="91";
	private String category;
	private String subcategory;
	private String eventLevel;
	private String eventDetails;
	private String line1;
	private String line2;
	private String areaName;
	private String cityName;
	private String pincode;
	private int stateID;
	private String currentLocationStr = "";   // will contain details of the location  selected by User (thru search)
	private String userSelectedEventLocID;   // ID of the above location
	//for search
	private List<Events> searchEvents;
	private List<EventLevel> eventLevelList;
	private String userSelectedEventIDForEdit;     //
	boolean userEventEditWIP = false;  // selection of the event to be edited is WIP

	private String formAction = "";


	public EventsForm() {
		super();
		eventLevelList = new ArrayList<EventLevel>();
		eventLevelList.add(new EventLevel(1, "Very Low"));
		eventLevelList.add(new EventLevel(2, "Low"));
		eventLevelList.add(new EventLevel(3, "Medium"));
		eventLevelList.add(new EventLevel(4, "High"));
		eventLevelList.add(new EventLevel(5, "Very High"));
		eventLevel = "3";
		List<Date> dateRange = LbrUtility.getDateRange(LbrConstants.EVENTS_ADD_DEFAULT_TIME_WINDOW);
		startDate = LbrUtility.formatDateToString(dateRange.get(0));
		endDate = LbrUtility.formatDateToString(dateRange.get(1));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
		if(this.formAction!=null && !this.formAction.equalsIgnoreCase("")){
			eventName = "";
			contactPerson="";
			contactPhone="91";
			eventDetails="";
			eventLevel = "3";
			line1="";
			line2="";
			areaName="";
			cityName="";
			pincode="";
			stateID =0;
			List<Date> dateRange = LbrUtility.getDateRange(LbrConstants.EVENTS_ADD_DEFAULT_TIME_WINDOW);
			startDate = LbrUtility.formatDateToString(dateRange.get(0));
			endDate = LbrUtility.formatDateToString(dateRange.get(1));
		}
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		if(this.formAction.equals("saveEvent") || this.formAction.equals("updateEvent")  || this.formAction.equals("retrieveEvent")){    //retrieveEvent
			if (eventName==null  || eventName=="") {
				actionErrors.add(eventName, new ActionMessage("Errors.event.name.blank"));
			}
			if(contactPhone!=""){
				String temp = contactPhone.replaceAll("[-+\\s]", "");
				if(!LbrUtility.isNumber(temp))
					actionErrors.add(eventName, new ActionMessage("Errors.event.phone.number"));
				else
					contactPhone = temp;
			}

			if((Users)request.getSession().getAttribute("USERVO")!=null && ((Users)request.getSession().getAttribute("USERVO")).getUserpermissions().getEventsModulePermission().booleanValue()){
				String key = "SUBCAT_"+this.subcategory;
				Subcategory subcat = (Subcategory)DaoUtilities.staticCache.get(key);
				boolean type = subcat.getType();
				if(type == LbrConstants.SUBCATEGORY_TYPE_STATIC)  // dateRange for static events type can be 1000 yrs
					LbrUtility.validateDateRange(actionErrors, startDate, endDate, 2);
				else
					LbrUtility.validateDateRange(actionErrors, startDate, endDate, 3);
			}
			//LbrUtility.validateDateString(actionErrors, endDate);
			if (category==null  || category.equals("0")) {
				actionErrors.add(eventName, new ActionMessage("Errors.event.category.blank"));
			}
			if (stateID==0) {
				actionErrors.add(stateID+"", new ActionMessage("Errors.event.state.blank"));
			}

			// validation needed only for new Events creation
	/*		if(this.formAction.equals("saveEvent")){
				if (pincode==null  || pincode=="") {
					actionErrors.add(pincode, new ActionMessage("Errors.event.pincode.blank"));
				}
			}*/
		}
		if(this.formAction.equals("saveEvent") || this.formAction.equals("updateEvent")) {
			if (line1==null  || line1=="") {
				actionErrors.add(line1, new ActionMessage("Errors.event.line1.blank"));
			}
			if (cityName==null  || cityName=="") {
				actionErrors.add(cityName, new ActionMessage("Errors.event.cityName.blank"));
			}

		}

		if (!actionErrors.isEmpty())
			this.setFormAction("");

		return actionErrors;
	}

	public boolean isUserEventEditWIP() {
		return userEventEditWIP;
	}

	public void setUserEventEditWIP(boolean userEventEditWIP) {
		this.userEventEditWIP = userEventEditWIP;
	}

	public String getUserSelectedEventIDForEdit() {
		return userSelectedEventIDForEdit;
	}

	public void setUserSelectedEventIDForEdit(String userSelectedEventIDForEdit) {
		this.userSelectedEventIDForEdit = userSelectedEventIDForEdit;
	}

	public List<Events> getSearchEvents() {
		return searchEvents;
	}

	public void setSearchEvents(List<Events> searchEvents) {
		this.searchEvents = searchEvents;
	}

	public String getUserSelectedEventLocID() {
		return userSelectedEventLocID;
	}

	public void setUserSelectedEventLocID(String userSelectedcurrLocID) {
		this.userSelectedEventLocID = userSelectedcurrLocID;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public String getCurrentLocationStr() {
		return currentLocationStr;
	}

	public void setCurrentLocationStr(String currentLocationStr) {
		this.currentLocationStr = currentLocationStr;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public List<EventLevel> getEventLevelList() {
		return eventLevelList;
	}

	public void setEventLevelList(List<EventLevel> eventLevelList) {
		this.eventLevelList = eventLevelList;
	}

/*	private List<Category> categoryList;
	private List<Category> subcategoryList;
*/




}
