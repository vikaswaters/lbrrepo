package com.lbr;

public class Vicinity {

	private int vicinityID;
	private String vicinityName;
	
	
	public Vicinity(int vicinityID, String vicinityName) {
		super();
		this.vicinityID = vicinityID;
		this.vicinityName = vicinityName;
	}
	public int getVicinityID() {
		return vicinityID;
	}
	public void setVicinityID(int vicinityID) {
		this.vicinityID = vicinityID;
	}
	public String getVicinityName() {
		return vicinityName;
	}
	public void setVicinityName(String vicinityName) {
		this.vicinityName = vicinityName;
	}
	
}
