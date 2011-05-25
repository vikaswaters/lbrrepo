<%@page import="com.lbr.LbrUtility"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean2" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html3" %> 

<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.web.struts.form.*"%>

<%-- 
<LINK rel="stylesheet" type="text/css" name="style" href="<html:rewrite page='/css/style.css'/>">
<LINK rel="stylesheet" type="text/css" name="base" href="<html:rewrite page='/css/base.css'/>">
<LINK rel="stylesheet" type="text/css" name="style" href="<html:rewrite page='/css/calendar.css'/>">
--%>
<%-- 
<LINK REL=StyleSheet TYPE="text/css" HREF="/css/calendar.css" MEDIA=screen>
--%>
<script language="javascript" type="text/javascript">
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}
</script>

   <% 
   boolean showEditButtons = false;
   boolean eventRetrieved = false;
 	EventsForm prefForm =(EventsForm)session.getAttribute("EventsForm");
	if(prefForm==null){
		prefForm =  new EventsForm();
		session.setAttribute("EventsForm", prefForm);
	}
    if(request.getParameter("modifyEvent")!=null && ((String)request.getParameter("modifyEvent")).equals("true")){
    	prefForm.setUserEventEditWIP(true);
    }	
    if(prefForm.isUserEventEditWIP()){
    	showEditButtons = true;
    }
    
 	if(prefForm.getUserSelectedEventIDForEdit() !=null && !prefForm.getUserSelectedEventIDForEdit().equals("")){
 		eventRetrieved = true;
 	}
	UserPreferenceForm userPrefForm =(UserPreferenceForm)session.getAttribute("UserPreferenceForm");
	if(userPrefForm==null){
		userPrefForm =  new UserPreferenceForm();
		session.setAttribute("UserPreferenceForm", userPrefForm);
	}   
	UserLocationForm userLocForm =(UserLocationForm)session.getAttribute("UserLocationForm");
	if(userLocForm==null){
		userLocForm =  new UserLocationForm();
		session.setAttribute("UserLocationForm", userLocForm);
	}
 	%>
 	
<html:html locale="true">
<head>
<!--  
<title><bean:message key="label.events.entrypage.welcome.title"/></title>
-->
<html:base/>
</head>
<body bgcolor="white">
<%@ include file="top.jsp"%>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<h3><bean:message key="label.events.entrypage.welcome.title"/></h3>

<font color="red"><html:errors/></font> 


<logic:present name="eventsMessages">
<font color="blue" size="4">
	<logic:iterate id="msg" name="eventsMessages">
	<bean:write name="msg"/><br>
	</logic:iterate>
