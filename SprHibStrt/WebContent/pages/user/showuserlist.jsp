<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<!-- 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@page import="allcooljobs.web.common.*"%>
-->
<%@page import="java.util.*"%>

<html:html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>index page</title>
<html:base/>
<style type="text/css">


A:active { COLOR: green; TEXT-DECORATION: none } 
A:hover { COLOR: red; TEXT-DECORATION: none; font-weight: none }

</style>
</head>
</html:html>
<body>




<table width="100%" border="0" align="left" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr valign="top">
		
			<td valign="top">
			<table width="100%" height="188" border="0" cellpadding="1" cellspacing="1" bgcolor="#FFFFFF">
				<tr bgcolor="#F4F4F4">
				  <td align ="left"><strong>Latest Jobs</strong></td>
				  </tr>
				  <tr>
				  <td>
				  <table>
				     
					
				 	<c:forEach var="result" items="${userlist}">

						<tr>
							
							<td><li><a href="../jobs/viewjob/<c:out value="${result.id}"/>"><c:out value="${result.loginid}"/></a></li></td>
							
						</tr>

                    
					</c:forEach>

				
				 
				 
				</table>
				</td>
			   </tr>
			</table>
</body>
</html>