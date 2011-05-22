<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean2" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html3" %> 

<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.web.struts.form.*"%>

<LINK rel="stylesheet" type="text/css" name="style" href="<html:rewrite page='/css/style.css'/>">
<LINK rel="stylesheet" type="text/css" name="base" href="<html:rewrite page='/css/base.css'/>">
<html:html locale="true">
<head>
<!--  
<title><bean:message key="welcome.title"/></title>
-->
<html:base/>
</head>

<body bgcolor="white">
<h3><bean:message key="message.location.helper.heading"/></h3>
<font color="red"><html:errors/></font>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<logic:present name="eventsMessages">

<font color="blue" size="1">

	<logic:iterate id="msg" name="eventsMessages">
	<ul><li>
	<bean:write name="msg"/><br>
	</li></ul>
	</logic:iterate>
	
</font>
</logic:present>

  <html:form action="/UserLocation"  style="position: relative; left: 10px; top:20px;" method="post">
  <input type="hidden" name="formAction" value=""/>
    <%! String prefStatus; %> 
    <bean:message key="message.location.header.help" />
    <br/><br/>
	<table style=""position: relative; left: 5px; top: 40px;>
		<tr>
	        <td colspan="2"><b>Use either of the OPTIONS below to search the location:</b></td>
	    </tr>
	    <tr>
	        <td><b>OPTION 1 :</b></td>
	    </tr>		
	    <tr>
	        
	        <td><b>PIN Code* :</b></td>
	        <td><html:text property="pincode" size="8" maxlength="6"></html:text> </td>

			<!-- 
			name = form name as in <form-bean name="UserPreferenceForm"  ...struts-config.xml 
			property = attribute of type="com.lbr.web.struts.form.UserPreferenceForm" 
			label = attribute of ObjectType stored in the collection ...CategoryData here
			value= attribute of ObjectType stored in the collection...CategoryData here
			 -->        

	    </tr>
	     <tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/>
	    <tr>
	        <td><b>OPTION 2 :</b></td>
	    </tr>	    
	    <tr>
	        <td><b>Area Name :</b></td>
	        <td><html:text property="areaName"></html:text> </td>
	        <td><b><bean:message key="label.UserLocation.html.text.cityname"/>* :</b></td>
	        <td><html:text property="cityName"></html:text> </td>	        
	    </tr>
  <tr/><tr/><tr/>
	    <tr>
	        <td><b>State Name* :</b></td>
	        <td>
				<html:select property="stateID" onchange="">
					<html:optionsCollection name="UserLocationForm" property="stateList" label="stateName" value="stateId" />
				</html:select>	 
			</td>       
	    </tr>
  
<tr/><tr/><tr/><tr/><tr/><tr/> <tr/><tr/><tr/> <tr/><tr/><tr/>
        <tr> 
            <td/>  
	    	<td>
	           	<html:submit onclick="SetActionSubmitForm('UserLocationForm', 'locationSearch')">
	                     <bean:message key="label.common.html.select.button.locate" />
		        </html:submit>
	        </td>
	    <%  
		if(request.getParameter("fromEvents")!=null && ((String)request.getParameter("fromEvents")).equals("true")){
		%> 		    
		    <td>
		       	<html:cancel onclick="SetActionSubmitForm('UserLocationForm', 'eventLocationSelectionCancel')">Cancel</html:cancel>
		    </td> 
	  <% }  else{ %>  
	    <td>
	       	<html:cancel onclick="SetActionSubmitForm('UserLocationForm', 'locationPreferenceCancel')">Cancel</html:cancel>
	    </td> 
	     <% } %>  
	    </tr>
	</table>
	
    <% 
    UserLocationForm prefForm =(UserLocationForm)session.getAttribute("UserLocationForm");
 	if(prefForm!=null && prefForm.getSuggestedLocations()!=null){
 		int count =0;
 	%>
	
 <div id="myDiv" style="position: absolute; left: 670px; top:80px;">
My potential Locations (total): <%=prefForm.getSuggestedLocations().size()%>
 	<table cellpadding="3" cellspacing="3" border="1">   
   <%   
		if(prefForm.getSuggestedLocations().size()>20){
		%> 	
 	    <tr> 
		<td>
	       	<html:submit onclick="SetActionSubmitForm('UserLocationForm', 'locationPreferenceConfirm')">
	                 <bean:message key="label.common.html.select.button.locate.confirm" />
	        </html:submit>
	    </td>

<%--     
	    <%  
		if(request.getParameter("fromEvents")!=null && ((String)request.getParameter("fromEvents")).equals("true")){
		%> 		    
		    <td>
		       	<html:cancel onclick="SetActionSubmitForm('UserLocationForm', 'eventLocationSelectionCancel')">Cancel</html:cancel>
		    </td> 
	  <% }  else{ %>  
	    <td>
	       	<html:cancel onclick="SetActionSubmitForm('UserLocationForm', 'locationPreferenceCancel')">Cancel</html:cancel>
	    </td> 
	     <% } %>  
--%>		     			    
	</tr> 
	<% 		}   %>
 		<tr>
 		    <th>Select</th>
	    	<th>PIN Code</th>
	        <th>Name</th>
	        <th>City</th>
	        <th>State</th>
	    </tr>
	
	 	<% 
    List<Locations> currUserPreferences = prefForm.getSuggestedLocations();
	for (Iterator iterator = currUserPreferences.iterator(); iterator.hasNext();) {
		Locations lc = (Locations) iterator.next();
	%>
	    <tr>
    	<td>
			<html:radio property="userSelectedCurrLocation" value="<%=lc.getLocationId().toString()%>"/>
    	</td>	    
    	<td>
    	<%=lc.getPincode()%>[<%=lc.getLocationId()%>]
    	</td>
        <td>
        <%=lc.getLocName()%>
        </td>
    	<td>
    	<%=lc.getCity().getCityName()%>[<%=lc.getCity().getCityId()%>]
    	</td>
    	<td>
    	<%=lc.getCity().getState().getStateName()%>[<%=lc.getCity().getState().getStateId()%>]
    	</td>
        </tr>
	
<% }   
		if(prefForm.getSuggestedLocations().size()>0){
		%> 
	    <tr> 
			<td>
		       	<html:submit onclick="SetActionSubmitForm('UserLocationForm', 'locationPreferenceConfirm')">
		                 <bean:message key="label.common.html.select.button.locate.confirm" />
		        </html:submit>
		    </td>
	    <% }
		
 	}
 	else
 		System.out.println("Suggested Locations is null");
   %>
   	    </table>
 </div>	
   </html:form>
 

</body>
</html:html>
<script type="text/javascript" language="javascript" src="../lbr.js"></script>
