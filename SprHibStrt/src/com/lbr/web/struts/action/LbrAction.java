package com.lbr.web.struts.action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public abstract class LbrAction extends Action {
	private static final Logger logger = Logger.getLogger(LbrAction.class);

	/**
	 * Mechanism to carry the errors info thru the diff layers
	 */
	private static ThreadLocal<ActionMessages> threadLocal = new ThreadLocal<ActionMessages>();

	public static void setThreadLocalValue(ActionMessages errors) {
		threadLocal.set(errors);
	  }

	  public static ActionMessages getThreadLocalValue() {
	    return (ActionMessages) threadLocal.get();
	  }

	protected ActionForward handleSuccessOrFailure(boolean result, HttpServletRequest request, ActionMessages errors, ActionMapping mapping, String operation, String errorKey){
		List messages = new LinkedList();
		if(result){
			messages.add(operation+" successful!!");
			request.setAttribute("eventsMessages", messages);
			return mapping.findForward("success");
		}
		else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));  // errorKey
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}
	}

}
