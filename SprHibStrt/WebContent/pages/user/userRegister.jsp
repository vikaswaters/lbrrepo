<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>


<html:html locale="true">
<%@page import="com.lbr.web.struts.form.*"%>
<head>
<LINK rel="stylesheet" type="text/css" name="style" href="<html:rewrite page='/css/style.css'/>">
<LINK rel="stylesheet" type="text/css" name="base" href="<html:rewrite page='/css/base.css'/>">
<title></title>
<html:base/>

<script type="text/javascript" language="javascript" src="../ajax.js"></script>
<script type="text/javascript" language="javascript" src="../lbr.js"></script>
<script type="text/javascript">


function checkEmailXXXX(email) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
		return (true);
	}
	alert("Invalid E-mail Address! Please re-enter.")
	return (false);
}


function validateFormXXXXX(formObj){

		if(formObj.userid.value.length==0){
		alert("Please enter User ID!");
		formObj.userid.focus();
		return false;
		}
		
		if(formObj.password.value.length==0){
		alert("Please enter password!");
		formObj.password.focus();
		return false;
		}
		
		if(formObj.email.value.length==0){
		alert("Please enter Email!");
		formObj.email.focus();
		return false;
		}
		
		if(!checkEmail(formObj.email.value)){
		   formObj.email.focus();
		   return false;
		}
		
		if(formObj.address.value.length==0){
		alert("Please enter address!");
		formObj.address.focus();
		return false;
		}
		
		if(formObj.phno.value.length==0){
		alert("Please enter Phone No.!");
		formObj.phno.focus();
		return false;
		}

		if(isNaN(formObj.phno.value)){
		alert("Please enter correct Phone No!");
		formObj.phno.focus();
		return false;
		}


		//formObj.action.value="createnew";
		return true;
}

</SCRIPT>
</head>
<body>


<%@ include file="../top.jsp"%>  

<%boolean updateProfile = false; 
UserRegisterForm prefForm =(UserRegisterForm)request.getAttribute("UserRegisterForm");
if(prefForm!=null){
	String action = prefForm.getAction();
	if( action!=null && action.equals("update"))
		updateProfile = true;
}
%>
 <center>
 <!--
 <table width="60%"> 
	 <tr>
		 <td width="100%">
		 
	 --> 
	 <html:form action="/UserRegister"  method="post" onsubmit="return validateUserRegisterForm(this);" focus="userid" onreset="setFocus()">
	 
	   <html:hidden property="id" />
	   
	   <!-- 
	   <html:hidden property="action"/>
	   <html:hidden property="actionUpdateData"/>
	   -->
	   
	   
	   
	   <table border="1" class="signup"  align="center">
<%if(!updateProfile) {%>
	<tr> 
		  <td colspan="2" align="center">
		   <font size="4" color="#660099">Please Enter the Following Details</font><br>
		  </td>
   </tr>
<% }else{ %>	
	<tr> 
		  <td colspan="2" align="center">
		   <font size="4" color="#660099">Please update only the fields that need to be modified.</font><br>
		  </td>
   </tr>		
<% }%>		
		   <!--
		   <tr>
			  <td align="right" width="50%"><b>Id</b></td> 
			  <td width="50%" align="left">
				 <html:text property="id" size="30" maxlength="120"/>
			  </td>
		 </tr> 
		 -->
		  <tr><td colspan="2" align="center"><font color="red"><html:errors/></font>&nbsp;</td></tr>
<%if(!updateProfile) {%>		  
		 <tr>
		  <td align="right"><b>User Id<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:text property="userid" size="30" maxlength="12" onblur="checkUserNameAvailability()"/> 
		  </td>
		  <%-- Its a stupid step to have name and ID as same...but thats what works in IE...typical MS fokkers --%>
		  <td name="unameavailability" id="unameavailability"> </td>  
	 </tr>
	 
	  <tr>
		  <td align="right"><b>Password<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:password property="password" size="30" maxlength="12"/>
		  </td>
	 </tr>
	  <tr>
		  <td align="right"><b>Confirm Password<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:password property="passwordCopy" size="30" maxlength="12"/>
		  </td>
	 </tr>	 
<% } %>	
<%if(updateProfile) {%>
	  <tr>
		  <td align="right"><b>New Password<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:password property="password" size="30" maxlength="12"/>
		  </td>
	 </tr>	 
	  <tr>
		  <td align="right"><b>Confirm New Password<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:password property="passwordCopy" size="30" maxlength="12"/>
		  </td>
	 </tr>		 
<% } %>	
	<tr>
		  <td align="right"><b>Email<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:text property="email" size="30" maxlength="30"/>
		  </td>
	 </tr>

	  <tr>
		  <td align="right"><b>Address</b></td>
		  <td align="left">
			 <html:text property="address" size="30" maxlength="60"/>
		  </td>
	 </tr>
	  <tr>
		  <td align="right"><b>Mobile No.<font color="#FF0000">*</font></b></td>
		  <td align="left">
			 <html:text property="phno" size="30" maxlength="14"/>
		  </td>
	 </tr>
	 <tr><td colspan="2">&nbsp;</td></tr>
	   <tr>
	   <%if(!updateProfile) {%>
		  <td align="center" colspan="2">
			  <html:submit property="action" value="Create User">Create User</html:submit>
		  </td>
		<% }else{ %>	  
		  <td align="center" colspan="2">
			  <html:submit property="action" value="Update User">Update User</html:submit>
		  </td>
		  <% } %>	
	 </tr>
	 </table>
	 <html:javascript formName="UserRegisterForm"/>
</html:form>
   <!--
		 </td>
	 </tr>
 </table>
</center>
-->
</body>
</html:html>
