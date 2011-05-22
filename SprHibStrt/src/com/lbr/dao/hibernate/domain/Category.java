package com.lbr.dao.hibernate.domain;

// Generated 07-Mar-2011 11:48:20 by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Integer catId;
	private String catName;
	private Set<Subcategory> subcategories = new HashSet<Subcategory>(0);

	public Category() {
	}

	public Category(String catName) {
		this.catName = catName;
	}

	public Category(String catName, Set<Subcategory> subcategories) {
		this.catName = catName;
		this.subcategories = subcategories;
	}

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Set<Subcategory> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

}