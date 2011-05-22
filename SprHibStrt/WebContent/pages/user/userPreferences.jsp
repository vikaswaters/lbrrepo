<%@page import="com.lbr.LbrUtility"%>
<%@page import="com.lbr.web.struts.form.UserPreferenceForm"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%@page import="com.lbr.web.struts.action.*"%>
<%@page import="com.lbr.web.struts.form.*"%>
<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.core.*"%>

<script type="text/javascript" language="javascript" src="ajax.js"></script>
<script type="text/javascript" language="javascript" src="lbr.js"></script>

<h2>
	<% String userName = ((Users)request.getSession().getAttribute("USERVO")).getUserName(); %>
	&nbsp;&nbsp;<%=userName+"'s "%>
	<bean:message key="label.UserPreferenceAction.message.dashboard" />
</h2>

<logic:present name="messages">
<font color="blue" size="1">
	<logic:iterate id="msg" name="messages">
	<bean:write name="msg"/><br>
	</logic:iterate>
</font>
</logic:present>	

<font color="red"><html:errors/></font>
   <% 
 	UserPreferenceForm prefForm =(UserPreferenceForm)session.getAttribute("UserPreferenceForm");
 	%>
 <div style="width: 100; position: relative; left: 5px; top: 10px;">
  <html:form action="/UserPreference" method="post">
  <input type="hidden" name="formAction" value=""/>
    <%! String prefStatus; %> 
	<table>
	    <tr>
	        <td>
	            <b>Select Category :</b>
	        </td>
	        <td>
			<!-- 
			name = form name as in <form-bean name="UserPreferenceForm"  ...struts-config.xml 
			property = attribute of type="com.lbr.web.struts.form.UserPreferenceForm" 
			label = attribute of ObjectType stored in the collection ...CategoryData here
			value= attribute of ObjectType stored in the collection...CategoryData here
			 -->        
			<html:select property="category" onchange="loadSubCategoryData()">
			    <html:option value="0">Select Category</html:option>
				<html:optionsCollection name="UserPreferenceForm" property="categoryList" label="catName" value="catId" />
			</html:select>
	        </td>
	    </tr>
	    <tr>
	        <td>
	            <b>Select SubCategory :</b>
	        </td>
	        <td>
			<html:select property="subcategory" onchange="" multiple="true">
			<!-- <html:option value="0">Select SubCategories</html:option>  -->
				<html:optionsCollection name="UserPreferenceForm" property="subcategoryList" label="subCatName" value="subCatId" />
			</html:select>
	        </td>
	    </tr>
	    <tr/>
	    <tr>
	        <td/>
	    	<td>
	           	<html:submit onclick="SetActionSubmitForm('UserPreferenceForm', 'save')">
	                     <bean:message key="label.common.html.select.button.savePreference" />
		        </html:submit>
	        </td>
	    </tr>
	    <tr>
	        <td/>
	    	<td>
	           	<html:submit onclick="SetActionSubmitForm('UserPreferenceForm', 'delete')">
	                     <bean:message key="label.common.html.select.button.deletePreference" />
		        </html:submit>
	        </td>
	    </tr>	 
	    <tr/><tr/><tr/> <tr/><tr/><tr/><tr/><tr/><tr/>  
 	    <tr>
	        <td><b>Vicinity Policy (optional):</b></td>
	        <td>
				<html:select property="vicinitypolicyID" onchange="loadSubCategoryData()">
				<html:option value="0">Use System Default</html:option>
					<html:optionsCollection name="UserPreferenceForm" property="vicinityPolicyList" label="vicinityName" value="vicinityID" />
				</html:select>	 
			</td>       
	    </tr> 
	     <tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/>      
	    <tr>
	        <td><b>Select Date Range</b><br/>e.g 2011-01-28 10:00</td>
	    	<td><html:text property="startDate" size="17" maxlength="16"></html:text> 
	    		<a onclick="CallCalendarMethod('startDate', 'anchor18');" name="anchor18" id="anchor18"><img src="../images/calendar.jpg"  height="20" width="30"/></a>
	    	</td></td>
	    </tr>
	    <tr class="spaceUnderSmall">
	        <td/> 
	        <td><html:text property="endDate" size="17" maxlength="16"></html:text> 
	        	<a onclick="CallCalendarMethod('endDate', 'anchor19');" name="anchor19" id="anchor19"><img src="../images/calendar.jpg"  height="20" width="30"/></a>
	        </td>
	    </tr>    
	     <tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/>  
	     <tr class="spaceUnderSmall">   
	        <td><b>My Current Location :</b><br/>(recommendations will be<br/>generated in the vicinity of<br/>this location:) <%if(prefForm!=null){%>
	        						<%=prefForm.getCurrentLocationStr()%>
	        						<%}%>
	        </td>
	    	<td>
	           	<html:link action="UserLocation.do?formActionOriginatedFrom=UserPreferenceModule">
	                     <h3><bean:message key="label.common.html.select.button.location" /></h3>
		        </html:link>
	        </td>
	    </tr>
	    <tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/>
	    
	    <tr>  
        
   <% 
   if(request.getParameter("formAction")!=null && !((String)request.getParameter("formAction")).equals("hideRecommend") && 
		   (prefForm!=null && prefForm.getRecommendations()!=null && prefForm.getRecommendations().size()>0) ){
	   
   %>	        
	    	<td colspan="2">
	           	<html:submit onclick="SetActionSubmitForm('UserPreferenceForm', 'hideRecommend')">
	           		<bean:message key="label.common.html.select.button.recommend.hide" />
		        </html:submit>
	        </td>
   <% } else {%>
	    	<td colspan="2">
	           	<html:submit onclick="SetActionSubmitForm('UserPreferenceForm', 'recommend')">
	           		<bean:message key="label.common.html.select.button.recommend" />
		        </html:submit>
	        </td>
   <%  }  %> 
	    </tr>
	</table>
   </html:form>
  </div>  

