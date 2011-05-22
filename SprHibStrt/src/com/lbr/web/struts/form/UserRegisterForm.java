package com.lbr.web.struts.form;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UserRegisterForm extends ActionForm{
	private static final Logger logger = Logger.getLogger(UserRegisterForm.class);


	   private String action="add";
	   private String actionUpdateData;

	    private Integer id;

		  private String userid;  //shud be b/w 6-12 chars long
		  private String password;  //shud be b/w 6-12 chars long
		  private String passwordCopy;  //shud be b/w 6-12 chars long

		  private String email;
		  private String address;
		  private String phno; ////shud be atmost 14 digits

		  public void reset(ActionMapping mapping,HttpServletRequest request){

			  this.id = null;
			  this.userid=null;
			  this.password=null;
			  this.email=null;
			  this.address=null;
			  this.phno=null;

			  this.action="add";
			  this.actionUpdateData="";

		  }

	      public ActionErrors validate(

	      ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      if(password!=null && passwordCopy!=null && password!="" && passwordCopy!=""){
	    	  if(!password.equals(passwordCopy))
	    		  errors.add("password", new ActionMessage("error.updateprofile.password.retype.dissimilar"));
	      }
	      if(phno!=null && phno!=""){
	    	  try{
	    		  new Long(phno);
	    	  }catch (Exception e) {
	    		  errors.add("password", new ActionMessage("error.register.mobilno.invalid"));
			}
	      }
	      return errors;
	  }



		public String getPasswordCopy() {
			return passwordCopy;
		}

		public void setPasswordCopy(String passwordCopy) {
			this.passwordCopy = passwordCopy;
		}

		public String getAction() {
			return action;
		}


		public void setAction(String action) {
			this.action = action;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getPhno() {
			return phno;
		}



		public void setPhno(String phno) {
			this.phno = phno;
		}


		public String getUserid() {
			return userid;
		}


		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getActionUpdateData() {
			return actionUpdateData;
		}

		public void setActionUpdateData(String actionUpdateData) {
			this.actionUpdateData = actionUpdateData;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}




}
