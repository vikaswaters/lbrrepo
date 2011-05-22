package com.lbr.web.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserLoginForm extends ActionForm{
	private static final Logger logger = Logger.getLogger(UserLoginForm.class);

	      private String action="add";
		  private String userid = null;
		  private String password = null;

		  public void reset(ActionMapping mapping,HttpServletRequest request){
			  this.userid=null;
			  this.password=null;
			  this.action="add";
		  }

	      public ActionErrors validate(
	      ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      /*
	      if( getUserid() == null || getUserid().length() < 1 ) {
	        errors.add("username",new ActionMessage("error.username.required"));
	      }
	      if( getPassword() == null || getPassword().length() < 1 ) {
	        errors.add("password",new ActionMessage("error.password.required"));
	      }

*/
	      return errors;
	  }

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}
	}



