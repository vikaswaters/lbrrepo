package com.lbr.dao.genericdao.impl;


import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.lbr.dao.genericdao.GenericDao;
import com.lbr.dao.hibernate.domain.City;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.utils.ApplicationContextProvider;
import com.lbr.web.struts.action.LbrAction;

/**
 * Hibernate implementation of GenericDao
 * A typesafe implementation of CRUD and finder methods based on Hibernate and Spring AOP
 * The finders are implemented through the executeFinder method. Normally called by the FinderIntroductionInterceptor
 */
public class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK>
{
	private static final Logger logger = Logger.getLogger(GenericDaoHibernateImpl.class);
    private SessionFactory sessionFactory;
  //  private FinderNamingStrategy namingStrategy = new SimpleFinderNamingStrategy(); // Default. Can override in config
  //  private FinderArgumentTypeFactory argumentTypeFactory = new SimpleFinderArgumentTypeFactory(); // Default. Can override in config

    private Class<T> type;

    // Read the configuration file
    ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
    //ApplicationContext ctx = new FileSystemXmlApplicationContext("springconfig.xml");

    public GenericDaoHibernateImpl(Class<T> type)
    {
        this.type = type;
    }

    public PK create(T o) throws org.hibernate.exception.ConstraintViolationException, SQLGrammarException
    {
    	PK pk = null;
	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	    /** Starting the Transaction */
	    Transaction tx = session.beginTransaction();
	    try{
	    	pk = (PK)session.save(o);
		    tx.commit(); // flush occurs
	    }catch (ConstraintViolationException e) {
	    		logger.error(e);
				e.printStackTrace();
				LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.constraint.violation"));
				throw e;
	    }catch (DataException e) {
    		logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.sqldata.exception"));
			throw e;
	    }
	    catch (SQLGrammarException e) {
    		logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.sqlgrammarexception"));
			throw e;
	    }
	    catch (HibernateException e) {
    		logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.exception"));
			throw e;
	    }
	    catch (Exception e) {
    		logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.unknown.exception"));
			//throw e;
	    }
	    finally{
		    /** Closing Session */
		    session.close();
	    }
        return pk;
    }

    /*    public T read(PK id)
    {
    	T t = null;
	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	    *//** Starting the Transaction *//*
	    Transaction tx = session.beginTransaction();
	    try{
	    	t =  (T) session.get(type, id);
		    tx.commit(); // flush occurs
	    }catch (Exception e) {
				e.printStackTrace();
	    }
	    finally{
		    *//** Closing Session *//*
		    session.close();
		    return t;
	    }
    }*/
    public T read(PK id)
    {
        return (T) getSession().get(type, id);
    }

    public boolean update(T o) throws HibernateException
    {
        //getSession().update(o);

	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	    /** Starting the Transaction */
	    Transaction tx = session.beginTransaction();
	    try{
	    	session.saveOrUpdate(o);
		    tx.commit(); // flush occurs
	    }catch (ConstraintViolationException e) {
	    	tx.rollback();
	    	logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.constraint.violation"));
			//throw e;
			return false;
	    }catch (DataException e) {
    	    tx.rollback();
    	    logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.sqldata.exception"));
			//throw e;
			return false;
	    }
	    catch (SQLGrammarException e) {
	    	tx.rollback();
	    	logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.sqlgrammarexception"));
			//throw e;
			return false;
	    }
	    catch (HibernateException e) {
	    	tx.rollback();
	    	logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.exception"));
			//throw e;
			return false;
	    }
	    catch (Exception e) {
	    	tx.rollback();
	    	logger.error(e);
			e.printStackTrace();
			LbrAction.getThreadLocalErrorsValue().add("DatabaseError", new ActionMessage("error.database.hibernate.unknown.exception"));
			return false;
			//throw e;
	    }
	    finally{
		    /** Closing Session */
		    session.close();
	    }
	    return true;
    }

    public boolean delete(T o)
    {
	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	    /** Starting the Transaction */
	    Transaction tx = session.beginTransaction();
	    try{
	    	session.delete(o);
		    tx.commit(); // flush occurs
	    }catch (Exception e) {
	    		logger.error(e);
				e.printStackTrace();
				return false;
		    }
	    finally{
		    /** Closing Session */
		    session.close();
	    }
	    return true;
    }

//    public ScrollableResults scrollFinder(Method method, final Object[] queryArgs)
//    {
//        final Query namedQuery = prepareQuery(method, queryArgs);
//        return (ScrollableResults) namedQuery.scroll();
//    }

