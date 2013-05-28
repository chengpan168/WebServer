package com.eden.service.support.impl;

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
		return entityDao.selectOne(statementId , param );
	}

	@Override
	public <T> List<T> selectList(String statementId) {
		return entityDao.selectList(statementId);
	}

	@Override
	public <T> List<T> selectList(String statementId,
			Object param) {
		return entityDao.selectList(statementId , param);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			RowBounds rowBounds) {
		return selectList(statementId , null , rowBounds);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			Object param, RowBounds rowBounds) {
		return entityDao.selectList(statementId , param , rowBounds);
	}


	@Override
	public <T> List<T> selectPage(String statementId,
			Page page) {
		RowBounds rowBounds = null ;
		if(page!=null){
			rowBounds = new RowBounds(page.getTotal(), page.getPageCount()) ;
		}
		return selectList(statementId , page , rowBounds);
	}


	@Override
	public int selectCount(String statementId, Page page) {
		return entityDao.selectOne(statementId, page) ;
	}


	@Override
	public int insert(String statement) {
		return entityDao.insert(statement) ;
	}

	@Override
	public int insert(String statementId, Object param) {
		return entityDao.insert(statementId, param) ;
	}


	@Override
	public int insertAll(String statementId, Collection<? extends Object> params) {
		return entityDao.insert(statementId, params) ;
	}

	@Override
	public int update(String statementId, Object parameter) {
		return entityDao.update(statementId, parameter);
	}


	@Override
	public int updateAll(String statementId, Collection<? extends Object> params) {
		return entityDao.update(statementId, params) ;
	}


	@Override
	public int delete(String statementId, Object param) {
		return entityDao.delete(statementId, param) ;
	}


	@Override
	public int deleteAll(String statementId, Collection<? extends Object> params) {
		return entityDao.delete(statementId, params) ;
	}

	
	public EntityDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(EntityDao entityDao) {
		this.entityDao = entityDao;
	}
	
}
