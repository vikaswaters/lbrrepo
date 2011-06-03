<%@page import="com.lbr.SubcategoryWrapper"%>
<%@page import="com.lbr.LbrConstants"%>
<%@page import="com.lbr.LbrUtility"%>
<%@page import="com.lbr.web.struts.form.UserPreferenceForm"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<%@page import="com.lbr.web.struts.action.*"%>
<%@page import="com.lbr.web.struts.form.*"%>
<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.core.*"%>

<script type="text/javascript" language="javascript" src="ajax.js"></script>
<script type="text/javascript" language="javascript" src="lbr.js"></script>
<font color="red"><html:errors /></font>
<h2>
<%
	String userName = ((Users) request.getSession().getAttribute(
			"USERVO")).getUserName();
    Users curruser = LbrAction.getThreadLocalUserValue();
    boolean fullDebugON = LbrConstants.LBR_DEBUG && (curruser.getUserpermissions().getUserTypeId() == LbrConstants.ADMIN_USERTYPE_ID);
%> &nbsp;&nbsp;<%=userName + "'s "%> <bean:message
	key="label.UserPreferenceAction.message.dashboard" /></h2>

<logic:present name="messages">
	<font color="blue" size="1"> <logic:iterate id="msg"
		name="messages">
		<bean:write name="msg" />
		<br>
	</logic:iterate> </font>
</logic:present>

<%
	UserPreferenceForm prefForm = (UserPreferenceForm) session
			.getAttribute("UserPreferenceForm");
