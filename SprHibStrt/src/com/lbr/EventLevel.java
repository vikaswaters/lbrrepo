package com.lbr;


public class EventLevel {
	
	private Integer levelId;
	private String levelName;
	
	
	public EventLevel(Integer levelId, String levelName) {
		super();
		this.levelId = levelId;
		this.levelName = levelName;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	

}
