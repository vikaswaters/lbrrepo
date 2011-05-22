package com.lbr;

import java.util.List;

import com.lbr.dao.hibernate.domain.Locations;

public class LocationSearchResult {
	// String pincode, String cityName, String areaName, int stateID
	int stateID;
	int cityID;
	String userEnteredAreaName;
	String userEnteredCityName;
	String userEnteredPincode;
	
	boolean cityAvailable;
	boolean pinAvailable;
	List<Locations> returnedLocations;
	
	
	
	public LocationSearchResult(int stateID, String userEnteredAreaName,
			String userEnteredCityName, String userEnteredPincode) {
		super();
		this.stateID = stateID;
		this.userEnteredAreaName = userEnteredAreaName;
		this.userEnteredCityName = userEnteredCityName;
		this.userEnteredPincode = userEnteredPincode;
	}
	public int getStateID() {
		return stateID;
	}
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public String getUserEnteredAreaName() {
		return userEnteredAreaName;
	}
	public void setUserEnteredAreaName(String userEnteredAreaName) {
		this.userEnteredAreaName = userEnteredAreaName;
	}
	public String getUserEnteredCityName() {
		return userEnteredCityName;
	}
	public void setUserEnteredCityName(String userEnteredCityName) {
		this.userEnteredCityName = userEnteredCityName;
	}
	public String getUserEnteredPincode() {
		return userEnteredPincode;
	}
	public void setUserEnteredPincode(String userEnteredPincode) {
		this.userEnteredPincode = userEnteredPincode;
	}
	public boolean isCityAvailable() {
		return cityAvailable;
	}
	public void setCityAvailable(boolean cityAvailable) {
		this.cityAvailable = cityAvailable;
	}
	public boolean isPinAvailable() {
		return pinAvailable;
	}
	public void setPinAvailable(boolean pinAvailable) {
		this.pinAvailable = pinAvailable;
	}
	public List<Locations> getReturnedLocations() {
		return returnedLocations;
	}
	public void setReturnedLocations(List<Locations> returnedLocations) {
		this.returnedLocations = returnedLocations;
	}

	
}
