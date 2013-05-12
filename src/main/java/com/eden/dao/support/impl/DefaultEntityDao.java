package com.eden.dao.support.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.eden.dao.support.DaoService;
import com.eden.dao.support.EntityDao;
import com.eden.dao.support.Page;

@Repository("entityDao")
public class DefaultEntityDao implements EntityDao{
	
	@Resource
	private DaoService daoService ;
	
	@Override
	public <T> T selectOne(String statementId) {
		return daoService.selectOne(statementId);
	}
	
	@Override
	public <T> T selectOne(String statementId, Object param) {
		return daoService.selectOne(statementId , param );
	}

	@Override
	public <T> List<T> selectList(String statementId) {
		return daoService.selectList(statementId);
	}

	@Override
	public <T> List<T> selectList(String statementId,
			Object param) {
		return daoService.selectList(statementId , param);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			RowBounds rowBounds) {
		return selectList(statementId , null , rowBounds);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			Object param, RowBounds rowBounds) {
		return daoService.selectList(statementId , param , rowBounds);
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
		return daoService.selectOne(statementId, page) ;
	}


	@Override
	public int insert(String statement) {
		return daoService.insert(statement) ;
	}

	@Override
	public int insert(String statementId, Object param) {
		return daoService.insert(statementId, param) ;
	}


	@Override
	public int insertAll(String statementId, Collection<? extends Object> params) {
		return daoService.insert(statementId, params) ;
	}

	@Override
	public int update(String statementId, Object parameter) {
		return daoService.update(statementId, parameter);
	}


	@Override
	public int updateAll(String statementId, Collection<? extends Object> params) {
		return daoService.update(statementId, params) ;
	}


	@Override
	public int delete(String statementId, Object param) {
		return daoService.delete(statementId, param) ;
	}


	@Override
	public int deleteAll(String statementId, Collection<? extends Object> params) {
		return daoService.delete(statementId, params) ;
	}
	
	
/*
 * ----------------------------------分隔线--------------------------------------
 **/	
	public DaoService getDaoService() {
		return daoService;
	}
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	

}
