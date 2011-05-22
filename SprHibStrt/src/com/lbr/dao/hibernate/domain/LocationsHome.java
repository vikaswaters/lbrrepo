package com.lbr.dao.hibernate.domain;

// Generated Feb 23, 2011 12:34:52 PM by Hibernate Tools 3.4.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Locations.
 * @see com.lbr.dao.hibernate.domain.Locations
 * @author Hibernate Tools
 */
public class LocationsHome {

	private static final Log log = LogFactory.getLog(LocationsHome.class);

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

	public void persist(Locations transientInstance) {
		log.debug("persisting Locations instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Locations instance) {
		log.debug("attaching dirty Locations instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Locations instance) {
		log.debug("attaching clean Locations instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Locations persistentInstance) {
		log.debug("deleting Locations instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Locations merge(Locations detachedInstance) {
		log.debug("merging Locations instance");
		try {
			Locations result = (Locations) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Locations findById(java.lang.Integer id) {
		log.debug("getting Locations instance with id: " + id);
		try {
			Locations instance = (Locations) sessionFactory.getCurrentSession()
					.get("com.lbr.dao.hibernate.Locations", id);
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

	public List<Locations> findByExample(Locations instance) {
		log.debug("finding Locations instance by example");
		try {
			List<Locations> results = (List<Locations>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.lbr.dao.hibernate.Locations")
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
