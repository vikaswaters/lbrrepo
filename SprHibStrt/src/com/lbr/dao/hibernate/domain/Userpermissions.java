package com.lbr.dao.hibernate.domain;

// Generated 07-Mar-2011 11:48:20 by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Userpermissions generated by hbm2java
 */
public class Userpermissions implements java.io.Serializable {

	private int userTypeId;
	private String userTypeName;
	private Boolean basicModulePermission;
	private Boolean eventsModulePermission;
	private Boolean locationModulePermission;
	private Boolean categoryModulePermission;
	private Boolean purgeModulePermission;
	private String dbUser;
	private Set<Users> userses = new HashSet<Users>(0);

	public Userpermissions() {
	}

	public Userpermissions(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Userpermissions(int userTypeId, String userTypeName,
			Boolean basicModulePermission, Boolean eventsModulePermission,
			Boolean locationModulePermission, Boolean categoryModulePermission,
			Boolean purgeModulePermission, String dbUser, Set<Users> userses) {
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
		this.basicModulePermission = basicModulePermission;
		this.eventsModulePermission = eventsModulePermission;
		this.locationModulePermission = locationModulePermission;
		this.categoryModulePermission = categoryModulePermission;
		this.purgeModulePermission = purgeModulePermission;
		this.dbUser = dbUser;
		this.userses = userses;
	}

	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Boolean getBasicModulePermission() {
		return this.basicModulePermission;
	}

	public void setBasicModulePermission(Boolean basicModulePermission) {
		this.basicModulePermission = basicModulePermission;
	}

	public Boolean getEventsModulePermission() {
		return this.eventsModulePermission;
	}

	public void setEventsModulePermission(Boolean eventsModulePermission) {
		this.eventsModulePermission = eventsModulePermission;
	}

	public Boolean getLocationModulePermission() {
		return this.locationModulePermission;
	}

	public void setLocationModulePermission(Boolean locationModulePermission) {
		this.locationModulePermission = locationModulePermission;
	}

	public Boolean getCategoryModulePermission() {
		return this.categoryModulePermission;
	}

	public void setCategoryModulePermission(Boolean categoryModulePermission) {
		this.categoryModulePermission = categoryModulePermission;
	}

	public Boolean getPurgeModulePermission() {
		return this.purgeModulePermission;
	}

	public void setPurgeModulePermission(Boolean purgeModulePermission) {
		this.purgeModulePermission = purgeModulePermission;
	}

	public String getDbUser() {
		return this.dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
