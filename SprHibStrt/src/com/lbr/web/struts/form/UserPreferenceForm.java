package com.lbr.web.struts.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.lbr.SubcategoryWrapper;
import com.lbr.VICINITY_POLICY;
import com.lbr.Vicinity;
import com.lbr.core.EventRecommendationVO;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.specificdao.DaoUtilities;


public class UserPreferenceForm extends ActionForm{
	private static final Logger logger = Logger.getLogger(UserPreferenceForm.class);

	private String formAction = "";
	private String category;
	private String[] subcategory;
	private String categories;
	private String subCategories;
	private List<Category> categoryList;
	private List<Subcategory> subcategoryList;
	//private Map<Integer, List<Subcategory>> cacheSubcategoryList;
	//private List<Subcategory> userPreferences;
	private List<SubcategoryWrapper> userPreferencesWithLevels;
	private List<EventRecommendationVO> recommendations;
	private String startDate;
	private String endDate;
	private String currentLocationStr = "Not specified";

	private int vicinitypolicyID;
	private List<Vicinity> vicinityPolicyList;
	
	private String[] subcatLevels;
	
//	private List<List<EventLevel>> subcatLevelsList;
	

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	public UserPreferenceForm() {
		super();
		List<Vicinity> vicinityList = new ArrayList<Vicinity>();
		vicinityList.add(new Vicinity(1, VICINITY_POLICY.PINCODE.getVICINITY_POLICYName()));
		vicinityList.add(new Vicinity(3, VICINITY_POLICY.CITY.getVICINITY_POLICYName()));
		vicinityList.add(new Vicinity(4, VICINITY_POLICY.DISTRICT.getVICINITY_POLICYName()));
		vicinityList.add(new Vicinity(5, VICINITY_POLICY.STATE.getVICINITY_POLICYName()));
		this.setVicinityPolicyList(vicinityList);
		this.setVicinitypolicyID(2);

		List<Category> categoryList = DaoUtilities.getAllCategory();
        this.setCategoryList(categoryList);
        this.setCategory("3");

        List<Subcategory> subcategoryList = DaoUtilities.getAllSubCategoryForCatID(new Integer(this.getCategory()));
        this.setSubcategoryList(subcategoryList);
        
        //subcatLevelsList = new ArrayList<List<EventLevel>>();   //new ArrayList<EventLevel>()
        
	}

	public String getCurrentLocationStr() {
		return currentLocationStr;
	}

	public void setCurrentLocationStr(String currentLocationID) {
		this.currentLocationStr = currentLocationID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<EventRecommendationVO> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<EventRecommendationVO> recommendations) {
		this.recommendations = recommendations;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String[] getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String[] subcategory) {
		this.subcategory = subcategory;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCategories() {
		return categories;
	}


	public void setCategories(String categories) {
		this.categories = categories;
	}


	public String getSubCategories() {
		return subCategories;
	}


	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}


	public List<Category> getCategoryList() {
		return categoryList;
	}


	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}


	public List<Subcategory> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(List<Subcategory> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

/*	public Map<Integer, List<Subcategory>> getCacheSubcategoryList() {
		return cacheSubcategoryList;
	}

	public void setCacheSubcategoryList(Map<Integer, List<Subcategory>> cacheSubcategoryList) {
		this.cacheSubcategoryList = cacheSubcategoryList;
	}*/

/*	  public List<Subcategory> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(List<Subcategory> userPreferences) {
		this.userPreferences = userPreferences;
	}*/

	
	public int getVicinitypolicyID() {
		return vicinitypolicyID;
	}

	public List<SubcategoryWrapper> getUserPreferencesWithLevels() {
		return userPreferencesWithLevels;
	}

	public void setUserPreferencesWithLevels(
			List<SubcategoryWrapper> userPreferencesWithLevels) {
		this.userPreferencesWithLevels = userPreferencesWithLevels;
	}

	public void setVicinitypolicyID(int vicinitypolicyID) {
		this.vicinitypolicyID = vicinitypolicyID;
	}

	public List<Vicinity> getVicinityPolicyList() {
		return vicinityPolicyList;
	}

	public void setVicinityPolicyList(List<Vicinity> vicinityPolicyList) {
		this.vicinityPolicyList = vicinityPolicyList;
	}

	public String[] getSubcatLevels() {
		return subcatLevels;
	}

	public void setSubcatLevels(String[] subcatLevels) {
		this.subcatLevels = subcatLevels;
	}

	public String toXml() {
		    StringBuffer xml = new StringBuffer();
		    xml.append("<?xml version=\"1.0\"?>\n");
		    xml.append("<lbr generated=\""+System.currentTimeMillis()+"\">\n");
		    xml.append("<subcategory>\n");
		    for (Iterator iterator = subcategoryList.iterator(); iterator.hasNext();) {
				Subcategory data = (Subcategory) iterator.next();
				String catID = data.getSubCatId().toString();
				String catName = data.getSubCatName();
			      xml.append("<name>");
			      xml.append(catName);
			      xml.append("</name>\n");
			      xml.append("<catid>");
			      xml.append(catID);
			      xml.append("</catid>\n");
		    }
		    xml.append("</subcategory>\n");
		    xml.append("</lbr>\n");
    	    return xml.toString();
	}

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

/*	 public void populateSecondaryDropdown(String primarySelectionCatID){
		 //Map<Integer, List<Subcategory>> categoryNSubCubcategoryCache = DaoUtilities.categoryNSubCubcategoryCache;
		 List<Subcategory> subcategoryList  = null;
		 if (((Map)this.getCacheSubcategoryList()) == null){
			 DaoUtilities.categoryNSubCubcategoryCache = new HashMap<Integer, List<Subcategory>>();
			 this.setCacheSubcategoryList(DaoUtilities.categoryNSubCubcategoryCache);
		 }
		 else
			 DaoUtilities.categoryNSubCubcategoryCache = this.getCacheSubcategoryList();

		 int selection = new Integer(primarySelectionCatID);
		 if (DaoUtilities.categoryNSubCubcategoryCache.get(primarySelectionCatID) == null)
			 subcategoryList = DaoUtilities.getAllSubCategoryForCatID(new Integer(selection));

		 this.setSubcategoryList(subcategoryList);
		 DaoUtilities.categoryNSubCubcategoryCache.put(new Integer(primarySelectionCatID), subcategoryList);
		 this.setCategory(primarySelectionCatID);
	 }*/

	 public void populateSecondaryDropdown(String primarySelectionCatID){
		 List<Subcategory> subcategoryList  = null;
		 if (DaoUtilities.categoryNSubCubcategoryCache == null){
			 DaoUtilities.categoryNSubCubcategoryCache = new HashMap<Integer, List<Subcategory>>();
		 }

		 Integer selection = new Integer(primarySelectionCatID);
		 if(DaoUtilities.categoryNSubCubcategoryCache.get(selection) == null){ // make DB call ONLY if SubCategoryList is not available, then cache it
			 subcategoryList = DaoUtilities.getAllSubCategoryForCatID(new Integer(selection));
			 DaoUtilities.categoryNSubCubcategoryCache.put(selection, subcategoryList);
		 }else{
			 subcategoryList = DaoUtilities.categoryNSubCubcategoryCache.get(selection);
		 }
		 this.setSubcategoryList(subcategoryList);
		 this.setCategory(primarySelectionCatID);
	 }
	 
}
