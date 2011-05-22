 
  <%
 String userid=(String)session.getAttribute("userID");
if(userid == null)
{
	response.sendRedirect("../userlogin.jsp");
	
}else{
%> 
  
 <%@ taglib uri="/tags/struts-bean" prefix="bean" %>
 <%@ taglib uri="/tags/struts-html" prefix="html" %>
 <html:html locale="true">



 <%


		  						
		  			   
 String id=(String)session.getAttribute("ID");
// System.out.println("ID"+id);

%> 

 <head>

 <title></title>
 <html:base/>
 </head>
 <body>
 <%@ include file="../top.jsp"%>  
  
 <center>
  
  <p><b>You are login successfuly !</b></p>
<!--
  <p><a href="/project/UserRegister.do?action=Edit">Update your Profile</a></p>
  -->
</center>
  </body>
  </html:html>
  <%}%>