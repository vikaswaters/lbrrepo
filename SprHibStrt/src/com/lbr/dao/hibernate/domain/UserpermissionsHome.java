package com.lbr.dao.hibernate.domain;

// Generated 07-Mar-2011 11:48:20 by Hibernate Tools 3.4.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Userpermissions.
 * @see com.lbr.dao.hibernate.domain.Userpermissions
 * @author Hibernate Tools
 */
public class UserpermissionsHome {

	private static final Log log = LogFactory.getLog(UserpermissionsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Userpermissions transientInstance) {
		log.debug("persisting Userpermissions instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Userpermissions instance) {
		log.debug("attaching dirty Userpermissions instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userpermissions instance) {
		log.debug("attaching clean Userpermissions instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Userpermissions persistentInstance) {
		log.debug("deleting Userpermissions instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userpermissions merge(Userpermissions detachedInstance) {
		log.debug("merging Userpermissions instance");
		try {
			Userpermissions result = (Userpermissions) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Userpermissions findById(int id) {
		log.debug("getting Userpermissions instance with id: " + id);
		try {
			Userpermissions instance = (Userpermissions) sessionFactory
					.getCurrentSession().get(
							"com.lbr.dao.hibernate.Userpermissions", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Userpermissions> findByExample(Userpermissions instance) {
		log.debug("finding Userpermissions instance by example");
		try {
			List<Userpermissions> results = (List<Userpermissions>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.lbr.dao.hibernate.Userpermissions")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
