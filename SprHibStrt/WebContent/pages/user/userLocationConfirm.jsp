<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean2" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html3" %> 

<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.web.struts.form.*"%>

<script type="text/javascript">

function UserPrefAction(formName, action){
	//alert('OK1');
	oForm = document.forms[formName];
	oForm.formAction.value = action;
	oForm.submit();
}
</script>

<html:html locale="true">
<head>
<!--  
<title><bean:message key="welcome.title"/></title>
-->
<html:base/>
</head>
<body bgcolor="white">
 


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<h3><bean:message key="welcome.heading"/></h3>
  <html:form action="/UserLocationConfirm"  style="position: absolute; left: 10px; top:130px;">
      	<html:radio property="userSelectedCurrLocation" value="SGS"/>
      	<html:radio property="userSelectedCurrLocation" value="YUT"/>
  	<input type="hidden" name="formAction" value=""/>
    <%! String prefStatus; %> 
    <bean:message key="message.location.header" />
 
    <% 
    UserLocationForm prefForm =(UserLocationForm)session.getAttribute("UserLocationForm");
 	if(prefForm!=null && prefForm.getSuggestedLocations()!=null){
 		int count =0;
 	%>
My potential Locations (total): <%=prefForm.getSuggestedLocations().size()%>
 	<table cellpadding="3" cellspacing="3" border="1">   
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
			<html:radio property="userSelectedCurrLocation" value="UPS"/>
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
   <%
		} 
 	}
 	else
 		System.out.println("UserLocationForm  is null");
   %>
	</table>
  </html:form>
</body>
</html:html>
