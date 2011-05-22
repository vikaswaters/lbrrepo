package com.lbr.web.struts.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lbr.services.ServiceFinder;



public class showUserList {






	public class IndexAction extends Action
	{
		//private static final Logger logger = Logger.getLogger(IndexAction.class);
	  public ActionForward execute(
	    ActionMapping mapping,
	    ActionForm form,
	    HttpServletRequest request,
	    HttpServletResponse response) throws Exception{

		  //Retrieve the DAO Reference
	  	  com.lbr.dao.SpringHibernateDAO allcooljobsDAO = (com.lbr.dao.SpringHibernateDAO) ServiceFinder.getContext(request)
	  		.getBean("SpringHibernateDao");

		  try{


			  //latest jobs

			  Collection col = allcooljobsDAO.getUsersList();

			  request.setAttribute("userlist",col);

			  //logger.debug("col size:"+col.size());



		  	  }catch(Exception e){
		  		String strError=e.getMessage();
				//logger.debug("Error is: " + strError);
		  	  }


		  	return mapping.findForward("success");
		   }


	  }




}