    public List customHibernateQuery(String HQL_QUERY, List<String> paramsNames, List<Object> paramsValues){
    	logger.debug("==HQL_QUERY==>"+HQL_QUERY);
	    List results = null;
	    /** Getting the Session Factory and session */
	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
	    Session session = factory.openSession();
	    /** Starting the Transaction */
	    Transaction tx = session.beginTransaction();
	    try{

		    // Create HQL Between clause
		   // HQL_QUERY = "from events e where ((windowEndDate < e.startDate) OR (e.startDate between e.startDate and e.endDate) OR (e.endDate between e.startDate and e.endDate)";
		    //HQL_QUERY = "from events e where e.startDate between :startDateRange and :endDateRange";
		    Query query = session.createQuery(HQL_QUERY);
		/*    query.setParameter("startDateRange", ts1);
		    query.setParameter("endDateRange", ts2);*/
		    if(paramsNames!=null)
			    for (int i = 0; i < paramsNames.size(); i++) {
			    	String str = (String)paramsNames.get(i);
			    	query.setParameter(str, paramsValues.get(i));
				}
		    results = query.list();
		    logger.debug("======= NumResults= "+results.size());
		/*    for (Iterator it = result.iterator(); it.hasNext();) {
		    		Events event = (Events) it.next();
		            //logger.debug("\nCol: " + event.get());
		           // logger.debug("Invoice Date: " + event.getInvoiceDate());

		    }*/
		    tx.commit(); // flush occurs
	    }catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
		    }
	    finally{
		    /** Closing Session */
		    session.close();
	    }
	    return results;
}

    public List customHibernateQueryByOrdinal(String HQL_QUERY, List<Object> paramsValues){
    	/*    	params = new ArrayList();
    	    	java.util.Date dt1 = null;
    	    	java.util.Date dt2 = null;
    			try {
    				dt1 = LbrConstants.timestampFormatter.parse("2011-01-27 10:00:00");
    				dt2 = LbrConstants.timestampFormatter.parse("2011-01-31 10:00:00");
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    	Timestamp ts1 = new Timestamp(dt1.getTime());
    	    	Timestamp ts2 = new Timestamp(dt2.getTime());

    	    	params.add(ts1);
    	    	params.add(ts2);*/
    	    /** Getting the Session Factory and session */
    	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
    	    Session session = factory.openSession();
    	    /** Starting the Transaction */
    	    Transaction tx = session.beginTransaction();

    	    // Create HQL Between clause
    	    //HQL_QUERY = "from events e where e.startDate between ? and ?";   //OK
    	    Query query = session.createQuery(HQL_QUERY);
    	    //query.setString(0, "2011-01-27 10:00:00");
    	    //query.setString(1, "2011-01-31 10:00:00");
    	/*    query.setParameter("startDateRange", ts1);
    	    query.setParameter("endDateRange", ts2);*/
    	    if(paramsValues!=null)
	    	    for (int i = 0; i < paramsValues.size(); i++) {
	    	    	Object object = (Object)paramsValues.get(i);
	    	    	query.setParameter(i, object);
	    		}

    	/*    HQL_QUERY = "select i from events i where i.invoiceDate between :start and :end ";

    	    Query query = session.createQuery(HQL_QUERY);

    	    String strDateFrom = "2010/10/25";
    	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    	    Date dateFrom = formatter.parse(strDateFrom);

    	    String strDateTo = "2010/11/02";
    	    Date dateTo = formatter.parse(strDateTo);

    	    query.setParameter("start", dateFrom);
    	    query.setParameter("end", dateTo);*/
    	    List results = query.list();
    	    logger.debug("======= NumResults= "+results.size());
    	/*    for (Iterator it = result.iterator(); it.hasNext();) {
    	    		Events event = (Events) it.next();
    	            //logger.debug("\nCol: " + event.get());
    	           // logger.debug("Invoice Date: " + event.getInvoiceDate());

    	    }*/
    	    tx.commit(); // flush occurs
    	    /** Closing Session */
    	    session.close();
    	    return results;
    	}

    public List customSQLQuery(String SQL_QUERY, String tableNameAlias, Class cls){
    	logger.debug("==SQL_QUERY==>"+SQL_QUERY);
    	List results = null;
    	    /** Getting the Session Factory and session */
    	    SessionFactory factory = ApplicationContextProvider.getSessionFactory();
    	    Session session = factory.openSession();
    	    /** Starting the Transaction */
    	    try{
    	    Transaction tx = session.beginTransaction();
    	    //Query query = session.createSQLQuery(SQL_QUERY);

    	    results = session.createSQLQuery(SQL_QUERY).addEntity(tableNameAlias, cls).list();
    	   // results = query.list();
    	    logger.debug("======= DB query NumResults= "+results.size());
    	    tx.commit(); // flush occurs
    	    }catch (Exception e) {
    	    	e.printStackTrace();
    	    	logger.error(e);
				// TODO: handle exception
			}finally{
    	    /** Closing Session */
    	    session.close();
			}
    	    return results;
    	}
    public Session getSession()
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }



}
