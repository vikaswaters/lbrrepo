package com.lbr.dao.specificdao.dao;

import java.util.Iterator;
import java.util.List;

import com.lbr.dao.genericdao.GenericDao;
import com.lbr.dao.specificdao.domain.Person;



public interface PersonDao extends GenericDao<Person, Long>
{
    List<Person> findByName(String name);
    Iterator<Person> iterateByWeight(int weight);
}
