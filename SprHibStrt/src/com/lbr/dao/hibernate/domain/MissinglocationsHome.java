package com.lbr.dao.hibernate.domain;

// Generated 11-Mar-2011 20:54:27 by Hibernate Tools 3.4.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Missinglocations.
 * @see com.lbr.dao.hibernate.domain.Missinglocations
 * @author Hibernate Tools
 */
public class MissinglocationsHome {

	private static final Log log = LogFactory
			.getLog(MissinglocationsHome.class);

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

	public void persist(Missinglocations transientInstance) {
		log.debug("persisting Missinglocations instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Missinglocations instance) {
		log.debug("attaching dirty Missinglocations instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Missinglocations instance) {
		log.debug("attaching clean Missinglocations instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Missinglocations persistentInstance) {
		log.debug("deleting Missinglocations instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Missinglocations merge(Missinglocations detachedInstance) {
		log.debug("merging Missinglocations instance");
		try {
			Missinglocations result = (Missinglocations) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Missinglocations findById(com.lbr.dao.hibernate.domain.MissinglocationsId id) {
		log.debug("getting Missinglocations instance with id: " + id);
		try {
			Missinglocations instance = (Missinglocations) sessionFactory
					.getCurrentSession().get(
							"com.lbr.dao.hibernate.Missinglocations", id);
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

	public List<Missinglocations> findByExample(Missinglocations instance) {
		log.debug("finding Missinglocations instance by example");
		try {
			List<Missinglocations> results = (List<Missinglocations>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.lbr.dao.hibernate.Missinglocations")
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
