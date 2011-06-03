package com.lbr.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.UserVO;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.services.WebServiceCall;
import com.lbr.web.struts.form.UserLoginForm;


public class UserLoginAction  extends Action{
	private static final Logger logger = Logger.getLogger(UserLoginAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		        ActionMessages errors = new ActionMessages();
		        UserLoginForm objForm = (UserLoginForm) form;
		        String strUserid=objForm.getUserid();
		      	String strPassword=objForm.getPassword();
		      	boolean loginStatus = DaoUtilities.checkUserLogin(strUserid, LbrUtility.generaPassword(objForm.getPassword()));
		      	   if(loginStatus==true){
		      		   // ################  Store the user details in the session....it will be  used thruout  ##############
		      		   HttpSession  session = request.getSession();
		      		   Users user = DaoUtilities.getUserByIDSmartCall(request, strUserid);
		      		   if(LbrConstants.IP_BASED_LOCATION_ENABLED)
		      			   LbrUtility.initIPBasedLocation(request, user);
		      		   LbrAction.setThreadLocalUserValue(user);
		  			   session.setAttribute("USERVO", user);
		  			   logger.info("********* User logged in :"+user.getUserName());
		  			 if(user.getUserpermissions().getBasicModulePermission().booleanValue())
		  				 return mapping.findForward("userHome");   // success for normal users
		  			 else
		  				return mapping.findForward("eventsHome");  // success for Events Entry users
		         } else {
		      	   errors.add("login",new ActionMessage("error.login.invalid"));
		             saveErrors(request,errors);
		             return mapping.findForward("failure");
		         }
	       }
	}






