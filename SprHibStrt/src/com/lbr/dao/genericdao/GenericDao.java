package com.lbr.dao.genericdao;

import java.io.Serializable;
import java.util.List;

/**
 * The basic GenericDao interface with CRUD methods
 * Finders are added with interface inheritance and AOP introductions for concrete implementations
 *
 * Extended interfaces may declare methods starting with find... list... iterate... or scroll...
 * They will execute a preconfigured query that is looked up based on the rest of the method name
 */
public interface GenericDao<T, PK extends Serializable>
{

    PK create(T newInstance);

    T read(PK id);

    boolean update(T transientObject);

    boolean delete(T persistentObject);
    
    /**
     * For executing custom queries by replacing parameters by ordinal (int)
     * @param HQL_QUERY
     * @param params
     * @return
     */
    public List customHibernateQueryByOrdinal(String HQL_QUERY, List<Object> params);
    
    /**
     * For executing custom queries by replacing parameters by string
     * @param HQL_QUERY
     * @param params
     * @param values
     * @return
     */
    public List customHibernateQuery(String HQL_QUERY, List<String> params, List<Object> values);
    
    public List customSQLQuery(String SQL_QUERY,  String tableNameAlias, Class cls);
}
