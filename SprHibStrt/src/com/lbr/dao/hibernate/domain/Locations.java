package com.lbr.dao.hibernate.domain;

// Generated Feb 23, 2011 12:34:52 PM by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;

import org.hibernate.cache.CacheConcurrencyStrategy;

/**
 * Locations generated by hbm2java
 */
//@Entity
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Locations implements java.io.Serializable {

	private Integer locationId;
	private City city;
	private int pincode;
	private String locName;
	private Set<Users> usersesForCurrentLocationId = new HashSet<Users>(0);
	private Set<Events> eventses = new HashSet<Events>(0);
	private Set<Users> usersesForHomeLocationId = new HashSet<Users>(0);

	public Locations() {
	}

	public Locations(int pincode, String locName) {
		this.pincode = pincode;
		this.locName = locName;
	}

	public Locations(City city, int pincode, String locName,
			Set<Users> usersesForCurrentLocationId, Set<Events> eventses,
			Set<Users> usersesForHomeLocationId) {
		this.city = city;
		this.pincode = pincode;
		this.locName = locName;
		this.usersesForCurrentLocationId = usersesForCurrentLocationId;
		this.eventses = eventses;
		this.usersesForHomeLocationId = usersesForHomeLocationId;
	}

	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getPincode() {
		return this.pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getLocName() {
		return this.locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public Set<Users> getUsersesForCurrentLocationId() {
		return this.usersesForCurrentLocationId;
	}

	public void setUsersesForCurrentLocationId(
			Set<Users> usersesForCurrentLocationId) {
		this.usersesForCurrentLocationId = usersesForCurrentLocationId;
	}

	public Set<Events> getEventses() {
		return this.eventses;
	}

	public void setEventses(Set<Events> eventses) {
		this.eventses = eventses;
	}

	public Set<Users> getUsersesForHomeLocationId() {
		return this.usersesForHomeLocationId;
	}

	public void setUsersesForHomeLocationId(Set<Users> usersesForHomeLocationId) {
		this.usersesForHomeLocationId = usersesForHomeLocationId;
	}

}
