package com.eden.dao.support.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.eden.dao.support.DaoService;
import com.eden.dao.support.EntityDao;

@Repository("entityDao")
public class DefaultEntityDao implements EntityDao{
	
	@Resource
	private DaoService daoService ;
	
	@Override
	public <T> T selectOne(String statementId) {
		return daoService.selectOne(statementId);
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
