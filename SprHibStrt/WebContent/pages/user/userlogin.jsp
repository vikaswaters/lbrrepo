<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html locale="true">
<head>
	<title><bean:message key="welcome.title"/></title>
	<LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/css/style.css'/>">
</head>
	<html:base/>

<body>

<%@ include file="../top.jsp"%>  
<center>
   
   <table width="40%">
       
   <tr>
   <!--
     <td colspan="5">
	
			<table width="400" border="1"  cellpadding="0" cellspacing="0" align="center" >
				<tr>
						  
				-->		 <td>
						  <html:form action="/UserLogin" method="post" focus="userid" onreset="setFocus()">
							  <table border="1" cellspacing="2" cellpadding="1" width="100%" class="signup">
								  <tr>
									 <td align="center" colspan="2" ><font size="4" color="blue">User Login</font><br>
									 	<font color="red"><html:errors/>
									 </td>
								  </tr> 
								 
								  <!-- display errors -->
								
                                  <!-- ..................-->
								  <tr align="center">
									  <td align="right" width="50%"><b>User ID:</b></td> 
									  <td width="50%"><html:text property="userid" size="30" maxlength="30"/></td>
								 </tr> 
								 <tr align="center">
									  <td align="right"><b>Password:</b></td> 
									  <td><html:password property="password" size="30" maxlength="30"/></td>
								 </tr> 
												
								  <tr>
									  <td  align="center" colspan="2"><html:submit>Sign-In !</html:submit></td>
								 </tr> 
								  
								 
					               
							 </table>
						</html:form>
					</td>
					
				<!--	
			</tr>
		</table>
     </td> -->
	 </tr>
</table>

</center>
<body>
</html:html>