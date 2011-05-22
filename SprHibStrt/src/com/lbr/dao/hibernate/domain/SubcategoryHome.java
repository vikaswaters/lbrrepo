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
 * Home object for domain model class Subcategory.
 * @see com.lbr.dao.hibernate.domain.Subcategory
 * @author Hibernate Tools
 */
public class SubcategoryHome {

	private static final Log log = LogFactory.getLog(SubcategoryHome.class);

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

	public void persist(Subcategory transientInstance) {
		log.debug("persisting Subcategory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Subcategory instance) {
		log.debug("attaching dirty Subcategory instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subcategory instance) {
		log.debug("attaching clean Subcategory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Subcategory persistentInstance) {
		log.debug("deleting Subcategory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subcategory merge(Subcategory detachedInstance) {
		log.debug("merging Subcategory instance");
		try {
			Subcategory result = (Subcategory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subcategory findById(java.lang.Integer id) {
		log.debug("getting Subcategory instance with id: " + id);
		try {
			Subcategory instance = (Subcategory) sessionFactory
					.getCurrentSession().get(
							"com.lbr.dao.hibernate.Subcategory", id);
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

	public List<Subcategory> findByExample(Subcategory instance) {
		log.debug("finding Subcategory instance by example");
		try {
			List<Subcategory> results = (List<Subcategory>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.lbr.dao.hibernate.Subcategory")
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
