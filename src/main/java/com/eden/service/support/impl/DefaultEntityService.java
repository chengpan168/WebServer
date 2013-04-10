package com.eden.service.support.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;

import com.eden.dao.support.EntityDao;
import com.eden.dao.support.Page;
import com.eden.service.support.EntityService;

public class DefaultEntityService implements EntityService {

	@Resource(name="entityDao")
	private EntityDao entityDao ;

	@Override
	public <T> T selectOne(String statementId) {
		return entityDao.selectOne(statementId);
	}

	@Override
	public <T> T selectOne(String statementId, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T selectOne(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, Object parameters,
			RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectPage(String statementId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long selectCount(String statementId, Page page) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T query(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(String statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int insert(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(String statementId, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int update(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String statementId, Object parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int deleteById(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int delete(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String statementId, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public EntityDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(EntityDao entityDao) {
		this.entityDao = entityDao;
	}
	
}
