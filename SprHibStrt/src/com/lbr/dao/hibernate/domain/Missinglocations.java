package com.lbr.dao.hibernate.domain;

// Generated 11-Mar-2011 20:54:26 by Hibernate Tools 3.4.0.Beta1

import java.util.Date;


/**
 * Missinglocations generated by hbm2java
 */
public class Missinglocations implements java.io.Serializable {

	private MissinglocationsId id;
	private Date addedOn;

	public Missinglocations() {
	}

	public Missinglocations(MissinglocationsId id, Date addedOn) {
		this.id = id;
		this.addedOn = addedOn;
	}

	public MissinglocationsId getId() {
		return this.id;
	}

	public void setId(MissinglocationsId id) {
		this.id = id;
	}

	public Date getAddedOn() {
		return this.addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

}
