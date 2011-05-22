package com.lbr.web.struts.form;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class UserForgetPasswordForm extends ActionForm
{
	private static final Logger logger = Logger.getLogger(UserForgetPasswordForm.class);

      private String action="add";


	  private String username = null;
	  private String email = null;


      public void setAction(String action){
		this.action=action;
	  }

	  public String getAction(){
		return this.action;
	  }




	  public void setUsername(String username){
		this.username=username;
	  }

	  public String getUsername(){
		return this.username;
	  }



	   public void setEmail(String email){
		this.email=email;
	  }

	  public String getEmail(){
		return this.email;
	  }




	  public void reset(ActionMapping mapping,HttpServletRequest request){


		  this.username=null;
		  this.email=null;

		  this.action="add";

	  }


      public ActionErrors validate(

      ActionMapping mapping, HttpServletRequest request ) {
      ActionErrors errors = new ActionErrors();




	  if(( getUsername() == null || getUsername().length() < 1 ) && ( getEmail() == null || getEmail().length() < 1)){
        errors.add("username",new ActionMessage("error.username/email.required"));
      }

      return errors;
  }




}