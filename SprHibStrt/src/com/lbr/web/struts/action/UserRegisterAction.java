package com.lbr.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lbr.LbrUtility;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.form.UserRegisterForm;

public class UserRegisterAction extends LbrAction {
	private static final Logger logger = Logger.getLogger(UserRegisterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserRegisterForm objForm = (UserRegisterForm) form;

		String forwardToPage = "input";
		String strError = "";
		ActionMessages errors = new ActionMessages();
		String ajax = request.getParameter("ajax");
		LbrAction.setThreadLocalErrosValue(errors);
        if(ajax!=null && ajax.equals("yes")){  // AJAX call ONLY
        	logger.debug("**** AJAX request received for name availability **********");
        	boolean ans = false;
        	String potentialUserName = request.getParameter("userName");
        	Users curruser = DaoUtilities.getUserByIDSmartCall(request, potentialUserName);
        	if(curruser==null)
        		ans = true;  ///yes  its  available
        	String ajaxXml = getAjaxResponseXml(potentialUserName, ans)	;
        	LbrUtility.sendAjaxResponse(ajaxXml, request, response);
        	//logger.debug("AJAX response XML: "+ajaxXml);
        	return null;
        }

		try {
			boolean ValidUsernameStatus = false;
			if (objForm.getAction().equals("Create User")) {
				Users newUser = new Users();
				newUser.setAddress(objForm.getAddress());
				newUser.setEmail(objForm.getEmail());
				newUser.setPassword(LbrUtility.generaPassword(objForm.getPassword()));
				if(objForm.getPhno()!="")
					newUser.setMobileNo(new Long(objForm.getPhno()));
				newUser.setUserName(objForm.getUserid());
				newUser.setUserpermissions((DaoUtilities.getModuleUserTypeByID(0)));
				boolean created = DaoUtilities.createNewUser(newUser);
				if(created){
					forwardToPage = "success";   // SUCCESS
					ValidUsernameStatus = true;
				}
				else{
					//errors.add("invalidUsername", new ActionMessage("error.register.username.taken"));
					saveErrors(request, errors);
					LbrAction.getThreadLocalErrorsValue();
					return mapping.findForward("input");
				}
				objForm.setAction("update");
			}
			else if (request.getParameter("action")!=null && request.getParameter("action").equals("Update User")) {
				String actiom = (String)request.getParameter("action");
				Users curruser = DaoUtilities.getUserByIDSmartCall(request, ((Users)request.getSession().getAttribute("USERVO")).getUserName());
				if(objForm.getAddress()!="" && !objForm.getAddress().equals(curruser.getAddress()))
					curruser.setAddress(objForm.getAddress());
				if(objForm.getEmail()!="" && !objForm.getEmail().equals(curruser.getEmail()))
					curruser.setEmail(objForm.getEmail());
				if(objForm.getPassword()!="" && !objForm.getPassword().equals(curruser.getPassword()))
					curruser.setPassword(objForm.getPasswordCopy());
				if(objForm.getPhno()!="" && !objForm.getPhno().equals(curruser.getMobileNo()))
					curruser.setMobileNo(new Long(objForm.getPhno()));

				DaoUtilities.updateUserByIDSmartCall(request, curruser);
				forwardToPage = "userHomeJSP";

			}

		} catch (Exception e) {
			forwardToPage = "input";
			strError = e.getMessage();
			logger.error("===> Error:" + strError);
		}

		return mapping.findForward(forwardToPage);

	}

	private String getAjaxResponseXml(String potentialUserName, boolean ans) {
	    StringBuffer xml = new StringBuffer();
	    xml.append("<?xml version=\"1.0\"?>\n");
	    xml.append("<usernameAvail>\n");
		      xml.append("<username>");
		      xml.append(potentialUserName);
		      xml.append("</username>\n");
		      xml.append("<avail>");
		      if(ans)
		    	  xml.append(potentialUserName+" is available");
		      else
		    	  xml.append(potentialUserName+" is NOT available");
		      xml.append("</avail>\n");
		xml.append("</usernameAvail>\n");
	    return xml.toString();
}
}
