<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<!--<%if(request.getSession().getAttribute("USERVO")==null) {%> 
	<logic:redirect forward="userLogin"/>
<% } %>
	
	-->
	<font color="red"><html:errors property="error.login.required"/></font>
<html:html locale="true">
<head>
<title><bean:message key="welcome.title"/></title>
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
<!-- 
<font color="red"><html:errors/></font>
 -->
<%@ include file="/pages/user/userPreferences.jsp"%>
</body>
</html:html>
