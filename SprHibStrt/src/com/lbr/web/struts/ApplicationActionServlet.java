package com.lbr.web.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import com.lbr.dao.hibernate.domain.Users;
import org.apache.log4j.Logger;
public class ApplicationActionServlet extends ActionServlet
{
	private static final Logger logger = Logger.getLogger(ApplicationActionServlet.class);
    /**
     * Ask the specified Action instance to handle this request.  Return
     * the <code>ActionForward</code> instance (if any) returned by
     * the called <code>Action</code>.
     *
     * @param action The Action to process this request
     * @param mapping The ActionMapping we are processing
     * @param formInstance The ActionForm we are processing
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */



    protected ActionForward processActionPerform(Action action,
                                        ActionMapping mapping,
                                        ActionForm formInstance,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
	throws IOException, ServletException
	{
    	super.process(request, response);

    	logger.debug("############# ApplicationActionServlet called.....");
    	try{
				//return action.execute(mapping, formInstance, request, response);
    			return mapping.findForward("userLogin");
			}
    	catch (Exception e) {

		}
		finally{
		}
		return null;
	}

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub


		Users currUserID = (Users)request.getSession().getAttribute("USERVO");
		if(currUserID!=null)
			super.process(request, response);  ///UserLoginJsp.do
		else {
			String uri = request.getRequestURI();
			if(uri.indexOf("UserLogin")!= -1 || uri.indexOf("UserRegister")!= -1)
				super.process(request, response);
			else
				logger.warn("======= Not authorized =====");

		}
	}

    /**
     * Retrieve and return the <code>ActionForm</code> bean associated with
     * this mapping, creating and stashing one if necessary.  If there is no
     * form bean associated with this mapping, return <code>null</code>.
     *
     * @param mapping The ActionMapping we are processing
     * @param request The servlet request we are processing
     */
/*    protected ActionForm processActionForm(ActionMapping mapping,
    					   HttpServletRequest request)
	{

		ActionForm actionForm = super.process(mapping, request);

//		logger.debug("======== Action Form name  ========= "+actionForm.getClass().getName());
//		logger.debug("======== Action Form is AppForm ?? ========= "+(actionForm instanceof ApplicationForm));
		if(actionForm instanceof ApplicationForm)
		{


		}

		return actionForm;
	}*/


}
