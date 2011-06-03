package com.lbr;

import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Users;

public class UserVO {

	Users user;
	Locations userIPLocation;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Locations getUserIPLocation() {
		return userIPLocation;
	}
	public void setUserIPLocation(Locations userIPLocation) {
		this.userIPLocation = userIPLocation;
	}
	
	
}