<div id="myDiv" style="position: absolute; width: 300px; height: 150px; top: 140px; left: 420px;" type="hidden">
   <% 
 	if(prefForm!=null && prefForm.getUserPreferences()!=null){
   %>
   <h3><bean:message key="label.UserPreferenceAction.message.preferences.list" /><%=prefForm.getUserPreferences().size()%> </h3>
 	<table cellpadding="3" cellspacing="3" border="1" width="300">   
 		<tr>
	    	<th>Category</th>
	        <th>SubCategory</th>
	    </tr>
	
	 	<% 
    List<Subcategory> currUserPreferences = prefForm.getUserPreferences();
	for (Iterator iterator = currUserPreferences.iterator(); iterator.hasNext();) {
		Subcategory subcat = (Subcategory) iterator.next();
		Category cat = subcat.getCategory();
	%>
	    <tr>
    	<td>
    	<%=cat.getCatName()%>[<%=cat.getCatId()%>]
    	</td>
        <td>
        <%=subcat.getSubCatName()%>[<%=subcat.getSubCatId()%>]
        </td>
        </tr>
   <%
		} 
	%>
	</table>
	<%
 	}
 	else{
 		System.out.println("UserPreference is null  --> Not specified");
 	%>
 		My current Preferences (total): 0
   <%
 	}
   %> 
 </div>

<div id="recoDiv" style="position: absolute; left: 750px; top: 130px;">

   <% 
 	if(prefForm!=null && prefForm.getRecommendations()!=null && request.getParameter("formAction") !=null && !((String)request.getParameter("formAction")).equals("hideRecommend")){
   %>

	<h3><bean:message key="label.UserPreferenceAction.message.recos.list" /><%=prefForm.getRecommendations().size()%></h3>(Generated as of System state at: <%=RecommendationEngine.lbrRecommendations.getGeneratedOn()%>)
	<%if(prefForm.getRecommendations().size()>0) {%>
	 	<table cellpadding="3" cellspacing="3" border="1" width="510">   
	 		<tr>
		    	<th>#</th>
		        <th>Recommended Event</th>
		    </tr>
		
		 	<% 
		 	int ii = 0;
		 	List<EventRecommendationVO> userRecos = prefForm.getRecommendations();
		for (Iterator iterator = userRecos.iterator(); iterator.hasNext();) {
			EventRecommendationVO eventreco = (EventRecommendationVO) iterator.next();
			Events event = eventreco.getEvent();
		%>
		    <tr>
	    	<td>
	    	<%=++ii%>
	    	</td>
	        <td>
	        <%=LbrUtility.printEventForHTML(event)%>
	        </td>
	        </tr>
		<%
		} 
	} 	
	%>
   </table> 
    <% }
 	if(prefForm.getRecommendations()==null || prefForm.getRecommendations().size()==0){
 		System.out.println("No recommendation for User !!");
 		if(prefForm.getFormAction()!=null && prefForm.getFormAction().equalsIgnoreCase("recommend")){
 			prefForm.setFormAction(null);
 	%>
 	<br/>
 		<bean:message key="Info.UserPreferenceAction.Recommendation.none" />
   <%
 		}
 	}
   %> 
 </div>
<%@include  file="../calendarStatic.html" %> 
 <!-- 
 <iframe style="position: absolute; left: 30px; top:500px;" width="500" height="400" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;output=embed"></iframe><br /><small><a href="http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;source=embed" style="color:#0000FF;text-align:left">View Larger Map</a></small>
   -->
   