</font>
</logic:present>
	    <%  
		if(request.getAttribute("saveAddressCityAnyway")!=null && ((String)request.getAttribute("saveAddressCityAnyway")).equals("true")){
		%> 
           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'saveAddressCityAnyway')">
                     <bean:message key="label.common.html.event.button.saveAnyway" />
	        </html:submit>
	  <% } %>  

  <html:form action="/Events"  style="position: relative; left: 10px; top:10px;" method="post" onsubmit="return validateEventsForm(this);" focus="eventName" onreset="setFocus()">
  <input type="hidden" name="formAction" value=""/>
    <%! String prefStatus; %> 

	<table>
						<!-- Event Details -->
		<tr>
	        <td><b><bean:message key="label.events.details" /></b></td>
	    </tr>
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.name" /></b></td>
	        <td><html:text property="eventName" size="25" maxlength="50"></html:text> </td>
	    </tr>	
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.eventDescription" /></b></td>
	        <td><html:textarea property="eventDetails" rows="4" cols="40" onkeydown="limitText(this.form.eventDetails,this.form.countdown,300);" 
 					onkeyup="limitText(this.form.eventDetails,this.form.countdown,300);"></html:textarea> 
 			</td>
	    </tr> 
	    <tr>
	   		<td/>
	   		<td/>
		    <td><font size="1">(Maximum characters: 300)<br/>
			You have <input readonly type="text" name="countdown" size="3" value="300"> characters left.</font></td>
		<tr>	    
	    <tr/>
	    				<!-- Event Category Details -->	     	    
		<tr>
	        <td><b><bean:message key="label.events.category" /></b></td>
	    </tr>	    
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.categoryName" /></b></td>
	        <td>
				<html:select property="category" onchange="loadSubCategoryData()">
				    <html:option value="0">Select Category</html:option>
					<html:optionsCollection name="UserPreferenceForm" property="categoryList" label="catName" value="catId" />
				</html:select>	        
	        </td>
	    </tr>  
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.subCategoryName" /></b></td>
	        <td>
				<html:select property="subcategory" onchange="">
					<html:optionsCollection name="UserPreferenceForm" property="subcategoryList" label="subCatName" value="subCatId" />
				</html:select>
	        </td>
	    </tr> 
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.level" /></b>
	        <img alt="help" height="16" width="16" src="../images/help.jpg" TITLE="For beginners/amateurs audience, choose Lower level. For very advanced(niche) audience, choose High level.">
	        </td>
	        <td>
				<html:select property="eventLevel" onchange="">
					<html:optionsCollection name="EventsForm" property="eventLevelList" label="levelName" value="levelId" />
				</html:select>
	        </td>
	    </tr> 	    	    
						<!-- Event Dates -->
		<tr>
	        <td><b><bean:message key="label.events.Dates" /></b></td>
	    </tr>	    	    	
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.startDate"/></b><br/>e.g 2011-01-28 10:00:00</td>
	        <td><html:text property="startDate" size="17" maxlength="16" altKey="startDate.format" ></html:text> 
	        	<a onclick="InitiateCalendar('startDate', 'anchor18', 'endDate', true);" name="anchor18" id="anchor18"><img src="../images/calendar.jpg"  height="20" width="30"/></a>
	        </td></td>
	    </tr>
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.endDate" /></b></td>
	        <td><html:text property="endDate" size="17" maxlength="16"></html:text> 
	        	<a onclick="InitiateCalendar('endDate', 'anchor19', null);" name="anchor19" id="anchor19"><img src="../images/calendar.jpg"  height="20" width="30"/></a>
	        </td></td>
	    </tr>  
	    
	    <tr/><tr/><tr/>
	    				<!-- Event Contact Details -->
		<tr>
	        <td><b><bean:message key="label.events.contactDetails" /></b></td>
	    </tr>	    
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.contactPerson" /></b></td>
	        <td><html:text property="contactPerson" size="20" maxlength="20"></html:text> </td>
	    </tr>  	    
	    <tr>
	    	<td/>
	        <td><b><bean:message key="label.events.contactPhone" /></b></td>
	        <td><html:text property="contactPhone" size="20" maxlength="20"></html:text> </td>
	    </tr> 
	    <tr/><tr/><tr/>

			    
	    <%-- 
		<tr class= "separatorThick"/>
		--%>
	    				<!-- Event Venue Details -->	    
	    <tr>
	        <td><b><bean:message key="label.events.address" /></b></td>
	        <td> </td>
	    </tr> 	    
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.line1" /></b></td>
	        <td><html:text property="line1" size="20" maxlength="60"></html:text> </td>	        
	    </tr>
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.line2" /></b></td>
	        <td><html:text property="line2" size="20" maxlength="60" ></html:text> </td>	        
	    </tr>	    
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.area" /></b></td>
	        <td><html:text property="areaName" size="20" maxlength="30"></html:text> </td>	        
	    </tr>
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.city" /></b></td>
	        <td><html:text property="cityName"  size="20" maxlength="20"></html:text> </td>	        
	    </tr>
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.pin" /></b></td>
	        <td><html:text property="pincode" size="8" maxlength="6"></html:text> </td>	        
	    </tr>	    	    
	    <tr>
	    	<td/>		      
	        <td><b><bean:message key="label.events.address.state" /></b></td>
	        <td>
				<html:select property="stateID" onchange="">
					<html:option value="0">Select State</html:option>
					<html:optionsCollection name="UserLocationForm" property="stateList" label="stateName" value="stateId" />
				</html:select>	 
			</td>       
	    </tr>
	    
	    <%-- 
		<tr class= "separatorThick"/>
		--%>
	    <tr class="locationTableRow">
	        <td><b><bean:message key="label.events.locationID" /></b><br/>
	        	<% if (prefForm != null && prefForm.getCurrentLocationStr()!=null){ %>
	        		<%=prefForm.getCurrentLocationStr()	%> 
	        	<% } %>	
	        </td>
	        <td>	
	           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'Help Location')">
	                     <bean:message key="label.events.location.help" />
		        </html:submit>	

		        <%-- 
	           	<html:link styleClass="locationLink" onclick="SetActionSubmitForm('EventsForm', 'Help Location')">
	                     <bean:message key="label.events.location.help" />
		        </html:link>		        

		        --%>		     
		    </td>
		    <td/> 
	    </tr> 
    
	    <%-- 
		<tr class= "separatorThick"/>
		--%>    
	    <tr class="locationTableRow">
	        <td/> 
	        <td>	
	        
	           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'clearEventLocation')">
	                     <bean:message key="label.events.location.clear" />
		        </html:submit>	
		        <%-- 	        
	           	<html:link styleClass="locationLink" action="Events.do?formAction=clearEventLocation">
	                     <bean:message key="label.events.location.clear" />
		        </html:link>	
		        --%>     
		    </td>
	        <td>	
	        
	           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'resetForm')">
	                      <bean:message key="label.events.form.reset" />
		        </html:submit>		        
	        <%-- 
	           	<html:reset>
	                     <bean:message key="label.events.form.reset" />
		        </html:reset>	
		    --%>
		    </td>
	    </tr> 	    
 
		
	    
	    <%-- 
		<tr class= "separatorThick"/>
		--%>
