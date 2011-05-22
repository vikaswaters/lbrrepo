package com.lbr.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.log4j.Logger;
import com.lbr.VICINITY_POLICY;
import com.lbr.Vicinity;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.State;
import com.lbr.dao.specificdao.DaoUtilities;

public class UserLocationForm extends ActionForm {
	private static final Logger logger = Logger.getLogger(UserLocationForm.class);

	private String formAction;
	private String pincode;
	private String areaName;
	private String cityName;
	private String districtName;
	private int stateID;
	private List<State> stateList;

	private List<Locations> suggestedLocations;
	private String userSelectedCurrLocation;
	private String formActionOriginatedFrom;  // the same action can originate from different modles. This var  will distinguish it.

	public UserLocationForm() {
		super();

		List<State> stateList = DaoUtilities.getAllStates();
        this.setStateList(stateList);
        this.setStateID(17);
	}


	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}


	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		if (pincode==""  && cityName=="") {
			actionErrors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Errors.UserLocationAction.Option.select.error"));
		}
		return actionErrors;
	}

	public String getFormActionOriginatedFrom() {
		return formActionOriginatedFrom;
	}


	public void setFormActionOriginatedFrom(String formActionOriginatedFrom) {
		this.formActionOriginatedFrom = formActionOriginatedFrom;
	}


	public String getUserSelectedCurrLocation() {
		return userSelectedCurrLocation;
	}

	public void setUserSelectedCurrLocation(String userSelectedCurrLocation) {
		this.userSelectedCurrLocation = userSelectedCurrLocation;
	}

	public List<Locations> getSuggestedLocations() {
		return suggestedLocations;
	}

	public void setSuggestedLocations(List<Locations> suggestedLocations) {
		this.suggestedLocations = suggestedLocations;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}


}
