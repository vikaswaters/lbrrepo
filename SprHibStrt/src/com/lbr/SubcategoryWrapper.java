package com.lbr;

import com.lbr.dao.hibernate.domain.Subcategory;

public class SubcategoryWrapper {
	
	private Subcategory userPreference;
	private int level;
	public Subcategory getUserPreference() {
		return userPreference;
	}
	public void setUserPreference(Subcategory userPreference) {
		this.userPreference = userPreference;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public SubcategoryWrapper(Subcategory userPreference, int level) {
		super();
		this.userPreference = userPreference;
		this.level = level;
	}

	public SubcategoryWrapper(Subcategory userPreference) {
		super();
		this.userPreference = userPreference;
		this.level = LbrConstants.DEFAULT_PREFERENCES_LEVEL;
	}
}
