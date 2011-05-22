<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html locale="true">
<head>
	<title><bean:message key="welcome.title"/></title>
	<LINK rel="stylesheet" type="text/css" name="anyname" href="<html:rewrite page='/css/style.css'/>">
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

						 
                      <html:form action="/UserForgetPassword" method="post">
						  <table border="0" cellspacing="2" cellpadding="1" width="100%" class="signup">
						  <tr>
							 <td align="center" colspan="2"><font size="5">Access Your Username and password</font></td>
						  </tr> 
						   <tr>
							  <td align="center" colspan="2"><font color="red"><html:errors/></font></td>
						   </tr>
						  <tr><td colspan="2"><p>&nbsp</p></td></tr>
						   <tr><td colspan="2"><li>Enter your user name as registered</li></td></tr>
						  <tr align="center">
							  
							  <td align="right">User Name:</td> 
							  <td align="left"><html:text property="username" size="30" maxlength="30"/></td>
						 </tr> 
						 <tr><td colspan="2"><p>&nbsp;</p></td></tr>  
						 <tr><td colspan="2"><li>OR If you do not remember your username, then enter your e-mail address as entered in your resume:</li></td></tr>
						 <tr>
							  <td align="right">Email:</td> 
							  <td><html:text property="email" size="30" maxlength="30"/></td>
						 </tr> 
						  <tr><td colspan="2"><p>&nbsp;</p></td></tr>              
						  <tr>
							  <td  align="center" colspan="2"><html:submit>Send Me My Username and Password</html:submit></td>
						 </tr> 
						 <!-- <tr><td colspan="2"><p>&nbsp;</p></td></tr> --> 
				   
									  
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