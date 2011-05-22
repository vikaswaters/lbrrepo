package com.lbr.dao.specificdao.dao;


import java.util.Iterator;
import java.util.List;

import com.lbr.dao.genericdao.GenericDao;
import com.lbr.dao.hibernate.domain.Category;



public interface CategoryDao extends GenericDao<Category, Integer>
	{
	    List<Category> findByName(String name);
	    Iterator<Category> iterateById(int id);
	}

