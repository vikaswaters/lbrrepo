package com.lbr;

import java.text.SimpleDateFormat;

public class LbrConstants {
	
	public static final VICINITY_POLICY currentVicinityPolicy = VICINITY_POLICY.DISTRICT;
	public static final int UNSPECIFIED_LOCATION_ID= 0;
	//public static final String USER_ID="1";
	public static final String LINE_SEPARATOR_WITH_SPACE_BOTH=" | ";
	public static final String LINE_SEPARATOR_WITH_SPACE_RIGHT="| ";
	public static final String LINE_SEPARATOR_WITH_SPACE_LEFT=" |";
	public static final String LINE_SEPARATOR_NO_SPACE="|";	
	
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final int ALLOWED_DATE_RANGE= 365;
	
	public static final int VALID_EVENTS_TIME_WINDOW = 30;
	public static final int EVENTS_ADD_DEFAULT_TIME_WINDOW = 2;
	public static final int WINDOW_OFFSET_FROM_TODAY = 1;
	
	public static final boolean SUBCATEGORY_TYPE_STATIC = false;  // 0: static , 1 : dynamic
	public static final int RECCOMENDATION_THREAD_SLEEP_TIME_MINUTES= 5;
	public static final boolean PREFERENCES_LEVELS_ENABLED = true;
	public static final int DEFAULT_PREFERENCES_LEVEL = 3;
	
	/*********************************  Debug/testing  params *********************/
	public static final boolean LBR_TESTING = false;
	public static final boolean LBR_DEBUG = true;
	public static final int ADMIN_USERTYPE_ID = 10;
	public static final boolean RECCOMENDATION_ENGINE_LIVE_ON_REQUEST = true;

}