%>
<html:form action="/UserPreference" method="post">
<div style="width: 100; position: relative; left: 5px; top: 10px;">

	<input type="hidden" name="formAction" value="" />
	<%!String prefStatus;%>
	<table>
		<tr class="spaceUnderSmallNoWrap">
			<td><b>Select Category :</b></td>
			<td><!-- 
			name = form name as in <form-bean name="UserPreferenceForm"  ...struts-config.xml 
			property = attribute of type="com.lbr.web.struts.form.UserPreferenceForm" 
			label = attribute of ObjectType stored in the collection ...CategoryData here
			value= attribute of ObjectType stored in the collection...CategoryData here
			 --> <html:select property="category"
				onchange="loadSubCategoryData(this)">
				<html:option value="0">Select Category</html:option>
				<html:optionsCollection name="UserPreferenceForm"
					property="categoryList" label="catName" value="catId" />
			</html:select></td>
		</tr>
		<tr>
			<td width="50%"><b>Select SubCategory :</b></td>
			<td><html:select property="subcategory" onchange=""
				multiple="true">
				<!-- <html:option value="0">Select SubCategories</html:option>  -->
				<html:optionsCollection name="UserPreferenceForm"
					property="subcategoryList" label="subCatName" value="subCatId" />
			</html:select></td>
		</tr>
		<tr />
		<tr>
			<td />
			<td><html:submit
				onclick="SetActionSubmitForm('UserPreferenceForm', 'save')">
				<bean:message key="label.common.html.select.button.savePreference" />
			</html:submit></td>
		</tr>
		<tr class="spaceUnderSmall">
			<td />
			<td><html:submit
				onclick="SetActionSubmitForm('UserPreferenceForm', 'delete')">
				<bean:message key="label.common.html.select.button.deletePreference" />
			</html:submit></td>
		</tr>

		<tr class="spaceUnderSmall">
			<td width="50%"><b>Vicinity Policy : </b><img alt="help" height="16" width="16" src="../images/help.jpg" TITLE="Recommendations can be generated for events within the PINCode/City/District/State"></td>
			<td><html:select property="vicinitypolicyID">
				<html:option value="0">Use System Default</html:option>
				<html:optionsCollection name="UserPreferenceForm"
					property="vicinityPolicyList" label="vicinityName"
					value="vicinityID" />
			</html:select></td>
		</tr>
		<tr>
			<td><b>Select Date Range : </b><img alt="help" height="16" width="16" 
				src="../images/help.jpg"
				TITLE="Start and end dates for events you are interested in. e.g 2011-01-28 10:00 , 2011-01-31 20:00"></td>
			<td><html:text property="startDate" size="17" maxlength="16"></html:text>
			<a onclick="CallCalendarMethod('startDate', 'anchor18');"
				name="anchor18" id="anchor18"><img src="../images/calendar.jpg"
				height="20" width="30" /></a></td>
			</td>
		</tr>
		<tr class="spaceUnderSmallNoWrap">
			<td />
			<td><html:text property="endDate" size="17" maxlength="16"></html:text>
			<a onclick="CallCalendarMethod('endDate', 'anchor19');"
				name="anchor19" id="anchor19"><img src="../images/calendar.jpg"
				height="20" width="30" /></a></td>
		</tr>
		<!-- 
	     <tr class="spaceUnderSmall">   
	        <td><b>My Current Location :</b><br/>(recommendations will be<br/>generated in the vicinity of<br/>this location:) <%if (prefForm != null) {%>
	        						<%=prefForm.getCurrentLocationStr()%>
	        						<%}%>
	        </td>
	    	<td>
	           	<html:link action="UserLocation.do?formActionOriginatedFrom=UserPreferenceModule">
	                     <h3><bean:message key="label.common.html.select.button.location" /></h3>
		        </html:link>
	        </td>
	    </tr>	     
	      -->


		<tr class="spaceUnderSmall">
			<td><b>My Current Location :</b><img alt="help" height="16" width="16" 
				src="../images/help.jpg"
				TITLE="Recommendations will be generated in the vicinity of this location">
			</td>
			<td class="locationData">
			<%
				if (prefForm != null) {
			%> <%=prefForm.getCurrentLocationStr()%> <%
 	}
 %>
			</td>
		</tr>
		<tr class="nowrap">
			<td>
			    <html:link action="UserLocation.do?formActionOriginatedFrom=UserPreferenceModule">
				<h3><bean:message key="label.common.html.select.button.location" /></h3>
				</html:link>
			</td>
			<td>
			<img alt="help" height="16" width="16" src="../images/help.jpg" TITLE="Use the location helper to select your location. Recommendation for events will be generated in this location's vicinity.">
			</td>			
		</tr>
		<tr>
			<%
				if (request.getParameter("formAction") != null
							&& !((String) request.getParameter("formAction"))
									.equals("hideRecommend")
							&& (prefForm != null
									&& prefForm.getRecommendations() != null && prefForm
									.getRecommendations().size() > 0)) {
			%>
			<td colspan="1"><html:submit onclick="SetActionSubmitForm('UserPreferenceForm', 'recommend')">
				<bean:message key="label.common.html.select.button.recommend" />
			</html:submit></td>
		</tr>
		<tr class="spaceUnderSmall">
			<td colspan="1"><html:submit
				onclick="SetActionSubmitForm('UserPreferenceForm', 'hideRecommend')">
				<bean:message key="label.common.html.select.button.recommend.hide" />
			</html:submit>
			</td>
		</tr>
		<%
			} else {
		%>
		<tr class="spaceUnderSmall">
			<td colspan="1"><html:submit
				onclick="SetActionSubmitForm('UserPreferenceForm', 'recommend')">
				<bean:message key="label.common.html.select.button.recommend" />
			</html:submit></td>
			<%
				}
			%>
	    </tr>
	</table>
</div>

<div id="myDiv"
	style="position: absolute; width: 300px; height: 150px; top: 140px; left: 500px;"
	type="hidden">
