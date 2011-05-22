/*
 * Created on Aug 5, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.lbr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lbr.dao.hibernate.domain.Login;
import com.lbr.web.common.UsersList;


/**
 * Hibernate implementation of the JobModule interface.
 *
 * <p>
 * The mappings are defined in "com.lbr.hbm.xml", located in the root of the
 * classpath.
 *
 * @author Deepak Kumar
 * @since 07-Oct-2006
 */
public class SpringHibernateDAOImpl extends HibernateDaoSupport implements
		SpringHibernateDAO {


//check admin login

	public boolean checkUserLogin(String strUserName, String strPassword) throws DataAccessException,java.sql.SQLException{
		boolean valid = false;
		Connection conn = this.getSession().connection();
		//Write jdbc code to validate the user against database
		Statement smt = conn.createStatement();
		ResultSet rs;
		//write select query for checking password

		String query="select id from login where loginid='"+strUserName+"' and password='"+strPassword+"'";
		rs=smt.executeQuery(query);

		if(rs.next()== true){
			valid=true;

		}else{
			valid=false;
		}

		smt.close();
		rs.close();
		conn.close();

		return valid;


	}


	public void addUser(com.lbr.dao.hibernate.domain.Login obj) throws DataAccessException{
		getHibernateTemplate().save(obj);
}


public Login loadUser(String id) throws DataAccessException{
	//return getHibernateTemplate().find("from roseindialocal.dao.hibernate.Article obj where obj.id = '" + id + "'");
			return (Login) getHibernateTemplate().get(Login.class,new Integer(id));
}

public boolean checkValidUserName(String strUserid) throws DataAccessException,java.sql.SQLException{
	boolean valid = false;
	Connection conn = this.getSession().connection();
	//Write jdbc code to validate the user against database
	Statement smt = conn.createStatement();
	ResultSet rs;
	//write select query for checking password

	String query="select id from login where loginid='"+strUserid+"'";
	rs=smt.executeQuery(query);


	if(rs.next() == true){

		valid=true;

	}else{
		valid=false;
	}

	smt.close();
	rs.close();
	conn.close();


	return valid;
}


//get latest jobs
public Collection getUsersList()throws DataAccessException,java.sql.SQLException{



	Connection conn = this.getSession().connection();

	//Write jdbc code
	Statement smt = conn.createStatement();

	ResultSet rs=null;

	ArrayList list = new ArrayList();

	String query="SELECT id,loginid positionVacant FROM login";



	rs=smt.executeQuery(query);



	while(rs.next()){

		UsersList userslist =new UsersList();

		int jobid=rs.getInt("id");
		userslist.setId(jobid);

		String loginid=rs.getString("loginid");
		userslist.setLoginid(loginid);

	list.add(userslist);

	}

	rs.close();
	smt.close();
	conn.close();


	return list;
}

public int getUserId(String strUserid) throws DataAccessException,java.sql.SQLException{

	Connection conn = this.getSession().connection();
	//Write jdbc code to validate the user against database
	Statement smt = conn.createStatement();
	ResultSet rs;
	//write select query for checking password

	String query="select id from login where loginid='"+strUserid+"'";
	rs=smt.executeQuery(query);
	rs.next();

	int id=rs.getInt("id");


	smt.close();
	rs.close();
	conn.close();
	return id;
}

// retrive user forget password
public String[] retriveUserForgetPassword(String strUserName, String strEmail) throws DataAccessException,java.sql.SQLException{
	//boolean valid = false;




	Connection conn = this.getSession().connection();
	//Write jdbc code to validate the user against database
	Statement smt = conn.createStatement();

	ResultSet rs;
	String query;


	//write select query for checking password

    if(strUserName != ""){

              query="select password,email,loginid from login where loginid='"+strUserName+"'";

	     }else{
	          query="select password,email,loginid from login where email='"+strEmail+"'";
         }

	rs=smt.executeQuery(query);



    String[] returnValues = new String[3];



 	while(rs.next()){



	 		returnValues[0]=rs.getString("password");

	 		returnValues[1]=rs.getString("email");

	 		returnValues[2]=rs.getString("loginid");


 	}

 	smt.close();
 	rs.close();
 	conn.close();

 	if(returnValues[0] != null){

 		return returnValues;

 	}	else{
 		String[] errorValues = new String[2];
 		errorValues[0]="error";
 		return errorValues;
 	}
}



public void updateUser(Login arg0) throws DataAccessException {
	getHibernateTemplate().update(arg0);

}


}