<% if(showEditButtons) {%>		
        <tr> 
            <td/>  
	    	<td>
	           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'retrieveEvent')">
	                     <bean:message key="label.common.html.event.button.retrieve" />
		        </html:submit>
	        </td>
	        
	    </tr>
	<% if (eventRetrieved){ %>
	        <tr> 
	            <td/>  
		    	<td>
		           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'deleteEvent')">
		                     <bean:message key="label.common.html.event.button.delete" />
			        </html:submit>
		        </td>
		    </tr>	    
	        <tr> 
	            <td/>  
		    	<td>
		           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'updateEvent')">
		                     <bean:message key="label.common.html.event.button.update" />
			        </html:submit>
		        </td>
		    </tr>
	<% } 
} else{ %>	    		
        <tr> 
            <td/>  
	    	<td>
	           	<html:submit onclick="SetActionSubmitForm('EventsForm', 'saveEvent')">
	                     <bean:message key="label.common.html.event.button.save" />
		        </html:submit>
	        </td>
	        <%-- 
	    <td>
	       	<html:cancel onclick="SetActionSubmitForm('UserLocationForm', 'locationPreferenceCancel')">Cancel</html:cancel>
	    </td>  
	    --%>	        
	    </tr>
 <% } %>
	</table>
 
   
   <%--  Table to display the searched events --%>
<% 
if(prefForm!=null && prefForm.getSearchEvents()!=null){
%>
	
 <div id="myDiv" style="position: absolute; left: 600px; top:200px;">
Searched Events (total): <%=prefForm.getSearchEvents().size()%>
 	<table cellpadding="3" cellspacing="3" border="1">   
   <%   
		if(prefForm.getSearchEvents().size()>20){
		%> 	
 	    <tr> 
			<td>
		       	<html:submit onclick="SetActionSubmitForm('EventsForm', 'eventsConfirm')">
		                 <bean:message key="label.common.html.select.button.edit.confirm" />
		        </html:submit>
		    </td>
		    <td>
		       	<html:cancel onclick="SetActionSubmitForm('EventsForm', 'eventsCancel')">Cancel</html:cancel>
		    </td>   
		</tr> 
	<% 		}   %>
 		<tr>
 			<th>Select</th>
 		    <th>Name</th>
 		    <th>SubCategory</th>
	    	<th>Start Date</th>
	        <th>End Date</th>
	        <th>Contact No</th>
	        <th>Contact Person</th>
	        <th>Venue</th>
	    </tr>
	
	 	<% 
    List<Events> searchedEvents = prefForm.getSearchEvents();
	for (Iterator iterator = searchedEvents.iterator(); iterator.hasNext();) {
		Events lc = (Events) iterator.next();
	%>
	    <tr>
    	<td>
    	<%--
			<html:radio property="userSelectedEventIDForEdit" value="<%=lc.getEventId().toString()+""%>"/>
			 --%>
			  <html:radio property="userSelectedEventIDForEdit" value="<%=lc.getEventId().toString()%>"/>
    	</td>	    
    	<td>
    	<%=lc.getName()%>
    	</td>
        <td>
		<%=lc.getSubcategory().getSubCatName() %>
        </td>
    	<td>
    	<%=lc.getStartDate()%>
    	</td>
    	<td>
    	<%=lc.getEndDate()%>
    	<td>
    	<%=lc.getContactNo()%>
    	</td>
    	<td>
    	<%=lc.getContactPerson()%>
    	</td>
    	<td>
    	<%=lc.getAddress()%>[<%=LbrUtility.printLocationHTML(lc.getLocations())%>]
    	</td>    	
    	</td>
        </tr>
	
<% }   
		if(prefForm.getSearchEvents().size()>0){
		%> 
 	    <tr> 
			<td>
		       	<html:submit onclick="SetActionSubmitForm('EventsForm', 'eventsConfirm')">
		                 <bean:message key="label.html.select.button.event.confirm" />
		        </html:submit>
		    </td>
		    <td>
		       	<html:cancel onclick="SetActionSubmitForm('EventsForm', 'eventsCancel')">Cancel</html:cancel>
		    </td>   
		</tr> 
	   <%
		}
 	}
 	else{
 		System.out.println("SearchEvents is null");
 	}
   %>
   	    </table>
 </div>	  
 
<html:javascript formName="EventsForm"/>

</html:form> 
<%@include  file="calendarStatic.html" %>    
</body>
</html:html>

<script type="text/javascript" language="javascript" src="ajax.js"></script>
<script type="text/javascript" language="javascript" src="lbr.js"></script>