<%
	if (prefForm != null && prefForm.getUserPreferencesWithLevels() != null) {
%>
<h3><bean:message
	key="label.UserPreferenceAction.message.preferences.list" /><%=prefForm.getUserPreferencesWithLevels().size()%>
</h3>
<table cellpadding="3" cellspacing="3" border="1" width="300">
	
	<tr>
		<th>Category</th>
		<th>SubCategory</th>
		<th>Level</th>
	</tr>

	<%
			List<SubcategoryWrapper> currUserPreferences = prefForm.getUserPreferencesWithLevels();
			int counter =0;
			String[] userprefLevels = prefForm.getSubcatLevels();
			for (Iterator iterator = currUserPreferences.iterator(); iterator.hasNext();) {
				//String currSubCatUserPrefLevel = userprefLevels[counter++];
				SubcategoryWrapper subcatwrap = (SubcategoryWrapper) iterator.next();
				Subcategory subcat = subcatwrap.getUserPreference();
				Category cat = subcat.getCategory();
	%>
	<tr/>
	<tr>
		<td><%=cat.getCatName()%> <%if(fullDebugON) {%>[<%=cat.getCatId()%>] <%}%></td>
		<td><%=subcat.getSubCatName()%> <%if(fullDebugON) {%> [<%=subcat.getSubCatId()%>]<%}%></td>
		<td>
			<select name="subcatLevels" onchange="">
			<option value="1"<% if(subcatwrap.getLevel()==1) {%> SELECTED  <%} %> >Very Low</option>
			<option value="2"<% if(subcatwrap.getLevel()==2) {%> SELECTED  <%} %> >Low</option>
			<option value="3"<% if(subcatwrap.getLevel()==3) {%> SELECTED  <%} %> >Medium</option>
			<option value="4"<% if(subcatwrap.getLevel()==4) {%> SELECTED  <%} %> >High</option>
			<option value="5"<% if(subcatwrap.getLevel()==5) {%> SELECTED  <%} %> >Very High</option></select>
	    </td>	
	</tr>
	
	<%
		}
	%>
	<tr/><tr/>
	<tr class="tablerowButton">
	<td/><td/>
		<td>
		<table>
			<html:submit
				onclick="SetActionSubmitForm('UserPreferenceForm', 'saveLevels')">
				<bean:message key="label.common.html.select.button.save.levels" />
			</html:submit>
			</table>
		</td>
	</tr>	
</table>
<%
	} else {
		System.out.println("UserPreference is null  --> Not specified");
%> My current Preferences (total): 0 <%
	}
%>

</html:form>
</div>

<div id="recoDiv" style="position: absolute; left: 850px; top: 130px;">

<%
	if (prefForm != null
			&& prefForm.getRecommendations() != null
			&& request.getParameter("formAction") != null
			&& !((String) request.getParameter("formAction"))
					.equals("hideRecommend")) {
%>

<h3><bean:message
	key="label.UserPreferenceAction.message.recos.list" /><%=prefForm.getRecommendations().size()%></h3>
(Generated as of System state at: <%=RecommendationEngine.lbrRecommendations
						.getGeneratedOn()%>) <%
	if (prefForm.getRecommendations().size() > 0) {
%>
<table cellpadding="3" cellspacing="3" border="1" width="510">
	<tr>
		<th>#</th>
		<th>Recommended Event</th>
	</tr>

	<%
		int ii = 0;
				List<EventRecommendationVO> userRecos = prefForm
						.getRecommendations();
				for (Iterator iterator = userRecos.iterator(); iterator
						.hasNext();) {
					EventRecommendationVO eventreco = (EventRecommendationVO) iterator
							.next();
					Events event = eventreco.getEvent();
	%>
	<tr>
		<td><%=++ii%></td>
		<td><%=LbrUtility.printEventForHTML(event)%></td>
	</tr>
	<%
		}
			}
	%>
</table>
<%
	}
	if (prefForm.getRecommendations() == null
			|| prefForm.getRecommendations().size() == 0) {
		System.out.println("No recommendation for User !!");
		if (prefForm.getFormAction() != null
				&& prefForm.getFormAction().equalsIgnoreCase(
						"recommend")) {
			prefForm.setFormAction(null);
%> <br />
<bean:message key="Info.UserPreferenceAction.Recommendation.none" /> <%
 	}
 	}
 %>
</div>
<%@include file="../calendarStatic.html"%>
<!-- 
 <iframe style="position: absolute; left: 30px; top:500px;" width="500" height="400" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;output=embed"></iframe><br /><small><a href="http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;source=embed" style="color:#0000FF;text-align:left">View Larger Map</a></small>
   -->
