/*
 * Created on 07-Oct-2006
 *
 */
package com.lbr.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;


/**
 * @author Deepak Kumar
 *
 */
public interface SpringHibernateDAO {
	
	/**
	 * Retrieve all <code>true</code>/<code>false</code> from the datastore.
	 * @return a <code>true</code> or  <code>fasel</code>.
	 */
	public boolean checkUserLogin(String strUserName, String strPassword) throws DataAccessException,java.sql.SQLException;
   
	/**
	 * Saves Article object to the datastore.
	 *
	 */
	public void addUser(com.lbr.dao.hibernate.domain.Login obj) throws DataAccessException;


	/**
	 * Update Article object ot the datastore.
	 *
	 */
	public void updateUser(com.lbr.dao.hibernate.domain.Login obj) throws DataAccessException;


	/**
	 * Retrieve <code>Article</code> from the datastore.
	 * @return Article.
	 */
	public com.lbr.dao.hibernate.domain.Login loadUser(String id) throws DataAccessException;

	
	/**
	 * Retrieve all <code>true</code>/<code>false</code> from the datastore.
	 * @return a <code>true</code> or  <code>fasel</code>.
	 */
	public boolean checkValidUserName(String strUserid) throws DataAccessException,java.sql.SQLException;
	
	
	/**
	 * Retrieve <code>Country Name</code>s from the datastore.
	 * @return a <code>Collection</code> of Country.
	 */
	public Collection getUsersList() throws DataAccessException,java.sql.SQLException;

	/**
	 * Retrieve all <code>true</code>/<code>false</code> from the datastore.
	 * @return a <code>true</code> or  <code>fasel</code>.
	 */
	public int getUserId(String strUserid) throws DataAccessException,java.sql.SQLException;
	
	
	/**
	 * Retrieve all <code>true</code>/<code>false</code> from the datastore.
	 * @return a <code>true</code> or  <code>fasel</code>.
	 */
    public String[]  retriveUserForgetPassword(String strUserName, String strEmail) throws DataAccessException,java.sql.SQLException;
	
	
	
}

