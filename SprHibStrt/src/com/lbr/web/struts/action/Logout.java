package com.lbr.web.struts.action;
/*
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Logout extends Action
{
	private static final Logger logger = Logger.getLogger(Logout.class);
  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception{

	  try{

		  HttpSession session = request.getSession(false);
		  if(session!=null) {
			  session.invalidate();
			  }

	  	  }catch(Exception e){
	  		String strError=e.getMessage();
			logger.debug("Error is: " + strError);
	  	  }


	  	return mapping.findForward("success");
	   }


  }

