package com.lbr.utils;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 
public class ApplicationContextProvider implements ApplicationContextAware{
 private static ApplicationContext ctx = null;
 private static SessionFactory sessionFactory = null;

 public static ApplicationContext getApplicationContext() {
	 return ctx;
 }
 
 public void setApplicationContext(ApplicationContext ctx) throws BeansException {
	 this.ctx = ctx;
 }
 
 public static SessionFactory getSessionFactory()
 {
     boolean allowCreate = true;
     if(sessionFactory== null){
	     //return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    	 sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
     }
     return sessionFactory;
 }
}
